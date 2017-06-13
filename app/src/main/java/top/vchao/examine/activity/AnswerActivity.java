package top.vchao.examine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.vchao.examine.R;

import java.util.ArrayList;
import java.util.List;

import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.bean.JsonQuestBean;
import top.vchao.examine.fragment.AnswerFragment;

/**
 * @ 创建时间: 2017/6/13 on 17:08.
 * @ 描述：回答界面
 * @ 作者: 郑卫超 QQ: 2318723605
 */
public class AnswerActivity extends BaseActivity {

    private ViewPager vp_answer;
    private ArrayList<Fragment> fragmentlists;
    //模拟json文件
    String sjson = "{\"age\":81,\"messages\":[" +
            "{\"_id\":1,\"answer\":\"D\",\"optionA\":\"java\",\"optionB\":\"kotlin\",\"optionC\":\"c++\",\"optionD\":\"x#\",\"q_type\":0,\"title\":\"哪个不是Android开发语言？\"}," +
            "{\"_id\":2,\"answer\":\"A\",\"optionA\":\"Activity\",\"optionB\":\"xml\",\"optionC\":\"layout\",\"optionD\":\"88\",\"q_type\":0,\"title\":\"Android中属于MVC中V层的是？\"}," +
            "{\"_id\":2,\"answer\":\"A\",\"optionA\":\"Activity\",\"optionB\":\"xml\",\"optionC\":\"layout\",\"optionD\":\"88\",\"q_type\":0,\"title\":\"Android中属于MVC中V层的是？\"}," +
            "{\"_id\":2,\"answer\":\"A\",\"optionA\":\"Activity\",\"optionB\":\"xml\",\"optionC\":\"layout\",\"optionD\":\"88\",\"q_type\":0,\"title\":\"Android中属于MVC中V层的是？\"}," +
            "{\"_id\":3,\"answer\":\"B\",\"optionA\":\"Android studio\",\"optionB\":\"txt\",\"optionC\":\"记事本\",\"optionD\":\"eclipse\",\"q_type\":0,\"title\":\"Android开发工具没有哪个？\"}],\"name\":\"aaaaa\"}";

    @Override
    int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    void initView() {
        Gson gson = new Gson();
        JsonQuestBean JsonQuestBean = gson.fromJson(sjson, JsonQuestBean.class);
        fragmentlists = new ArrayList<>();
        List<QuestBean> messages = JsonQuestBean.getMessages();
        for (int i = 0; i < messages.size(); i++) {
            QuestBean questBean = messages.get(i);
            fragmentlists.add(new AnswerFragment(messages.get(i)));
        }
        vp_answer = (ViewPager) findViewById(R.id.vp_answer);
    }

    @Override
    void initData() {
        vp_answer.setAdapter(new MainAdapter(getSupportFragmentManager()));
    }

    @Override
    void initListener() {
        super.initListener();
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
}
