package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * 库存类
 *
 * @author sunhao
 * @date 2018/1/12
 */
public class Warehouse extends BaseEntity implements Serializable {
    private Integer appId;
    private int flag;
    private String key;
    private long quantity;

    @Override
    public String toString() {
        return "Warehouse{" +
                "appId=" + appId +
                ", flag=" + flag +
                ", key='" + key + '\'' +
                ", quantity=" + quantity +
                ", users=" + users +
                '}';
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
