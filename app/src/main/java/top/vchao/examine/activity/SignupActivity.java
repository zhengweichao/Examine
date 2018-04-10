package top.vchao.examine.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import top.vchao.examine.constants.Config;
import top.vchao.examine.R;
import top.vchao.examine.bean.JsonSignupBean;
import top.vchao.examine.utils.LogUtils;

/**
 * @ 创建时间: 2017/6/13 on 16:10.
 * @ 描述：注册页面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class SignupActivity extends BaseActivity {

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup)
    AppCompatButton _signupButton;

    @Override
    int getLayoutId() {
        return R.layout.activity_signup;
    }

    public void signup() {
        //判断是否合法
        if (!validate()) {
            onSignupFailed(0);
            return;
        }
        _signupButton.setEnabled(false);

        //显示圆形进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("创建账号...");
        progressDialog.show();
        //获取数据
        String username = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
//      联网获取数据
        OkGo.get(Config.URL_SIGNUP)
                .params("username", username)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JsonSignupBean jsonSignupBean = gson.fromJson(s, JsonSignupBean.class);
                        //如果得到返回消息为ok,则注册成功。
                        if (jsonSignupBean.getMsg().equals("ok")) {
                            LogUtils.e("onSuccess: 注册成功");
                            onSignupSuccess();
                            //对话框消失
                            progressDialog.dismiss();
                        } else {
                            onSignupFailed(1);
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    /**
     * 登陆成功
     */
    public void onSignupSuccess() {
        _signupButton.setEnabled(true);

        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 注册失败，按钮置为可用
     * 依据传参不同，进行不同吐司
     */
    public void onSignupFailed(int i) {
        if (i == 1) {
            Toast.makeText(getBaseContext(), "该用户名已经被注册", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "注册失败", Toast.LENGTH_LONG).show();
        }
        _signupButton.setEnabled(true);
    }

    /**
     * @return 输入内容是否合法
     */
    public boolean validate() {
        boolean valid = true;
//      从控件中获取数据
        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        //检测账号是否正确
        if (name.isEmpty()) {
            _nameText.setError("账号不能为空");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        //检测密码是否正确
        if (password.isEmpty()) {
            _passwordText.setError("请输入密码");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        //检测重复密码是否正确
        if (reEnterPassword.isEmpty() || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("两次密码不一致");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }
        return valid;

    }

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                signup();
                break;
            case R.id.link_login:
                //点击登录连接，跳转到登录页面
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                break;
        }
    }
}