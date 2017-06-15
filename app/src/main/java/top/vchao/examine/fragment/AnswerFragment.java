package top.vchao.examine.fragment;

import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import top.vchao.examine.R;

import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.db.LoveDao;

/**
 * @ 创建时间: 2017/6/11 on 16:25.
 * @ 描述： 答题fragment
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class AnswerFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
    private String option = "";
    private RadioGroup rg_base;
    private TextView tv_title;
    QuestBean questBean = null;

    public AnswerFragment(QuestBean questBean) {
        this.questBean = questBean;
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_quest, null);
        tv_title = (TextView) view.findViewById(R.id._tv_title);
        rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
        rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
        rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
        rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
        rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
        rg_base.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if (questBean == null) {
            Log.i("zwc", "initData: questBean==null");
            return;
        }
        tv_title.setText("" + questBean.getTitle());
        rb_option_a.setText("" + questBean.getOptionA());
        rb_option_b.setText("" + questBean.getOptionB());
        rb_option_c.setText("" + questBean.getOptionC());
        rb_option_d.setText("" + questBean.getOptionD());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == rb_option_a.getId()) {
            option = "A";
        } else if (checkedId == rb_option_b.getId()) {
            option = "B";
        } else if (checkedId == rb_option_c.getId()) {
            option = "C";
        } else if (checkedId == rb_option_d.getId()) {
            option = "D";
        }
        questBean.setMyanswer(option);
//        Toast.makeText(mActivity, option+"==="+questBean.getAnswer(), Toast.LENGTH_SHORT).show();
        LoveDao.updateLove(questBean);
    }
}
