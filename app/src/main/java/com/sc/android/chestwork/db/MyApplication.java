package com.sc.android.chestwork.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * 自定义Application 由于这个类只在app开启的时候执行一次
 * 一般会在这里做一些 全局的初始化设置
 */
public class MyApplication extends Application {
    private static String DB_NAME = "ClothesFactory02.db";
    private static DaoSession daoSession;

    /**
     * 获取DaoSession 通过这个DaoSession就能获取到其他各个表的所对应的dao了
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    /**
     * 初始化数据库
     */
    private void initDb() {
        //创建一个 College1.db的数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}