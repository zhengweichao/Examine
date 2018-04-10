package top.vchao.examine.utils;

import android.util.Log;

import top.vchao.examine.constants.Config;


/**
 * @ 创建时间: 2017/8/23 on 13:25.
 * @ 描述：log工具类
 */
public class LogUtils {

    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message) {
        if (Config.DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.e(Config.LogTag, createLog(message));
        }
    }

    public static void i(String message) {
        if (Config.DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(Config.LogTag, createLog(message));
        }
    }

}