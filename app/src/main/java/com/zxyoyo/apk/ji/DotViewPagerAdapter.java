package com.zxyoyo.apk.ji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 描述
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji
 * @createTime 创建时间 ：19/1/29
 * @modifyBy 修改人 ：zhouxin
 * @modifyTime 修改时间 ：19/1/29
 * @modifyMemo 修改备注：
 */
public class DotViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> views;

    public DotViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    public DotViewPagerAdapter(Context context, List<View> views) {
        this.context = context;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null ?0:views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }
}
