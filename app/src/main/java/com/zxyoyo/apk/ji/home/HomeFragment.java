package com.zxyoyo.apk.ji.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zxyoyo.apk.ji.BaseApplication;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.AccountBean;
import com.zxyoyo.apk.ji.adapter.AccountAdapter;
import com.zxyoyo.apk.ji.designview.BaseListFragment;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

import butterknife.BindView;

/**
 * time:2019/3/19
 * email:2024212718@qq.com
 * author:hosition
 * website:www.zxyoyo.com
 * -----------------------
 * function describe:
 **/
public class HomeFragment extends BaseListFragment {
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {
        List<AccountBean> accounts = BaseApplication.getDaoSession().getAccountBeanDao().loadAll();
        Log.e("size",accounts.size()+"");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        recyclerView.setAdapter(new AccountAdapter(accounts,getContext()));
    }

    @Override
    protected void loadData() {
        super.loadData();

    }
}
