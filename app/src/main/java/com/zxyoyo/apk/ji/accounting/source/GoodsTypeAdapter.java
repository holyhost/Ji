package com.zxyoyo.apk.ji.accounting.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zxyoyo.apk.ji.R;

import java.util.List;

/**
 * 描述
 *
 * @author 创建人 ：zhouxin
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.accounting.source
 * @createTime 创建时间 ：2019/2/15
 * @modifyBy 修改人 ：zhouxin
 * @modifyTime 修改时间 ：2019/2/15
 * @modifyMemo 修改备注：
 */
public class GoodsTypeAdapter extends RecyclerView.Adapter {

    private List<String> itemList;
    private Context context;

    public GoodsTypeAdapter(List<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i<1){
            //banner
            return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main,viewGroup,false));
        }else {
            //正常的子条目
            return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        // 因为又一个 头部 所以 总数+1

        return null == itemList?1:(itemList.size()+1);
    }


    class BannerViewHolder extends RecyclerView.ViewHolder{

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ItemViewerHolder extends RecyclerView.ViewHolder{

        public ItemViewerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}
