package com.gegehydro.store.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author sunhao
 * <p>
 * Create on 2018/1/4
 * appId app的id
 * flag 使用状态（使用中，已删除）
 */
public class Cart extends BaseEntity implements Serializable {
    private int appId;
    private int flag;
    private ArrayList<Apps> apps;

    @Override
    public String toString() {
        return "Cart{" +
                "appId=" + appId +
                ", flag=" + flag +
                ", apps=" + apps +
                ", users=" + users +
                '}';
    }

    public ArrayList<Apps> getApps() {
        return apps;
    }

    public void setApps(ArrayList<Apps> apps) {
        this.apps = apps;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
