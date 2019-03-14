package com.zxyoyo.apk.ji.designview;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zxyoyo.apk.ji.R;

/**
 * time:2019/3/14
 * email:2024212718@qq.com
 * author:hosition
 * website:www.zxyoyo.com
 * -----------------------
 * function describe: 用于输入的弹窗
 **/
public class ZzInputDialog {

    private AlertDialog mDialog;
    private String mTitle="Title";// 标题
    private String mInputText="";// 输入的内容

    /**
     *
     * @param context 上下文
     * @param title 标题
     */
    public ZzInputDialog(Context context,String title){
        this(context,title,"");
    }

    /**
     *
     * @param context 上下文
     * @param title 标题
     * @param inputText 输入的内容
     */
    public ZzInputDialog(Context context,String title,String inputText){
        this.mTitle = title;
        this.mInputText = inputText;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_text_field, null);
        initDialogView(view);
        mDialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        mDialog.show();
    }
    public void show(){
        if(mDialog!=null)
            mDialog.show();
    }
    public void dismiss(){
        if(mDialog!=null)
        mDialog.dismiss();
    }

    public void setmCancelable(boolean cancelable) {
        if(mDialog!=null){
            mDialog.setCancelable(cancelable);
        }
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmInputText() {
        return mInputText;
    }

    public void setmInputText(String mInputText) {
        this.mInputText = mInputText;
    }

    private void initDialogView(View view) {
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        TextView tvTitle = view.findViewById(R.id.tv_dialog_title);
        Button btnComplete = view.findViewById(R.id.btn_complete);
        final EditText etName = view.findViewById(R.id.et_input_describe);
        if(!TextUtils.isEmpty(mInputText)){
            etName.setText(mInputText);
        }
        tvTitle.setText(mTitle);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDialog!=null)
                    dismiss();
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onGetInputResult(etName.getText().toString());
                }
                if(mDialog!=null)
                    dismiss();
            }
        });
    }
    private OnInputResultListener mListener;

    public void setOnInputResultListener(OnInputResultListener listener) {
        this.mListener = listener;
    }

    public interface OnInputResultListener{
        void onGetInputResult(String inputText);//获取返回值
    }
}
