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
import top.vchao.examine.R;
import top.vchao.examine.bean.JsonLoginBean;

/**
 * @ 创建时间: 2017/6/13 on 16:03.
 * @ 描述：登录页面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class LoginActivity extends BaseActivity {

    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;

    @Override
    int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    void initView() {
        _emailText = (EditText) findViewById(R.id.input_username);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
    }

    @Override
    void initListener() {
        _loginButton.setOnClickListener(this);
        _signupLink.setOnClickListener(this);
    }

    @Override
    void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.link_signup:
                //如果点击了注册链接，则跳转到注册页面
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

    public void login() {
        //如果内容不合法，则直接返回，显示错误。
        if (!validate()) {
            onLoginFailed();
            return;
        }
        //合法，将登录按钮置为不可用
        _loginButton.setEnabled(false);
        //圆形进度对话框
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();

        OkGo.get("http://192.168.1.116:8080/Examine1/LogLet")
                .params("username", email)
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
                // By default we just finish the Activity and log them in automatically
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
//        _loginButton.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * 登录失败
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登陆失败", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    /**
     * 判断是否账号密码是否合法
     *
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        //获取输入内容
        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();

        //判断账号
        if (email.isEmpty()) {
            _emailText.setError("请输入你的账号");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        //判断密码
        if (password.isEmpty()) {
            _passwordText.setError("请输入你的密码");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
