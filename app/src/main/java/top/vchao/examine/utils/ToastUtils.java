package top.vchao.examine.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import top.vchao.examine.MyApplication;
import top.vchao.examine.R;

/**
 * @ Create_time: 2018/4/21 on 9:50.
 * @ description：
 * @ author: vchao  blog: http://blog.csdn.net/zheng_weichao
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 短时间显示  Toast
     *
     * @param sequence
     */
    public static void showShort(CharSequence sequence) {
        try {
            if (toast == null) {
                toast = new Toast(MyApplication.getInstance());
            }
            toast.setGravity(Gravity.BOTTOM, 0, 120);
            View v = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.toast_layout, null);
            TextView tv2 = (TextView) v.findViewById(R.id.tv_toast);
            tv2.setText(sequence);
            toast.setView(v);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            LogUtils.e("  吐司：  " + sequence);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("莫名奇妙的崩溃");
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param sequence
     */
    public static void showLong(CharSequence sequence) {
        try {
            if (toast == null) {
                toast = new Toast(MyApplication.getInstance());
            }
            toast.setGravity(Gravity.BOTTOM, 0, 120);
            View v = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.toast_layout, null);
            TextView tv2 = (TextView) v.findViewById(R.id.tv_toast);
            tv2.setText(sequence);
            toast.setView(v);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            LogUtils.e("  吐司：  " + sequence);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("莫名奇妙的崩溃");
        }

    }

    /**
     * 自定义显示时间
     *
     * @param context
     * @param sequence
     * @param duration
     */
    public static void show(Context context, CharSequence sequence, int duration) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView tv2 = (TextView) v.findViewById(R.id.tv_toast);
        tv2.setText(sequence);
        toast.setView(v);
        toast.setDuration(duration);
        toast.show();
    }

    /**
     * 隐藏toast
     */
    public static void hideToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
