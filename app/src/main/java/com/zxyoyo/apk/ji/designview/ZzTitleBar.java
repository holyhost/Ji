package com.zxyoyo.apk.ji.designview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxyoyo.apk.ji.R;

/**
 * 描述
 *  自定义标题栏
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.designview
 * @createTime 创建时间 ：19/1/30

 * @modifyTime 修改时间 ：19/1/30
 * @modifyMemo 修改备注：
 */
public class ZzTitleBar extends ConstraintLayout {

    private ImageView iv_zz_back,iv_zz_more;
    private TextView tv_zz_title_center,tv_zz_title_left;//左边，中间的标题，两者只能存在一个
    private boolean titleCenter;
    private OnMoreClickListener moreClickListener;
    private OnBackClickListener backClickListener;

    public ZzTitleBar(Context context) {
        super(context);
    }

    public ZzTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
        initEvent(context);
    }

    private void initEvent(final Context context) {
        iv_zz_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null == backClickListener){
                    ((Activity)context).onBackPressed();
                }else {
                    backClickListener.onBackClick();
                }
            }
        });

        iv_zz_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!=moreClickListener){
                    moreClickListener.onMoreClick();
                }
            }
        });
    }


    private void initView(Context context,AttributeSet attrs){

//        加载布局
        LayoutInflater.from(context).inflate(R.layout.layout_title_bar,this,true);
        iv_zz_back = findViewById(R.id.iv_zz_back);
        iv_zz_more = findViewById(R.id.iv_zz_more);
        tv_zz_title_center = findViewById(R.id.tv_zz_title_center);
        tv_zz_title_left = findViewById(R.id.tv_zz_title_left);
//        初始化属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZzTitleBar);
        if(typedArray!=null && typedArray.length()>0){

            // 标题相关属性
            titleCenter = typedArray.getBoolean(R.styleable.ZzTitleBar_zz_title_bar_title_center, true);
            int titleSize = typedArray.getInteger(R.styleable.ZzTitleBar_zz_title_bar_title_size, 18);
            String titleText = typedArray.getString(R.styleable.ZzTitleBar_zz_title_bar_text);
            int textColor = typedArray.getColor(R.styleable.ZzTitleBar_zz_title_bar_title_color, getResources().getColor(R.color.colorWhite));
            initTitle(titleText,titleCenter,titleSize,textColor);


            // 返回箭头相关属性
            int arrowColor = typedArray.getColor(R.styleable.ZzTitleBar_zz_title_bar_arrow_color, -1);
            boolean arrowVisible = typedArray.getBoolean(R.styleable.ZzTitleBar_zz_title_bar_arrow_visible, true);
            Drawable arrowDrawable = typedArray.getDrawable(R.styleable.ZzTitleBar_zz_title_bar_arrow_drawable);
            initArrowLeft(arrowVisible,arrowColor,arrowDrawable);

            // 右边更多按钮
            int moreColor = typedArray.getColor(R.styleable.ZzTitleBar_zz_title_bar_more_color, -1);
            boolean moreVisible = typedArray.getBoolean(R.styleable.ZzTitleBar_zz_title_bar_more_visible, false);
            Drawable moreDrawable = typedArray.getDrawable(R.styleable.ZzTitleBar_zz_title_bar_more_drawable);
            initMoreRight(moreVisible,moreColor,moreDrawable);


        }



    }

    /**
     * 初始化 标题
     * @param text 标题名称
     * @param isCenter 标题是否居中／居左
     * @param textSize 文字大小
     * @param textColor 文字颜色
     */
    private void initTitle(String text,boolean isCenter,int textSize,int textColor){
        if (isCenter){
            tv_zz_title_center.setVisibility(View.VISIBLE);
            tv_zz_title_left.setVisibility(View.GONE);
            tv_zz_title_center.setTextSize(textSize);
            tv_zz_title_center.setText(text);
            tv_zz_title_center.setTextColor(textColor);
        }else {
            tv_zz_title_center.setVisibility(View.GONE);
            tv_zz_title_left.setVisibility(View.VISIBLE);
            tv_zz_title_left.setTextSize(textSize);
            tv_zz_title_left.setText(text);
            tv_zz_title_left.setTextColor(textColor);
        }
    }

    private void initTitlePosition(){
        if (titleCenter){
            tv_zz_title_center.setVisibility(View.VISIBLE);
            tv_zz_title_left.setVisibility(View.GONE);
        }else {
            tv_zz_title_center.setVisibility(View.GONE);
            tv_zz_title_left.setVisibility(View.VISIBLE);
        }
    }
    private void setTitleBarSize(int size){
        if(titleCenter){
            tv_zz_title_center.setTextSize(size);
        }else {
            tv_zz_title_left.setTextSize(size);
        }
    }

    private void setTitleBarText(String text){
        if(titleCenter){
            tv_zz_title_center.setText(text);
        }else {
            tv_zz_title_left.setText(text);
        }
    }
    private void setTitleBarTextColor(int color){
        if(titleCenter){
            tv_zz_title_center.setTextColor(color);
        }else {
            tv_zz_title_left.setTextColor(color);
        }
    }

    /**
     * 初始化 左边返回箭头
     * @param visible 是否可见 默认可见
     * @param color 图标颜色
     * @param drawable 图标icon
     */
    private void initArrowLeft(boolean visible, int color,Drawable drawable){
        if(visible){
            iv_zz_back.setVisibility(VISIBLE);
            if(color!=-1){
                iv_zz_back.setColorFilter(color);
            }
            if(null!= drawable){
                iv_zz_back.setImageDrawable(drawable);
            }
        }else {
            iv_zz_back.setVisibility(GONE);
        }
    }

    /**
     * 初始化 右边更多按钮
     * @param visible 是否可见 默认不可见
     * @param color 图标颜色
     * @param drawable 图标icon
     */
    private void initMoreRight(boolean visible, int color,Drawable drawable){
        if(visible){
            iv_zz_more.setVisibility(VISIBLE);
            if(color>0){
                iv_zz_more.setColorFilter(color);
            }
            if(null!= drawable){
                iv_zz_more.setImageDrawable(drawable);
            }
        }else {
            iv_zz_more.setVisibility(GONE);
        }
    }

    /**
     * 更多  按钮 点击接口
     */
    public interface OnMoreClickListener{
        void onMoreClick();
    }

     /**
     * 返回  按钮 点击接口
     */
    public interface OnBackClickListener{
        void onBackClick();
    }




    public void setMoreClickListener(OnMoreClickListener moreClickListener) {
        this.moreClickListener = moreClickListener;
    }

    public void setBackClickListener(OnBackClickListener backClickListener) {
        this.backClickListener = backClickListener;
    }
}
