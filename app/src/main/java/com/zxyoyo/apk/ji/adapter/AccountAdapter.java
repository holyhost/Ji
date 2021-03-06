package com.zxyoyo.apk.ji.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.accounting.source.AccountBean;

import java.util.List;

/**
 * time:2019/3/19
 * email:2024212718@qq.com
 * author:hosition
 * website:www.zxyoyo.com
 * -----------------------
 * function describe:
 **/
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<AccountBean> datas;
    private Context context;

    public AccountAdapter(List<AccountBean> datas,Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void refreshData(List<AccountBean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.item_account,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder accountViewHolder, int i) {
        accountViewHolder.tvName.setText(datas.get(i).getDetailType());
        accountViewHolder.tvNumber.setText(datas.get(i).getNumber()+"");
        accountViewHolder.ivIcon.setBackgroundResource(datas.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class AccountViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvNumber;
        ImageView ivIcon;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
