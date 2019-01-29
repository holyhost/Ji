package com.zxyoyo.apk.ji.designview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zxyoyo.apk.ji.DotViewPagerAdapter;
import com.zxyoyo.apk.ji.R;

import java.util.List;

/**
 * 描述
 *
 * @author 创建人 ：zhouxin
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.designview
 * @createTime 创建时间 ：19/1/29
 * @modifyBy 修改人 ：zhouxin
 * @modifyTime 修改时间 ：19/1/29
 * @modifyMemo 修改备注：
 */
public class ZzViewPager extends ConstraintLayout {

    private ViewPager vp_container;// viewpager
    private ImageView iv_light_dot;// top icon to cover other bottom icon
    private LinearLayout ll_dots;// bottom icon's parent to add icon
    private int defaultColor;
    private int dotDistance;// the distance between two dots;

    public ZzViewPager(@NonNull Context context) {
        super(context);
    }



    public ZzViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.layout_viewpager_dot,this,true);
        iv_light_dot  = findViewById(R.id.iv_light_dot);
        vp_container  = findViewById(R.id.vp_container);
        ll_dots  = findViewById(R.id.ll_dots);

        //拿到控件的自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZzViewPager);
        //用户使用了自定义属性
        if(typedArray!=null){
            defaultColor = typedArray.getColor(R.styleable.ZzViewPager_zzdot_color_default, getResources().getColor(R.color.colorPrimary));
            int selectedColor = typedArray.getColor(R.styleable.ZzViewPager_zzdot_color_selected, getResources().getColor(R.color.colorPrimaryDark));
            Drawable selectedDrawable = typedArray.getDrawable(R.styleable.ZzViewPager_zzdot_drawable_selected);
            if(selectedColor!=0){
                iv_light_dot.setColorFilter(selectedColor);
            }
            if(selectedDrawable!=null){
                iv_light_dot.setImageDrawable(selectedDrawable);
            }
        }

        typedArray.recycle();
    }

    /**
     *
     * @param context
     * @param size 点的个数，与pageview的页数有关
     */
    private void initDots(Context context,int size){
        if(size<2) {
            iv_light_dot.setVisibility(View.GONE);
            return;
        }
        for(int i=0;i<size-1;i++){
            ImageView mOne_dot = new ImageView(context);
            mOne_dot.setImageResource(R.drawable.ic_flower_dot);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 40, 0);
            ll_dots.addView(mOne_dot, layoutParams);
        }
        ImageView mOne_dot = new ImageView(context);
        mOne_dot.setImageResource(R.drawable.ic_flower_dot);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 最后一个不用设置右边距
        layoutParams2.setMargins(0, 0, 0, 0);
        ll_dots.addView(mOne_dot, layoutParams2);
        // 动态获取两个点之间的距离
        ll_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                dotDistance = ll_dots.getChildAt(1).getLeft() - ll_dots.getChildAt(0).getLeft();
                ll_dots.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }


    public void setAdapter(PagerAdapter adapter){
        if(adapter==null) return;
        vp_container.setAdapter(adapter);
        initDots(getContext(),adapter.getCount());
        initScrollEvent();

    }

    private void initScrollEvent() {
        vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
                //position 当前位置，从0开始
                //offset 当前页滑动到下一页百分比，从0到1；
                float leftDistance = dotDistance *(position +offset);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_light_dot.getLayoutParams();
                layoutParams.leftMargin = (int)leftDistance;
                iv_light_dot.setLayoutParams(layoutParams);
                Log.e("scroll","position="+position+"------offset="+offset+"------positionOffsetPixels="+positionOffsetPixels+"-----leftDistance="+leftDistance);
            }

            @Override
            public void onPageSelected(int i) {
                float leftDistance = dotDistance *i;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_light_dot.getLayoutParams();
                layoutParams.leftMargin = (int)leftDistance;
                iv_light_dot.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // 开始滑动viewpager 触发此方法，i＝1；
                // 松开手，触发此方法，i = 2;
                // 滑动结束viewpager 触发此方法，i＝ 0；
                Log.e("scroll","scroll state = "+i);
            }
        });
    }
}
