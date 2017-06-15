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
        timu = getIntent().getCharSequenceArrayListExtra("timu");
        for (int i = 0; i < timu.size(); i++) {
            timu.get(i);
        }
        String grade = getIntent().getExtras().get("grade").toString().trim();
        tvScore.setText("您的成绩是： " + grade);
    }

    @Override
    void initData() {
        MyAdapter adapter = new MyAdapter();
        lvScore.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return timu != null ? timu.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return timu.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
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
                holder.ivPicture = (ImageView) convertView.findViewById(R.id.iv_item_activity_score_picture);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            QuestBean questBeenC = LoveDao.queryLove(Integer.parseInt(timu.get(position).toString()));
            String title = questBeenC.getTitle();
            holder.tvTitle.setText(position + 1 + "." + title);
            String optionA = questBeenC.getOptionA();
            holder.tvOptionA.setText(optionA);
            String optionB = questBeenC.getOptionB();
            holder.tvOptionB.setText(optionB);
            String optionC = questBeenC.getOptionC();
            holder.tvOptionC.setText(optionC);
            String optionD = questBeenC.getOptionD();
            holder.tvOptionD.setText(optionD);

            String rightAnswer = questBeenC.getAnswer();
            holder.tvRightAnswer.setText("正确答案：" + rightAnswer);
            String YourAnswer = questBeenC.getMyanswer();
            holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);
            if (rightAnswer.equals(YourAnswer)) {
            } else {
                holder.tvTitle.setTextColor(Color.RED);
            }


            return convertView;
        }

        class ViewHolder {
            TextView tvTitle, tvRightAnswer, tvWrongAnswer, tvOptionA,
                    tvOptionB, tvOptionC, tvOptionD;
            ImageView ivPicture;
        }

    }
}
