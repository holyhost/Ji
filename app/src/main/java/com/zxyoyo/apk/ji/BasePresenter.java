package com.zxyoyo.apk.ji;


public interface BasePresenter {
    void subscribe();// 订阅 用于绑定view
    void unsubscribe();// 解除与view的绑定
}
