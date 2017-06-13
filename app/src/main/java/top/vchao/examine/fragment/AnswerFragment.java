package top.vchao.examine.fragment;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vchao.examine.R;

import top.vchao.examine.bean.QuestBean;


/**
 * @ 创建时间: 2017/6/11 on 16:25.
 * @ 描述：
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class AnswerFragment extends BaseFragment {
    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
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
}
