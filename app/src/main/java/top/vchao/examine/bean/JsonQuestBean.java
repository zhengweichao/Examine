package top.vchao.examine.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * @ 创建时间: 2017/6/13 on 17:13.
 * @ 描述：
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class JsonQuestBean {

    private int age = 81;
    private String name = "aaaaa";
    private List<QuestBean> messages;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestBean> getMessages() {
        return messages;
    }

    public void setMessages(List<QuestBean> messages) {
        this.messages = messages;
    }

    public JsonQuestBean() {
        this.messages = new ArrayList<QuestBean>() {
            {
                QuestBean qu0 = new QuestBean(7,0,"a33","8w8a","a","c","d","a8");
                QuestBean qu1 = new QuestBean(47,0,"a45","8fc8a","a","k","88","aa");
                QuestBean qu2 = new QuestBean(87,0,"a33","88va","87","c","d","aa");

                add(qu0);
                add(qu1);
                add(qu2);
            }
        };
    }
}
