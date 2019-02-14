package com.zxyoyo.apk.ji;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import database.DaoMaster;
import database.DaoSession;

/**
 * 描述
 *
 * @author 创建人 ：zhouxin
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji
 * @createTime 创建时间 ：2019/2/14
 * @modifyBy 修改人 ：zhouxin
 * @modifyTime 修改时间 ：2019/2/14
 * @modifyMemo 修改备注：
 */
public class BaseApplication extends Application {

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // init database
        initDataBase();
    }

    private void initDataBase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"ji.db");
        // 获取可写的数据库
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        // 获取 dao 数据库对象
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        // 获取 dao 数据库对象管理者
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return  mDaoSession;
    }
}
