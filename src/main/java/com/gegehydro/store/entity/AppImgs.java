package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * @author sunhao
 * create on 2017/12/1
 * <p>
 * apps app实例
 * img 图片
 * status 使用状态
 */
public class AppImgs extends BaseEntity implements Serializable {
    private String img;
    private int status;

    @Override
    public String toString() {
        return "AppImgs{" +
                "img='" + img + '\'' +
                ", status=" + status +
                ", users=" + users +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
