package com.zxyoyo.apk.ji.designview;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxyoyo.apk.ji.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 描述
 *
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.designview
 * @createTime 创建时间 ：19/1/24
 * @modifyTime 修改时间 ：19/1/24
 * @modifyMemo 修改备注：
 */
public class JiInputView implements View.OnClickListener{
    private Context context;
    private double number;//输入数值
    private List<String> filePathes;// 文件路径集合
    private List<String> characters;// 文件路径集合
    private View rootView;// this JiInputView layout

    private Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_delete,btn_finish,btn_dot;
    private ImageView iv_photo;//图片选择
    private EditText et_number;// 输入的数值


    /**
     * 构造器
     * @param context
     */
    public JiInputView(Context context, ViewGroup viewGroup){
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_input_view, viewGroup, false);
        viewGroup.addView(rootView);
        onInitView();
        onInitData();
    }


    public JiInputView(Context context) {
        this.context = context;
    }

    /**
     * 界面初始化
     */
    private void onInitView(){
        btn_0 = rootView.findViewById(R.id.btn_0);
        btn_1 = rootView.findViewById(R.id.btn_1);
        btn_2 = rootView.findViewById(R.id.btn_2);
        btn_3 = rootView.findViewById(R.id.btn_3);
        btn_4 = rootView.findViewById(R.id.btn_4);
        btn_5 = rootView.findViewById(R.id.btn_5);
        btn_6 = rootView.findViewById(R.id.btn_6);
        btn_7 = rootView.findViewById(R.id.btn_7);
        btn_8 = rootView.findViewById(R.id.btn_8);
        btn_9 = rootView.findViewById(R.id.btn_9);
        btn_finish = rootView.findViewById(R.id.btn_finish);
        btn_dot = rootView.findViewById(R.id.btn_dot);
        btn_delete = rootView.findViewById(R.id.btn_delete);
        iv_photo = rootView.findViewById(R.id.iv_photo);
        et_number = rootView.findViewById(R.id.et_number);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
    }

    public void onInitData(){
        filePathes = new ArrayList<>();
        characters = new ArrayList<>();

    }

    /**
     * 界面销毁
     */

    public void onDestroy(){
        try{
            ((Activity)context).onBackPressed();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(context,"关闭页面失败",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_0:
                changeText(0);
                break;
            case R.id.btn_1:
                changeText(1);
                break;
            case R.id.btn_2:
                changeText(2);
                break;
            case R.id.btn_3:
                changeText(3);
                break;
            case R.id.btn_4:
                changeText(4);
                break;
            case R.id.btn_5:
                changeText(5);
                break;
            case R.id.btn_6:
                changeText(6);
                break;
            case R.id.btn_7:
                changeText(7);
                break;
            case R.id.btn_8:
                changeText(8);
                break;
            case R.id.btn_9:
                changeText(9);
                break;
            case R.id.btn_dot:
                changeText(".");
                break;
            case R.id.btn_delete:
                if(characters.size()>0){
                    characters.remove(characters.size()-1);
                }
                et_number.setText(getResult());
                break;
            case R.id.iv_photo:
                break;
            case R.id.btn_finish:
                onDestroy();
                break;
        }
    }

    private void changeText(String text){
        if(checkInput(text)) return;
        characters.add(text);
        et_number.setText(getResult());
    }

    private void changeText(int text){
        changeText(text+"");
    }

    private boolean checkInput(String input){
        if(characters == null) {
            characters = new ArrayList<>();
        }
        //第一个字符不能是小数点
        if(characters.size()<1&&TextUtils.equals(".",input)) return true;

        //已经输入了一个点后不允许再次输入一个点
        if(characters.contains(".")&& TextUtils.equals(".",input)) return true;
        // 小数点后最多两位数
        if(characters.contains(".")&&(characters.size()-characters.indexOf("."))>2) return true;
        //0开头，只能接小数点，输入其他数字无效
        if(characters.size()>1) return false;// 已经有0，点，了就直接跳过
        if(characters.size()>0&&TextUtils.equals(characters.get(0),"0")&& !TextUtils.equals(".",input)) return true;

        return false;
    }

    private String getResult(){
        if(characters == null || characters.size()<1) return "";
        String result="";
        for(String s:characters){
            result = result+s;
        }
        return result;
    }

}
