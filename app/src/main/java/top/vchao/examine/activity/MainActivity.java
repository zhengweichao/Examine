package top.vchao.examine.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import top.vchao.examine.R;

import java.util.Random;

public class MainActivity extends BaseActivity {

    private AlertDialog.Builder exitDialog;
    private AlertDialog.Builder mobileDialog;
    private Button btnSxlx;
    private Button btnSjlx;
    private Button btnZjlx;
    private Button btnLaw;
    private Button btnMnks;
    private ProgressDialog pd;
    private Random random = new Random();
    private SharedPreferences.Editor editor;
    private Button btnCollection;
    private Button btnCtjlb;
    private Button btnPoint;
    private Button btnCheats;
    private TextView tvUsername;
    private String usernameFromLogin;
    private String usernameFromRegister;
    private LinearLayout llCircleOfFriends;

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {

        btnSxlx = (Button) findViewById(R.id.btn_sxlx);
        btnSjlx = (Button) findViewById(R.id.btn_sjlx);
        btnZjlx = (Button) findViewById(R.id.btn_zjlx);
        btnMnks = (Button) findViewById(R.id.btn_mnks);
        btnLaw = (Button) findViewById(R.id.btn_law);
        btnCollection = (Button) findViewById(R.id.btn_collection);

        btnCtjlb = (Button) findViewById(R.id.btn_ctjlb);
        btnPoint = (Button) findViewById(R.id.btn_point);
        btnCheats = (Button) findViewById(R.id.btn_cheats);

        Intent intent = getIntent();
        usernameFromLogin = intent.getStringExtra("usernameFromLogin");
        usernameFromRegister = intent.getStringExtra("usernameFromRegister");
        if (usernameFromLogin != null) {
            tvUsername.setText(usernameFromLogin);
        } else if (usernameFromRegister != null) {
            tvUsername.setText(usernameFromRegister);
        }
    }

    @Override
    void initListener() {
        btnLaw.setOnClickListener(this);
        btnSxlx.setOnClickListener(this);
        btnSjlx.setOnClickListener(this);
        btnZjlx.setOnClickListener(this);
        btnMnks.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
        btnCtjlb.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnCheats.setOnClickListener(this);
    }

    @Override
    void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sxlx:
                // TODO: 2017/6/13 顺序练习
                Intent intent1 = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_sjlx:
                // TODO: 2017/6/13 随机练习
                break;

            case R.id.btn_zjlx:
                // TODO: 2017/6/13 章节练习
                break;

            case R.id.btn_mnks:
                // TODO: 2017/6/13 模拟考试
                break;

            case R.id.btn_ctjlb:
                // TODO: 2017/6/13 错题记录本
                break;

            case R.id.btn_point:
                // TODO: 2017/6/13 考试要点
                break;

            case R.id.btn_law:
                // TODO: 2017/6/13 法律法规
                break;

            case R.id.btn_cheats:
                // TODO: 2017/6/13 必过秘籍
                break;

            case R.id.btn_collection:
                // TODO: 2017/6/13 收藏夹
                break;

            default:
                break;
        }
    }

}
