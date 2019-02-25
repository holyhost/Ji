package com.zxyoyo.apk.ji;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import com.zxyoyo.apk.ji.accounting.source.AccountBean;
import com.zxyoyo.apk.ji.accounting.source.GoodsTypeBean;
import com.zxyoyo.apk.ji.login.source.UserBean;

import database.AccountBeanDao;
import database.DaoMaster;
import database.DaoSession;
import database.GoodsTypeBeanDao;

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
        if(mDaoSession.getUserBeanDao().loadAll().size()<1){
            //第一次使用，创建一个默认用户
            mDaoSession.getUserBeanDao().insert(new UserBean());
        }
        if(mDaoSession.getGoodsTypeBeanDao().loadAll().size()<1){
            //没有数据，填充数据
            initData();
        }
    }

    /**
     * 初始化数据字典
     * @return
     */
    private void initData(){
        String[] names = new String[]{
                getResources().getString(R.string.goods_type_shop),
                getResources().getString(R.string.goods_type_common),
                getResources().getString(R.string.goods_type_car),
                getResources().getString(R.string.goods_type_correspond),
                getResources().getString(R.string.goods_type_housing),
                getResources().getString(R.string.goods_type_traffic),
                getResources().getString(R.string.goods_type_travel),
                getResources().getString(R.string.goods_type_donate),
                getResources().getString(R.string.goods_type_sport),
                getResources().getString(R.string.goods_type_study),
                getResources().getString(R.string.goods_type_maintain),
                getResources().getString(R.string.goods_type_gift),
                getResources().getString(R.string.goods_type_financing),
                getResources().getString(R.string.goods_type_lottery),
                getResources().getString(R.string.goods_type_eating),
                getResources().getString(R.string.goods_type_pet),
                getResources().getString(R.string.goods_type_entertainment),
                getResources().getString(R.string.goods_type_book)
        };
        int[] icons = new int[]{
                R.drawable.goods_type_shop,
                R.drawable.goods_type_car,
                R.drawable.goods_type_pet,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
                R.drawable.goods_type_shop,
        };
        GoodsTypeBeanDao goodsTypeBeanDao = mDaoSession.getGoodsTypeBeanDao();
        for(int i=0;i<names.length;i++){
            try {
                goodsTypeBeanDao.insert(new GoodsTypeBean(i,names[i],0, System.currentTimeMillis(),icons[i]));

            }catch (Exception e){
                Log.e("error",e.getMessage());
            }
        }
    }

    public static DaoSession getDaoSession(){
        return  mDaoSession;
    }
}
