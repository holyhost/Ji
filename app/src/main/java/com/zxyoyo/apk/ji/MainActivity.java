package com.zxyoyo.apk.ji;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private ViewPager vp_container;

    private LinearLayout ll_dots;
    private int dotDistance;
    private ImageView ivLightDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        vp_container = findViewById(R.id.vp_container);
        ll_dots = findViewById(R.id.in_ll);
        ivLightDots = findViewById(R.id.iv_light_dots);
        addDots();
        Button btn1 = new Button(this);
        btn1.setText("button－1");
        Button btn2 = new Button(this);
        btn2.setText("button－2");

        Button btn3 = new Button(this);
        btn3.setText("button－3");

        Button btn4 = new Button(this);
        btn4.setText("button－4");

        List<View> views = new ArrayList<>();
        views.add(btn1);
        views.add(btn2);
        views.add(btn3);
        views.add(btn4);
        initPager(views);
    }

    private void initPager(List<View> views) {
        vp_container.setAdapter(new DotViewPagerAdapter(views));
        vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
                //position 当前位置，从0开始
                //offset 当前页滑动到下一页百分比，从0到1；
                float leftDistance = dotDistance *(position +offset);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivLightDots.getLayoutParams();
                layoutParams.leftMargin = (int)leftDistance;
                ivLightDots.setLayoutParams(layoutParams);
                Log.e("scroll","position="+position+"------offset="+offset+"------positionOffsetPixels="+positionOffsetPixels+"-----leftDistance="+leftDistance);
            }

            @Override
            public void onPageSelected(int i) {
                float leftDistance = dotDistance *i;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivLightDots.getLayoutParams();
                layoutParams.leftMargin = (int)leftDistance;
                ivLightDots.setLayoutParams(layoutParams);
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

    private void addDots(){
        ImageView mOne_dot = new ImageView(this);
        mOne_dot.setImageResource(R.drawable.ic_flower_dot);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 40, 0);
        ll_dots.addView(mOne_dot, layoutParams);
        mOne_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp_container.setCurrentItem(0,true);
            }
        });
        ImageView mTwo_dot = new ImageView(this);
        mTwo_dot.setImageResource(R.drawable.ic_flower_dot);
        ll_dots.addView(mTwo_dot, layoutParams);
        mTwo_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp_container.setCurrentItem(1,true);
            }
        });
        ImageView mThree_dot = new ImageView(this);
        mThree_dot.setImageResource(R.drawable.ic_flower_dot);
        ll_dots.addView(mThree_dot, layoutParams);

        ImageView mFour_dot = new ImageView(this);
        mFour_dot.setImageResource(R.drawable.ic_flower_dot);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 最后一个不用设置右边距
        layoutParams2.setMargins(0, 0, 0, 0);
        ll_dots.addView(mFour_dot, layoutParams2);

        // 动态获取两个点之间的距离
        ll_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                dotDistance = ll_dots.getChildAt(1).getLeft() - ll_dots.getChildAt(0).getLeft();
                ll_dots.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }
}
