package com.zxyoyo.apk.ji.home;

import android.support.v4.app.Fragment;

import com.zxyoyo.apk.ji.designview.BasePagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * time:2019/3/16
 * email:2024212718@qq.com
 * author:hosition
 * website:www.zxyoyo.com
 * -----------------------
 * function describe: 主页
 **/
public class HomeActivity extends BasePagerActivity {
    @Override
    public List<Fragment> initFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        return list;
    }

    @Override
    public String[] initTitles() {
        return new String[]{"账本","账单"};
    }
}
