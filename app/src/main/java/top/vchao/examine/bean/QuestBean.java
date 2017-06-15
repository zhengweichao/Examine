package top.vchao.examine.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @ 创建时间: 2017/6/13 on 17:12.
 * @ 描述：greenDao 学习，测试生成数据库
 * @ 作者: 郑卫超 QQ: 2318723605
 */
@Entity
public class QuestBean {
    @Id(autoincrement = true)
    private long id;
    @NotNull
    private int q_type;// 题型：0：判断题 1：选择题
    @NotNull
    private String title;// 问题
    private String optionA;// 选项A
    private String optionB;// 选项B
    private String optionC;// 选项C
    private String optionD;// 选项D
    private String tips;//提示
    private String explain;//解释
    private String answer;// 正确答案
    private String myanswer;// 我的答案

    public QuestBean(long id, int q_type, @NotNull String title, String optionA,
                     String optionB, String optionC, String optionD, String answer,
                     String tips, String explain) {
        this.id = id;
        this.q_type = q_type;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.tips = tips;
        this.explain=explain;
    }

    @Generated(hash = 1018521269)
    public QuestBean(long id, int q_type, @NotNull String title, String optionA,
            String optionB, String optionC, String optionD, String tips,
            String explain, String answer, String myanswer) {
        this.id = id;
        this.q_type = q_type;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.tips = tips;
        this.explain = explain;
        this.answer = answer;
        this.myanswer = myanswer;
    }

    @Generated(hash = 1276854522)
    public QuestBean() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQ_type() {
        return this.q_type;
    }

    public void setQ_type(int q_type) {
        this.q_type = q_type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return this.optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return this.optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return this.optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return this.optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMyanswer() {
        return this.myanswer;
    }

    public void setMyanswer(String myanswer) {
        this.myanswer = myanswer;
    }


}
