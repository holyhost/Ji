package com.zxyoyo.apk.ji.designview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.zxyoyo.apk.ji.R;

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
                //todo add a type to database and refresh view

            }
        });
        return view;
    }
}
