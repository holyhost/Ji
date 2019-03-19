package com.zxyoyo.apk.ji.designview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * time:2019/3/19
 * email:2024212718@qq.com
 * author:hosition
 * website:www.zxyoyo.com
 * -----------------------
 * function describe:
 **/
public abstract class BaseListFragment extends Fragment {
    protected View parentView;
    protected  boolean isPrepared;
    protected boolean isVisiable;
    protected Activity activity;
    protected Context context;
    private Unbinder bind;
    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void finishCreateView(Bundle state);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("BaseFragment-onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("BaseFragment-onActivityCreated");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.e("BaseFragment-onViewCreated");
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e("BaseFragment-onCreateView");
        activity = getActivity();
        parentView = inflater.inflate(getLayoutResId(),container,false);
        return parentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("BaseFragment-onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("BaseFragment-onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("BaseFragment-onDestroy");
        bind.unbind();
    }

    /**
     * 数据懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e("BaseFragment","setUserVisibleHint-"+isVisibleToUser);
        if(getUserVisibleHint()){
            isVisiable = true;
            onVisiable();
        }else {
            isVisiable  = false;
            onInvisiable();
        }
    }

    /**
     * 视图可见的时候执行相关操作
     */
    protected void onVisiable(){
        loadData();
    }

    /**
     * 视图不可见的时候执行相关操作
     */
    protected void onInvisiable(){

    }

    /**
     * 加载数据
     */
    protected void loadData(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        Logger.e("BaseFragment","onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
        Logger.e("BaseFragment","onDetach");
    }
}
