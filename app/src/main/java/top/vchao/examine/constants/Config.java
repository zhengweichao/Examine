package top.vchao.examine.constants;

/**
 * @ 创建时间: 2017/6/16 on 12:56.
 * @ 描述：配置常量
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class Config {
    //是否开启调试模式
    public static final boolean DEBUG = true;
    //    Log 打印的 tag
    public static final String LogTag = "vchao";

    //    考试试卷类型
    public static final String TestType = "kind";
    public static final String TestType_English = "english";
    public static final String TestType_Math = "math";
    public static final String TestType_Chinese = "chinese";

    //    主机 host
    public static final String HOST = "http://192.168.1.145:8080/Examine1/";
    //    登录网址
    public static final String URL_LOGIN = HOST + "LogLet";
    //    注册网址
    public static final String URL_SIGNUP = HOST + "RegLet";
    //    获取问题网址
    public static final String URL_GET_QUESTION = HOST + "GetQuestionLet";

    //    获取试卷题目网址
    public static final String URL_GET_TEST_QUESTION = HOST + "GetTestList";
    public static final String URL_UP_USER_GRADE = HOST + "UpUserGrade";
}