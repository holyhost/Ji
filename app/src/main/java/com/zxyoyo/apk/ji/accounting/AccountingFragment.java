package com.zxyoyo.apk.ji.accounting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxyoyo.apk.ji.BaseApplication;
import com.zxyoyo.apk.ji.DotViewPagerAdapter;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.GoodsTypeBean;
import com.zxyoyo.apk.ji.accounting.source.ImageLayoutAdapter;
import com.zxyoyo.apk.ji.designview.JiInputView;
import com.zxyoyo.apk.ji.designview.ZzViewPager;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AccountingFragment extends Fragment {

    private View view;
    private FrameLayout fl_container;
    private JiInputView jiInputView;
    private ZzViewPager view_pager;
    private String selectedName;//选中的icon名称
    private List<ImageLayoutAdapter> listAdapters;
    private int currentPage = 0;//当前选中图标的页数，0开始
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accounting, container, false);
        jiInputView = view.findViewById(R.id.input_view);
        view_pager = view.findViewById(R.id.view_pager);
        jiInputView.setListener(new JiInputView.JiInputClickListener() {
            @Override
            public void onClick() {
                PictureSelector.create(AccountingFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .isCamera(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<View> views = new ArrayList<>();
        List<GoodsTypeBean> datas = BaseApplication.getDaoSession().getGoodsTypeBeanDao().loadAll();
        listAdapters = new ArrayList<>();
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
            final ImageLayoutAdapter adapter = new ImageLayoutAdapter(page,getContext(),i);
            recyclerView.setAdapter(adapter);
            listAdapters.add(adapter);
            adapter.setListener(new ImageLayoutAdapter.ImageLayoutClickListener() {
                @Override
                public void onClick(String typeName) {
                    selectedName = typeName;
                    currentPage = adapter.getCurrentIndex();
                    for(int k=0;k<listAdapters.size();k++){
                        if(currentPage!=k)
                            listAdapters.get(k).refreshLayout();
                    }
                }
            });
            views.add(recyclerView);
        }

        view_pager.setAdapter(new DotViewPagerAdapter(getContext(),views));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("life","AccountingFragment-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("life","AccountingFragment-onStop");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("life","AccountingFragment-onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("life","AccountingFragment-onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("life","AccountingFragment-onStart");
    }



    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("life","AccountingFragment-onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("life","AccountingFragment-onAttach");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        jiInputView.setPhoto(selectList.get(0).getPath());

                    }
                    break;
            }
        }
    }
}
