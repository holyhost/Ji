package com.zxyoyo.apk.ji.designview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.zxyoyo.apk.ji.BaseApplication;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.GoodsTypeBean;

/**
 * 描述
 *
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.designview
 * @createTime 创建时间 ：2019/2/18
 */
public class AddTypeDialogFragment extends DialogFragment {



    private EditText etName;
    private Button btnCancel;
    private Button btnComplete;

    public static AddTypeDialogFragment getInstance(){
        AddTypeDialogFragment addDialog = new AddTypeDialogFragment();
        addDialog.setCancelable(true);
        return addDialog;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_fragment_add_type,container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnComplete = view.findViewById(R.id.btn_complete);
        etName = view.findViewById(R.id.et_input_goods_name);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseApplication.getDaoSession().getGoodsTypeBeanDao().insert(new GoodsTypeBean(etName.getText().toString(),0));
                dismiss();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("life","AddTypeDialogFragment-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("life","AddTypeDialogFragment-onStop");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("life","AddTypeDialogFragment-onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("life","AddTypeDialogFragment-onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("life","AddTypeDialogFragment-onStart");
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.e("life","AddTypeDialogFragment-onDismiss");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("life","AddTypeDialogFragment-onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("life","AddTypeDialogFragment-onAttach");

    }
}
