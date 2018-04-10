package top.vchao.examine.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import top.vchao.examine.R;
import top.vchao.examine.constants.SPkey;
import top.vchao.examine.utils.SPUtils;

/**
 * @ 创建时间: 2017/6/13 on 15:43.
 * @ 描述：闪屏启动页面
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class SplashActivity extends BaseActivity {

    @Override
    int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    void initData() {
        //延时2s，跳转。
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = (String) SPUtils.get(SplashActivity.this, SPkey.UserName, "");
                if (!TextUtils.isEmpty(username)) {
                    //直接跳转主页面
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //跳转到登录页面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }

}
