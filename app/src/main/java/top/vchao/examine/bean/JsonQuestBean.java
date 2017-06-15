package top.vchao.examine.bean;

import java.util.List;

/**
 * @ 创建时间: 2017/6/15 on 0:51.
 * @ 描述：
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class JsonQuestBean {

    /**
     * status : ok
     * code : 200
     * messages : [{"id":1,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":2,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":3,"q_type":1,"title":"1","optionA":"title","optionB":"选项A","optionC":"选项B","optionD":"选项C","answer":"tips","tips":"选项D","explain":"answer"},{"id":4,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":5,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"}]
     */

    private String status;
    private String code;
    private List<QuestBean> messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<QuestBean> getMessages() {
        return messages;
    }

    public void setMessages(List<QuestBean> messages) {
        this.messages = messages;
    }


}
