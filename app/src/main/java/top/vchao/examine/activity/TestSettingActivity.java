package top.vchao.examine.activity;

import android.content.Intent;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.OnClick;
import top.vchao.examine.R;
import top.vchao.examine.constants.Config;

public class TestSettingActivity extends BaseActivity {


    @BindView(R.id.rg_test_type)
    RadioGroup rgTestType;
    @BindView(R.id.rg_test_num)
    RadioGroup rgTestNum;

    //    初始化试题类型
    String type = Config.TestType_English;
    //    初始化 题目数目
    int num = 5;

    @Override
    int getLayoutId() {
        return R.layout.activity_test_setting;
    }

    @Override
    void initView() {
        rgTestType.check(R.id.rbtn_enlish);
        rgTestNum.check(R.id.rbtn_num_5);
    }

    @Override
    void initListener() {
        rgTestType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_chinise:
                        type = Config.TestType_Chinese;
                        break;
                    case R.id.rbtn_math:
                        type = Config.TestType_Math;
                        break;
                    case R.id.rbtn_enlish:
                        type = Config.TestType_English;
                        break;
                    default:
                        break;
                }
            }
        });
        rgTestNum.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_num_5:
                        num = 5;
                        break;
                    case R.id.rbtn_num_10:
                        num = 10;
                        break;
                }
            }
        });

    }

    @OnClick(R.id.btn_start_test)
    public void onViewClicked() {
        Intent intent = new Intent(TestSettingActivity.this, TestAnswerActivity.class);
        intent.putExtra("kind", type);
        intent.putExtra("num", num + "");
        startActivity(intent);
        finish();
    }
}
