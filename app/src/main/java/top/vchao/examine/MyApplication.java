package top.vchao.examine;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.lzy.okgo.OkGo;

import java.util.List;

import top.vchao.examine.bean.DaoMaster;
import top.vchao.examine.bean.DaoSession;
import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.bean.QuestBeanDao;

/**
 * @ 创建时间: 2017/6/10 on 17:36.
 * @ 描述：
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //必须调用初始化
        OkGo.init(this);

        //配置数据库
        setupDatabase();
        Log.i("zwc", "onCreate: ++++++");
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        // 获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
