package com.zxyoyo.apk.ji.designview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.zxyoyo.apk.ji.MainActivity;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.AccountingActivity;
import com.zxyoyo.apk.ji.adapter.TabTitleAdapter;

import java.util.List;

public abstract class BasePagerActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private List<Fragment> mFragmentList;// 界面：
    private String[] mTitles;// 标题
    private TabLayout tl_indicator;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        toolbar = findViewById(R.id.toolbar);
        tl_indicator = findViewById(R.id.tl_indicator);
        viewPager = findViewById(R.id.view_pager);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BasePagerActivity.this, AccountingActivity.class));
            }
        });
        mFragmentList = initFragments();
        mTitles = initTitles();
        initView();
        setSupportActionBar(toolbar);


    }

    /**
     * 初始化界面，填充fragment
     * @return  填充的界面
     */
    public abstract List<Fragment> initFragments();

    /**
     * 初始化标题；
     * 1. 可以没有标题；
     * 2. 只有一个标题的时候，显示到toolbar上面；
     * 3. 多个标题时，显示到tablayout上
     * @return 标题
     */
    public abstract String[] initTitles();

    public void initView(){
        checkDatas();
        viewPager.setAdapter(new TabTitleAdapter(getSupportFragmentManager(),mFragmentList,mTitles));
        if(mTitles.length>1){
            tl_indicator.setupWithViewPager(viewPager);

        }else {
            tl_indicator.setVisibility(View.GONE);
            toolbar.setTitle(mTitles[0]);
        }
    }

    private void checkDatas(){
        // 界面为空，不执行
        if(null==mFragmentList||mFragmentList.size()<1) return;
        // 标题为空，初始化标题，为空
        if(null == mTitles){
            mTitles = new String[mFragmentList.size()];
            for(int i=0;i<mFragmentList.size();i++) mTitles[i] = "";
        }
        if(mTitles.length == 1){
            tl_indicator.setVisibility(View.GONE);
            toolbar.setTitle(mTitles[0]);
            setSupportActionBar(toolbar);

        }
        if(mTitles.length>mFragmentList.size()){
            // 标题与界面不对应，以界面数量为标准，填充“”或删减标题后面多余数量
            String[] newTitles = new String[mFragmentList.size()];
            for(int i=0;i<mFragmentList.size();i++) newTitles[i] = mTitles[i];
            mTitles = newTitles;
        }
        if(mTitles.length<mFragmentList.size()){
            String[] newTitles = new String[mFragmentList.size()];
            for(int i=0;i<mFragmentList.size();i++) {

                if(i<mTitles.length){
                    newTitles[i] = mTitles[i];
                }else {
                    newTitles[i] = "";
                }
            }
            mTitles = newTitles;

        }
    }
}
