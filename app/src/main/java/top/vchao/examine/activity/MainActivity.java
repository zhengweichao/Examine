package top.vchao.examine.activity;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import top.vchao.examine.R;

public class MainActivity extends BaseActivity {

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_main_start_answer, R.id.btn_choose, R.id.btn_judge, R.id.btn_blanks, R.id.btn_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_start_answer:
                //   开始答题  跳转回答页面 （默认进入选择题回答）
                Intent intent1 = new Intent(MainActivity.this, TestSettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_choose:
                // 选择题
                Intent intent3 = new Intent(MainActivity.this, AnswerActivity.class);
                intent3.putExtra("type", 1);
                startActivity(intent3);
                break;
            case R.id.btn_judge:
                //  判断题
                Intent intent4 = new Intent(MainActivity.this, AnswerActivity.class);
                intent4.putExtra("type", 2);
                startActivity(intent4);
                break;
            case R.id.btn_blanks:
                // 简答题
                Intent intent5 = new Intent(MainActivity.this, AnswerActivity.class);
                intent5.putExtra("type", 3);
                startActivity(intent5);
                break;
            case R.id.btn_about:
                //  关于
                Intent intent6 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
