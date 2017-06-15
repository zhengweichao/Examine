package top.vchao.examine.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import top.vchao.examine.R;

import java.util.ArrayList;
import java.util.List;

import top.vchao.examine.bean.JsonQuestBean;
import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.db.LoveDao;
import top.vchao.examine.fragment.AnswerFragment;

/**
 * @ 创建时间: 2017/6/13 on 17:08.
 * @ 描述：回答界面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class AnswerActivity extends BaseActivity implements Chronometer.OnChronometerTickListener {

    private Chronometer chronometer;
    private ViewPager vp_answer;
    private ArrayList<Fragment> fragmentlists;
    private int minute = 0;
    private int second = 0;
    private AlertDialog.Builder builder;
    private ArrayList<String> a;
    private Button btn_previous;
    private Button btn_submit;
    private Button btn_next;
    private int nowpager = 0;
    private List<QuestBean> messages;

    @Override
    int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    void initView() {
        initNet();
        chronometer = (Chronometer) findViewById(R.id._chro_exam);
        vp_answer = (ViewPager) findViewById(R.id.vp_answer);
        btn_previous = (Button) findViewById(R.id._btn_previous);
        btn_submit = (Button) findViewById(R.id._btn_submit);
        btn_next = (Button) findViewById(R.id._btn_next);
        btn_previous.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
        setChronometer();
    }

    private void setChronometer() {
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
        chronometer.setOnClickListener(this);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        second++;
        if (second == 59) {
            minute++;
            second = 00;
        }
    }

    private String nowtime() {
        if (second < 10) {
            return (minute + ":0" + second);
        } else {
            return (minute + ":" + second);
        }
    }

    private void initNet() {
        a = new ArrayList<>();
        fragmentlists = new ArrayList<>();
        Log.e("zwc", "initNet: 开始联网…………");
        OkGo.get("http://192.168.1.116:8080/Examine1/GetQuestionLet?type=1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("zwc", "onSuccess: ========---------======" + s);
                        Gson gson = new Gson();
                        JsonQuestBean jsonQuestBean = gson.fromJson(s, JsonQuestBean.class);
                        messages = jsonQuestBean.getMessages();
                        Log.i("zwc", "：：：：：: "+ messages.get(3).getId());
                        for (int i = 0; i < messages.size(); i++) {
                            QuestBean questBeanQ = messages.get(i);
                            questBeanQ.setId(i);
                            fragmentlists.add(new AnswerFragment(questBeanQ));
                            LoveDao.insertLove(questBeanQ);
                            a.add(questBeanQ.getId()+"");

                            Log.e("zwc", i+"ooooooonSuccessssssssssss: "+questBeanQ.getId()+questBeanQ.getTitle());
                        }
                        vp_answer.setAdapter(new MainAdapter(getSupportFragmentManager()));
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.i("zwc", "onError///////////////////");
                    }
                });
        Log.e("zwc", "initNet: 联网结束…………");
    }

    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        //获取条目
        @Override
        public Fragment getItem(int position) {
            return fragmentlists.get(position);
        }

        //数目
        @Override
        public int getCount() {
            return fragmentlists.size();
        }
    }

    @Override
    void processClick(View v) {
        switch (v.getId()) {
            case R.id._btn_previous:
                if (nowpager == 0) {
                    Toast.makeText(AnswerActivity.this, "已经到头啦!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(--nowpager);
                }
                break;
            case R.id._btn_submit:
                initAlertDialog();
                builder.show();
                break;
            case R.id._btn_next:
                if (nowpager == fragmentlists.size()) {
                    Toast.makeText(AnswerActivity.this, "已经是最后一题了!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(++nowpager);
                }
                break;
            default:
                break;
        }
    }

    // 弹出是否确认交卷的对话框
    private void initAlertDialog() {

        builder = new AlertDialog.Builder(AnswerActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否确定交卷?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // TODO: 2017/6/14 交卷操作
                int grade = 0;
                for (int i = 0; i < messages.size(); i++) {
                    Log.i("zwc", "onClick: " + Integer.parseInt(a.get(i)));

                    LoveDao.queryLove(Integer.parseInt(a.get(i)));
                    QuestBean questBeenA = LoveDao.queryLove(Integer.parseInt(a.get(i)));
                    if (questBeenA.getAnswer().equals(questBeenA.getMyanswer())) {
                        grade += 20;
                    };
                }
                Intent intent = new Intent(AnswerActivity.this, GradeActivity.class);
                intent.putExtra("grade", "" + grade);
                intent.putStringArrayListExtra("timu", a);
                startActivity(intent);
                finish();
            }

        });
        builder.setNegativeButton("取消", null);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            nowpager = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
