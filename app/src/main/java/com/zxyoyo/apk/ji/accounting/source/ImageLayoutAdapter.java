package com.zxyoyo.apk.ji.accounting.source;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zxyoyo.apk.ji.BaseApplication;
import com.zxyoyo.apk.ji.R;
import com.zxyoyo.apk.ji.designview.AddTypeDialogFragment;

import java.util.List;
import java.util.Map;

import database.GoodsTypeBeanDao;

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

    private  GoodsTypeBean defaultBean;
    private List<GoodsTypeBean> data;
    private Context context;
    // 条目的最大数值
    public static  final  int MAX_COUNT = 10;
    private AlertDialog addDialog;
    AlertDialog deleteDialog;
    private int selectedPosition = -1;
    private int currentIndex;


    public int getCurrentIndex() {
        return currentIndex;
    }

    public ImageLayoutAdapter(List<GoodsTypeBean> data, Context context, int currentIndex) {
        this.currentIndex = currentIndex;
        this.data = data;
        this.context = context;
        if(data!=null&&data.size()<MAX_COUNT){
            defaultBean = new GoodsTypeBean(context.getResources().getString(R.string.goods_type_add), R.drawable.goods_type_add);
            this.data.add(defaultBean);
        }

    }

    public void addData(GoodsTypeBean bean){
        if(null!=data)
        {
            data.remove(data.size()-1);
            data.add(bean);

        }
        if(MAX_COUNT-data.size()>0){
            data.add(defaultBean);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageLayoutAdapter.ItemViewerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ItemViewerHolder(LayoutInflater.from(context).inflate(R.layout.item_image_text,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final ImageLayoutAdapter.ItemViewerHolder viewHolder, final int i) {
        GoodsTypeBean item = data.get(i);
        int icon = item.getIcon();
        if(icon>0){
            viewHolder.ivIcon.setImageDrawable(context.getResources().getDrawable(icon));
        }
        if(selectedPosition==i){
            viewHolder.ivIcon.setColorFilter(Color.RED);
        }else {
            viewHolder.ivIcon.setColorFilter(Color.GRAY);

        }
        final String iconName = item.getName();
        final int type = item.getType();
        viewHolder.tvName.setText(iconName);
        viewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(iconName,context.getResources().getString(R.string.goods_type_add))){
                    // 点击的是添加按钮
                    showInputDialog(context);
                }else {
                    // 点击的时候类别，记录选中位置，刷新界面，使其变为红色，同时，如果其它界面有选中，应该刷新掉
                    if(null!=listener){
                        listener.onClick(iconName);
                    }
                    selectedPosition = i;
                    notifyDataSetChanged();
                }

            }
        });
        viewHolder.llRoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //长按，删除已添加的种类，默认的不允许删除
                // 如果已选中，不触发长按事件
                if(type-0>0&&selectedPosition!=i){

                    deleteDialog = new AlertDialog.Builder(context)
                            .setTitle(R.string.alert_alarm)
                            .setMessage(R.string.alert_message_delete)
                            .setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int anInt) {
                                    BaseApplication.getDaoSession().getGoodsTypeBeanDao().delete(data.get(i));
                                    data.remove(i);
                                    notifyDataSetChanged();
                                    if(deleteDialog !=null) deleteDialog.dismiss();
                                }
                            })
                            .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int anInt) {
                                    if(deleteDialog !=null)
                                    deleteDialog.dismiss();
                                }
                            })
                            .create();
                    deleteDialog.show();
                    return true;
                }
                return false;
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


    private void showInputDialog(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_add_type, null);
        initView(view);
        addDialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        addDialog.show();

    }

    private void initView(View view) {
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Button btnComplete = view.findViewById(R.id.btn_complete);
        final EditText etName = view.findViewById(R.id.et_input_goods_name);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addDialog!=null)
                    addDialog.dismiss();
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsTypeBean bean = new GoodsTypeBean(etName.getText().toString(), R.drawable.goods_type_default,1);
                GoodsTypeBeanDao dao = BaseApplication.getDaoSession().getGoodsTypeBeanDao();
                long count = dao.queryBuilder()
                        .where(GoodsTypeBeanDao.Properties.Name.eq(etName.getText().toString())).count();
                if(count<1){
                    // 未存在，添加进去
                    dao.insert(bean);
                    addData(bean);

                }else {
                    Toast.makeText(context,"已存在此类别！",Toast.LENGTH_SHORT).show();
                }
                if(addDialog!=null)
                    addDialog.dismiss();
            }
        });
    }

    /**
     * 刷新界面，用于清楚掉选中的未红色
     */
    public  void refreshLayout(){
        if(selectedPosition>-1){
            selectedPosition = -1;
            notifyDataSetChanged();
        }
    }

    private ImageLayoutClickListener listener;

    public void setListener(ImageLayoutClickListener listener) {
        this.listener = listener;
    }

    public interface ImageLayoutClickListener{
        void onClick(String typeName);
    }


}
