package com.zxyoyo.apk.ji.accounting.source;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.designview.AddTypeDialogFragment;

import java.util.List;
import java.util.Map;

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
public class ImageLayoutAdapter extends RecyclerView.Adapter<ImageLayoutAdapter.ItemViewerHolder> {

    private List<GoodsTypeBean> data;
    private Context context;
    // 条目的最大数值
    public static  final  int MAX_COUNT = 10;
    public ImageLayoutAdapter(List<GoodsTypeBean> data, Context context) {
        this.data = data;
        this.context = context;
        if(data!=null&&data.size()<10){
            this.data.add(new GoodsTypeBean(context.getResources().getString(R.string.goods_type_add),R.drawable.goods_type_add));
        }

    }

    @NonNull
    @Override
    public ImageLayoutAdapter.ItemViewerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ItemViewerHolder(LayoutInflater.from(context).inflate(R.layout.item_image_text,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final ImageLayoutAdapter.ItemViewerHolder viewHolder, int i) {
        GoodsTypeBean item = data.get(i);
        int icon = item.getIcon();
        if(icon>0){
            viewHolder.ivIcon.setImageDrawable(context.getResources().getDrawable(icon));
        }
        final String iconName = item.getName();
        viewHolder.tvName.setText(iconName);
        viewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(iconName,context.getResources().getString(R.string.goods_type_add))){
                    AddTypeDialogFragment dialogFragment = AddTypeDialogFragment.getInstance();
                    dialogFragment.show(((AppCompatActivity)context).getSupportFragmentManager(),AddTypeDialogFragment.class.getSimpleName());

                }else {
                    viewHolder.llRoot.setFocusable(true);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if(null == data|| data.size()<1){
            // 没有数据的时候展示一个自定义的item
            return 1;
        }else{
            return  data.size();
        }
    }



    class ItemViewerHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivIcon;
        LinearLayout llRoot;

        public ItemViewerHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            llRoot = itemView.findViewById(R.id.ll_root);

        }
    }




}
