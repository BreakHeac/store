package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * 订单明细
 * <p>
 * name 名字
 * price 价格
 * orderId 订单id
 * type 类型（自用、礼物、已使用）
 * gift 礼物链接
 * explored 暴露key
 * key key
 *
 * @author sunhao
 * @date 2018/1/8
 */
public class OrderItem extends BaseEntity implements Serializable {
    private String name;
    private Double price;
    private Integer appId;
    private Integer orderId;
    private Integer type;
    private String gift;
    private Integer explored;
    private String key;

    @Override
    public String toString() {
        return "OrderItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", appId=" + appId +
                ", orderId=" + orderId +
                ", type=" + type +
                ", gift='" + gift + '\'' +
                ", explored=" + explored +
                ", key='" + key + '\'' +
                ", users=" + users +
                '}';
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExplored() {
        return explored;
    }

    public void setExplored(Integer explored) {
        this.explored = explored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
