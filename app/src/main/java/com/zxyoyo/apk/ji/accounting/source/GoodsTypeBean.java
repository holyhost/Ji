package com.zxyoyo.apk.ji.accounting.source;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * 描述
 * 交易物品种类
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.accounting.source
 * @createTime 创建时间 ：2019/2/15
 * @modifyTime 修改时间 ：2019/2/15
 * @modifyMemo 修改备注：
 */
@Entity
public class GoodsTypeBean {

    @Id(autoincrement = true)
    private long id;
    private String name;
    private int type=0;// 0->default,1->user add
    private long createTime = System.currentTimeMillis();
    @Generated(hash = 19990546)
    public GoodsTypeBean(long id, String name, int type, long createTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createTime = createTime;
    }
    @Generated(hash = 1042864668)
    public GoodsTypeBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}
