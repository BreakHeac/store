package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * 订单
 * <p>
 * name 名字
 * price 价格
 * type 类型（自用、礼物）
 * billing 礼物链接
 *
 * @author sunhao
 * @date 2018/1/8
 */
public class Order extends BaseEntity implements Serializable {

    private Double price;
    private int type;
    private String billing;
    private int status;
    private Integer owner;
    private Integer received;

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", type=" + type +
                ", billing='" + billing + '\'' +
                ", status=" + status +
                ", owner=" + owner +
                ", received=" + received +
                ", users=" + users +
                '}';
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }
}
