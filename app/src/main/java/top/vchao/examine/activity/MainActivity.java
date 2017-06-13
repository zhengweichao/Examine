package top.vchao.examine.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vchao.examine.R;

import java.util.Random;

import top.vchao.examine.bean.JsonQuestBean;

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
        Gson gson = new Gson();
        JsonQuestBean JsonQuestBean = new JsonQuestBean();
        String str = gson.toJson(JsonQuestBean);
        Log.i("zwc", "initView: "+str);

        btnSxlx = (Button) findViewById(R.id.btn_sxlx);
        btnSjlx = (Button) findViewById(R.id.btn_sjlx);
        btnZjlx = (Button) findViewById(R.id.btn_zjlx);
        btnMnks = (Button) findViewById(R.id.btn_mnks);
        btnLaw = (Button) findViewById(R.id.btn_law);
        btnCollection = (Button) findViewById(R.id.btn_collection);

        btnCtjlb = (Button) findViewById(R.id.btn_ctjlb);
        btnPoint = (Button) findViewById(R.id.btn_point);
        btnCheats = (Button) findViewById(R.id.btn_cheats);
        btnLaw.setOnClickListener(new MyOnClickListener());
        btnSxlx.setOnClickListener(new MyOnClickListener());
        btnSjlx.setOnClickListener(new MyOnClickListener());
        btnZjlx.setOnClickListener(new MyOnClickListener());
        btnMnks.setOnClickListener(new MyOnClickListener());
        btnCollection.setOnClickListener(new MyOnClickListener());
        btnCtjlb.setOnClickListener(new MyOnClickListener());
        btnPoint.setOnClickListener(new MyOnClickListener());
        btnCheats.setOnClickListener(new MyOnClickListener());

        Intent intent = getIntent();
        usernameFromLogin = intent.getStringExtra("usernameFromLogin");
        usernameFromRegister = intent.getStringExtra("usernameFromRegister");
        if (usernameFromLogin != null) {
            tvUsername.setText(usernameFromLogin);
        } else if (usernameFromRegister != null) {
            tvUsername.setText(usernameFromRegister);
        }
    }

    class MyOnClickListener implements android.view.View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btn_sxlx:
                    Intent intent_sxlx = new Intent(MainActivity.this,
                            AnswerActivity.class);
                    startActivity(intent_sxlx);
                    break;

                case R.id.btn_sjlx:
                    Intent intent_sjlx = new Intent(MainActivity.this,
                            MainActivity.class);
                    startActivity(intent_sjlx);
                    break;

                case R.id.btn_zjlx:
                    Intent intent_zjlx = new Intent(MainActivity.this,
                            MainActivity.class);
                    startActivity(intent_zjlx);
                    break;

                case R.id.btn_mnks:


                    break;

                case R.id.btn_ctjlb:
                    Intent intent_ctjlb = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent_ctjlb);
                    break;

                case R.id.btn_point:
                    Intent intent_point = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent_point);
                    break;

                case R.id.btn_law:
                    Intent intent_law = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent_law);
                    break;

                case R.id.btn_cheats:
                    Intent intent_cheats = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent_cheats);
                    break;

                case R.id.btn_collection:
                    Intent intent_collection = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent_collection);
                    break;

                default:
                    break;
            }
        }
    }

}
