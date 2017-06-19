package top.vchao.examine.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import top.vchao.examine.R;

import java.util.ArrayList;

import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.db.LoveDao;

/**
 * @ 创建时间: 2017/6/14 on 09:46.
 * @ 描述：分数界面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class GradeActivity extends BaseActivity {

    private ListView lvScore;
    private TextView tvScore;
    private ArrayList<CharSequence> timu;

    @Override
    int getLayoutId() {
        return R.layout.activity_grade;
    }

    @Override
    void initView() {
        lvScore = (ListView) findViewById(R.id.lv_activity_score_detail);
        tvScore = (TextView) findViewById(R.id.tv_activity_score_score);
    }

    @Override
    void initData() {
//        接收传递来的数据
        timu = getIntent().getCharSequenceArrayListExtra("timu");
        String grade = getIntent().getExtras().get("grade").toString().trim();
//        设置显示成绩分数
        tvScore.setText("您的成绩是： " + grade);
//        设置适配器
        lvScore.setAdapter(new MyAdapter());
    }

    /**
     * 题目列表适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return timu != null ? timu.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return timu.get(position);
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
                convertView = LayoutInflater.from(GradeActivity.this).inflate(R.layout.list_item_activity_score, null);
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
            QuestBean questBeenC = LoveDao.queryLove(Integer.parseInt(timu.get(position).toString()));
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
                    holder.tvWrongAnswer.setText("你选择了：null");
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
