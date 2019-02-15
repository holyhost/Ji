package com.zxyoyo.apk.ji.accounting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zxyoyo.apk.ji.DotViewPagerAdapter;
import com.zxyoyo.apk.ji.R;
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
        Button btn1 = new Button(getContext());
        btn1.setText("button－1");
        Button btn2 = new Button(getContext());
        btn2.setText("button－2");

        Button btn3 = new Button(getContext());
        btn3.setText("button－3");

        Button btn4 = new Button(getContext());
        btn4.setText("button－4");

        List<View> views = new ArrayList<>();
        views.add(btn1);
        views.add(btn2);
        views.add(btn3);
        views.add(btn4);
        view_pager.setAdapter(new DotViewPagerAdapter(getContext(),views));
    }
}
