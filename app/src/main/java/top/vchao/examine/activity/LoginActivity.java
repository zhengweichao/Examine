package top.vchao.examine.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import top.vchao.examine.R;
import top.vchao.examine.bean.JsonLoginBean;
import top.vchao.examine.constants.Config;
import top.vchao.examine.constants.SPkey;
import top.vchao.examine.utils.LogUtils;
import top.vchao.examine.utils.ToastUtils;
import top.vchao.examine.utils.SPUtils;

/**
 * @ 创建时间: 2017/6/13 on 16:03.
 * @ 描述：登录页面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class LoginActivity extends BaseActivity {

    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;


    @Override
    int getLayoutId() {
        return R.layout.activity_login;
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
        btnLogin.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登录中...");
        progressDialog.show();
        //获取输入内容
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        SPUtils.put(LoginActivity.this, SPkey.UserName, username);

        //联网，获取数据
        OkGo.get(Config.URL_LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JsonLoginBean jsonLoginBean = gson.fromJson(s, JsonLoginBean.class);
                        //如果得到权限>0,则登录成功。
                        if (jsonLoginBean.getPermission() > 0) {
                            LogUtils.e("onSuccess: ===  登录成功");
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
        btnLogin.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 登录失败
     */
    public void onLoginFailed() {
        ToastUtils.showShort("登陆失败");
        btnLogin.setEnabled(true);
    }

    /**
     * @return 判断是否账号密码是否合法
     */
    public boolean validate() {
        //设置初值，默认为合法
        boolean valid = true;

        //获取输入内容
        String email = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        //判断账号
        if (email.isEmpty()) {
            inputUsername.setError("请输入你的账号");
            valid = false;
        } else {
            inputUsername.setError(null);
        }
        //判断密码
        if (password.isEmpty()) {
            inputPassword.setError("请输入你的密码");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

    @OnClick({R.id.btn_login, R.id.link_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
        }
    }
}
