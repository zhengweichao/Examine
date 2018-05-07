package top.vchao.examine.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;
import top.vchao.examine.R;
import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.bean.UpGradeBean;
import top.vchao.examine.constants.Config;
import top.vchao.examine.constants.SPkey;
import top.vchao.examine.db.LoveDao;
import top.vchao.examine.utils.LogUtils;
import top.vchao.examine.utils.SPUtils;
import top.vchao.examine.utils.TimeUtils;
import top.vchao.examine.utils.ToastUtils;

public class TestGradeActivity extends BaseActivity {


    @BindView(R.id.tv_test_grade_name)
    TextView tvTestGradeName;
    @BindView(R.id.tv_test_grade_score)
    TextView tvTestGradeScore;
    @BindView(R.id.tv_test_grade_kind)
    TextView tvTestGradeKind;
    @BindView(R.id.tv_test_grade_use_time)
    TextView tvTestGradeUseTime;
    @BindView(R.id.lv_grade_score_detail)
    ListView lvGradeScoreDetail;
    @BindView(R.id.tv_test_grade_num)
    TextView tvTestGradeNum;
    @BindView(R.id.tv_test_grade_end_time)
    TextView tvTestGradeEndTime;
    private ArrayList<CharSequence> titleName;
    private String grade;
    private String time;
    private String kind;
    private String num;
    private String end_time;
    private String username;


    @Override
    int getLayoutId() {
        return R.layout.activity_test_grade;
    }

    @Override
    void getPreIntent() {
//        接收传递来的数据
        titleName = getIntent().getCharSequenceArrayListExtra("timu");
        grade = getIntent().getStringExtra("grade");
        time = getIntent().getStringExtra("time");
        kind = getIntent().getStringExtra("kind");
        num = getIntent().getStringExtra("num");
        end_time = TimeUtils.getNowTime();
        username = (String) SPUtils.get(TestGradeActivity.this, SPkey.UserName, "");
    }

    @Override
    void initView() {
//        设置显示成绩分数
        tvTestGradeName.setText(username + " 您的本次得分是：");
        tvTestGradeScore.setText(grade + "分");
        tvTestGradeUseTime.setText("使用时间：" + time);
        tvTestGradeNum.setText("题目数量：" + num);
        tvTestGradeEndTime.setText("交卷时间：" + end_time);
        switch (kind) {
            case Config.TestType_Chinese:
                tvTestGradeKind.setText("试卷类型：语文卷");
                break;
            case Config.TestType_English:
                tvTestGradeKind.setText("试卷类型：英语卷");
                break;
            case Config.TestType_Math:
                tvTestGradeKind.setText("试卷类型：数学卷");
                break;
            default:
                tvTestGradeKind.setVisibility(View.GONE);
                break;
        }
//        设置适配器
        lvGradeScoreDetail.setAdapter(new MyAdapter());
    }

    @Override
    void initData() {
//        上传成绩
//        http://localhost:8080/Examine1/UpUserGrade?username=张三&grade=88&usetime=10&endtime=111&kind=math&number=5
        OkGo.get(Config.URL_UP_USER_GRADE)
                .params("username", username)
                .params("grade", grade)
                .params("usetime", time)
                .params("endtime", end_time)
                .params("kind", kind)
                .params("number", num)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.e(" 上传答题信息 " + s);
                        Gson gson = new Gson();
                        UpGradeBean upGradeBean = gson.fromJson(s, UpGradeBean.class);
                        if (upGradeBean.getCode().equals("200")) {
                            ToastUtils.showShort("成绩上传成功！");
                        } else {
                            ToastUtils.showShort("" + upGradeBean.getStatus());
                        }
                    }
                });
    }

    /**
     * 题目列表适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titleName != null ? titleName.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return titleName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            listview优化，复用布局以及id
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(TestGradeActivity.this).inflate(R.layout.list_item_activity_score, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_activty_score_title);
                holder.tvOptionA = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionA);
                holder.tvOptionB = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionB);
                holder.tvOptionC = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionC);
                holder.tvOptionD = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionD);
                holder.tvRightAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_right);
                holder.tvWrongAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_wrong);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            查询数据库
            QuestBean questBeenC = LoveDao.queryLove(Integer.parseInt(titleName.get(position).toString()));
            String title = questBeenC.getTitle();
            holder.tvTitle.setText(position + 1 + "." + title);
//            设置题目数据
            //            显示正确答案以及填写答案
            String rightAnswer = questBeenC.getAnswer();
            holder.tvRightAnswer.setText("正确答案：" + rightAnswer);

//            选择
            if (("1".equals(questBeenC.getQ_type() + ""))) {
                String optionA = questBeenC.getOptionA();
                holder.tvOptionA.setText(optionA);
                String optionB = questBeenC.getOptionB();
                holder.tvOptionB.setText(optionB);
                String optionC = questBeenC.getOptionC();
                holder.tvOptionC.setText(optionC);
                String optionD = questBeenC.getOptionD();
                holder.tvOptionD.setText(optionD);
                String YourAnswer = questBeenC.getMyanswer();
                holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);

                if (!rightAnswer.equals(YourAnswer)) {
                    holder.tvTitle.setTextColor(Color.RED);
                }
            }
//            判断
            else if ("2".equals(questBeenC.getQ_type() + "")) {
                holder.tvOptionA.setText("对");
                holder.tvOptionB.setText("错");
                holder.tvOptionC.setVisibility(View.GONE);
                holder.tvOptionD.setVisibility(View.GONE);
                String YourAnswer = questBeenC.getMyanswer();
                if ("A".equals(YourAnswer)) {
                    holder.tvWrongAnswer.setText("你选择了：对");
                } else if ("B".equals(YourAnswer)) {
                    holder.tvWrongAnswer.setText("你选择了：错");
                } else {
                    holder.tvWrongAnswer.setText("你没有进行选择");
                }

            }

            return convertView;
        }

        class ViewHolder {
            TextView tvTitle, tvRightAnswer, tvWrongAnswer, tvOptionA,
                    tvOptionB, tvOptionC, tvOptionD;
        }
    }
}
