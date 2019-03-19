package com.zxyoyo.apk.ji.accounting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxyoyo.apk.ji.BaseApplication;
import com.zxyoyo.apk.ji.DotViewPagerAdapter;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.AccountBean;
import com.zxyoyo.apk.ji.accounting.source.GoodsTypeBean;
import com.zxyoyo.apk.ji.accounting.source.ImageLayoutAdapter;
import com.zxyoyo.apk.ji.designview.JiInputView;
import com.zxyoyo.apk.ji.designview.ZzInputDialog;
import com.zxyoyo.apk.ji.designview.ZzViewPager;
import com.zxyoyo.apk.ji.utils.DateTypeChangeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import cn.qqtheme.framework.picker.DatePicker;
import database.GoodsTypeBeanDao;


import static android.app.Activity.RESULT_OK;

public class AccountingFragment extends Fragment {

    private View view;
    private TextView tvDate;//日期
    private TextView tvDescribe;//备注
    private TextView tv_del_add;//收入与支出
    private FrameLayout fl_container;
    private JiInputView jiInputView;
    private ZzViewPager view_pager;
    private String selectedName;//选中的icon名称
    private String photoPath;//图片路径
    private List<ImageLayoutAdapter> listAdapters;
    private int selectIcon;// 选择类型的icon
    private int currentPage = 0;//当前选中图标的页数，0开始
    private int year;// 年
    private int month;// 月
    private int day;// 日

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
        tvDate = view.findViewById(R.id.tv_date);
        tvDescribe = view.findViewById(R.id.tv_describe);
        tv_del_add = view.findViewById(R.id.tv_del_add);
        initView();
        return view;
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        year = Integer.parseInt(String.valueOf(calendar.get(Calendar.YEAR)));
        month = Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH)))+1;
        day = Integer.parseInt(String.valueOf(calendar.get(Calendar.DATE)));
        tvDate.setText((month<10?("0"+month):month)+"-"+day);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePicker picker = new DatePicker(getActivity());
                picker.setCanceledOnTouchOutside(true);
                picker.setUseWeight(true);
//                picker.setTopPadding(ConvertUtils.toPx(this, 10));
                picker.setRangeEnd(year+3, 1, 11);
                picker.setRangeStart(year-3, 8, 29);
                picker.setSelectedItem(year, month, day);
                picker.setResetWhileWheel(false);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String y, String m, String d) {
                        if(TextUtils.equals(year+"",y)){
                            tvDate.setText(m+"-"+d);
                        }else {
                            tvDate.setText(y+"-"+m+"-"+d);

                        }
                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
                    }
                });
                picker.show();

            }
        });
        tvDescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZzInputDialog dialog = new ZzInputDialog(getContext(), "备注");
                dialog.setOnInputResultListener(new ZzInputDialog.OnInputResultListener() {
                    @Override
                    public void onGetInputResult(String inputText) {
                        Toast.makeText(getContext(),inputText,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        jiInputView.setListener(new JiInputView.JiInputClickListener() {
            @Override
            public void onImageClick() {
                PictureSelector.create(AccountingFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .isCamera(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void onComplete(){
                saveData();
            }
        });
        tv_del_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(tv_del_add.getText().toString(), getString(R.string.ji_common_expense))) {
                    tv_del_add.setText(getString(R.string.ji_common_income));
                }else{
                    tv_del_add.setText(getString(R.string.ji_common_expense));
                }
            }
        });
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
                public void onClick(GoodsTypeBean bean) {
                    selectedName = bean.getName();
                    selectIcon = bean.getIcon();
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
                        String picPath = selectList.get(0).getPath();
                        photoPath = picPath;
                        jiInputView.setPhoto(picPath);

                    }
                    break;
            }
        }
    }

    /**
     * 保存当前数据
     */
    private void saveData(){
        AccountBean bean = new AccountBean();
        bean.setType(TextUtils.equals(tv_del_add.getText().toString(), getString(R.string.ji_common_expense))?0:1);
        bean.setNumber(jiInputView.getValue());
        bean.setDetailType(selectedName);
        bean.setPhoto(photoPath);
        bean.setDescription(tvDescribe.getText().toString());
        bean.setIcon(selectIcon);
        try {
            bean.setTime(DateTypeChangeUtil.stringToLong(year+""+month+""+day,DateTypeChangeUtil.DATE_FORMATE_YMD));
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            bean.setTime(System.currentTimeMillis());
            BaseApplication.getDaoSession().getAccountBeanDao().insert(bean);
        }


    }
}
