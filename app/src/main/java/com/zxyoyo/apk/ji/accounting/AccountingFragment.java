package com.zxyoyo.apk.ji.accounting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxyoyo.apk.ji.DotViewPagerAdapter;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.GoodsTypeBean;
import com.zxyoyo.apk.ji.accounting.source.ImageLayoutAdapter;
import com.zxyoyo.apk.ji.designview.JiInputView;
import com.zxyoyo.apk.ji.designview.ZzViewPager;

import java.util.ArrayList;
import java.util.List;

public class AccountingFragment extends Fragment {

    private View view;
    private FrameLayout fl_container;

    private ZzViewPager view_pager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accounting, container, false);
        container = view.findViewById(R.id.fl_container);
        view_pager = view.findViewById(R.id.view_pager);

        new JiInputView(getContext(),container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<View> views = new ArrayList<>();
        List<GoodsTypeBean> datas = new ArrayList<>();
        datas.add(new GoodsTypeBean("购物",R.drawable.goods_type_shop));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("购物",R.drawable.goods_type_shop));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("购物",R.drawable.goods_type_shop));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("购物",R.drawable.goods_type_shop));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("购物",R.drawable.goods_type_shop));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        datas.add(new GoodsTypeBean("汽车",R.drawable.goods_type_car));
        for(int i=0;i<datas.size()/10+1;i++){
            GridLayoutManager manager = new GridLayoutManager(getContext(),5);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            RecyclerView recyclerView = new RecyclerView(getContext());
            recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            recyclerView.setLayoutManager(manager);
            List<GoodsTypeBean> page = new ArrayList<>();
            for(int j=0;j<((i<(datas.size()/10))?10:(datas.size()%10));j++){
                page.add(datas.get(j+10*i));
            }
            ImageLayoutAdapter adapter = new ImageLayoutAdapter(page,getContext());
            recyclerView.setAdapter(adapter);
            views.add(recyclerView);
        }

        view_pager.setAdapter(new DotViewPagerAdapter(getContext(),views));
    }
}
