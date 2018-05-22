package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * 厂商类
 *
 * @author sunhao
 * @date 2018/1/29
 */
public class Firm extends BaseEntity implements Serializable {
    private String name;
    private int status;

    @Override
    public String toString() {
        return "Firm{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", users=" + users +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
