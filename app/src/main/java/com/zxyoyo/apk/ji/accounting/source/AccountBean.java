package com.zxyoyo.apk.ji.accounting.source;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述
 * 账单实体类
 * @version 1.0
 * @package 包名 ：com.zxyoyo.apk.ji.accounting.source
 * @createTime 创建时间 ：2019/2/14
 * @modifyTime 修改时间 ：2019/2/14
 * @modifyMemo 修改备注：
 */
@Entity
public class AccountBean {
    @Id(autoincrement = true)
    private long id;
    private String account;//账户
    private double number;//金额
    private long time;// 纪录时间
    private int icon;// 图标
    private int type;// 0 为支出，1为收入
    private String detailType;// 具体种类
    private String description;// 详情描述
    private String photo;//照片
    @Generated(hash = 797197479)
    public AccountBean(long id, String account, double number, long time, int icon,
            int type, String detailType, String description, String photo) {
        this.id = id;
        this.account = account;
        this.number = number;
        this.time = time;
        this.icon = icon;
        this.type = type;
        this.detailType = detailType;
        this.description = description;
        this.photo = photo;
    }
    @Generated(hash = 1267506976)
    public AccountBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public double getNumber() {
        return this.number;
    }
    public void setNumber(double number) {
        this.number = number;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getDetailType() {
        return this.detailType;
    }
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public int getIcon() {
        return this.icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
}
