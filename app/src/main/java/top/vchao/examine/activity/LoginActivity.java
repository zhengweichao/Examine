package top.vchao.examine.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import top.vchao.examine.CONFIG;
import top.vchao.examine.R;
import top.vchao.examine.bean.JsonLoginBean;

/**
 * @ 创建时间: 2017/6/13 on 16:03.
 * @ 描述：登录页面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class LoginActivity extends BaseActivity {

    private static final int REQUEST_SIGNUP = 0;

    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView tv_signup;

    @Override
    int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    void initView() {
        //通过id找控件
        et_username = (EditText) findViewById(R.id.input_username);
        et_password = (EditText) findViewById(R.id.input_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_signup = (TextView) findViewById(R.id.link_signup);

    }

    @Override
    void initListener() {
        //登录按钮，注册链接设置点击监听事件
        btn_login.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
    }

    @Override
    void processClick(View v) {
        switch (v.getId()) {
            //点击登录按钮，执行登录操作
            case R.id.btn_login:
                login();
                break;
            //如果点击了注册链接，则跳转到注册页面
            case R.id.link_signup:
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                //动画效果
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            default:
                break;
        }
    }

    /**
     * 登录方法
     */
    public void login() {
        //如果内容不合法，则直接返回，显示错误。
        if (!validate()) {
            onLoginFailed();
            return;
        }
        //输入合法，将登录按钮置为不可用，显示圆形进度对话框
        btn_login.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登录中...");
        progressDialog.show();
        //获取输入内容
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        //联网，获取数据
        OkGo.get(CONFIG.URL_LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JsonLoginBean jsonLoginBean = gson.fromJson(s, JsonLoginBean.class);
                        //如果得到权限>0,则登录成功。
                        if (jsonLoginBean.getPermission() > 0) {
                            Log.e("zwc", "onSuccess: ===");
                            onLoginSuccess();
                            progressDialog.dismiss();
                        } else {
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    /**
     * 重写返回键的返回方法
     */
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    /**
     * 登录成功
     */
    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 登录失败
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登陆失败", Toast.LENGTH_LONG).show();
        btn_login.setEnabled(true);
    }

    /**
     * @return 判断是否账号密码是否合法
     */
    public boolean validate() {
        //设置初值，默认为合法
        boolean valid = true;

        //获取输入内容
        String email = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //判断账号
        if (email.isEmpty()) {
            et_username.setError("请输入你的账号");
            valid = false;
        } else {
            et_username.setError(null);
        }
        //判断密码
        if (password.isEmpty()) {
            et_password.setError("请输入你的密码");
            valid = false;
        } else {
            et_password.setError(null);
        }

        return valid;
    }
}
