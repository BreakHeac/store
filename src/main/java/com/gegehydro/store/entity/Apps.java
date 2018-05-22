package com.gegehydro.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sunhao
 * create on 2017/12/1
 * <p>
 * name app名字
 * producer 开发商
 * publisher 发行商
 * language 语言
 * listDate 上架日期
 * price 价格
 * discount 开启折扣标记
 * discountPrice 折扣价格
 * discountOff 折扣力度
 * discountStart 折扣开启时间
 * discountEnd 折扣结束时间
 * coverImg app封面
 * introduction app介绍
 * flag app状态 发售。。。。。
 * outOfStock 缺货？
 */
public class Apps extends BaseEntity implements Serializable {
    private String name;
    private String producer;
    private String publisher;
    private String language;
    private String listDate;
    private Double price;
    private Integer discount;
    private Double discountPrice;
    private Integer discountOff;
    private String discountStart;
    private String discountEnd;
    private String coverImg;
    private String introduction;
    private String flag;
    private Integer outOfStock;
    private ArrayList<AppImgs> appImgs;
    private String[] img;

    @Override
    public String toString() {
        return "Apps{" +
                "name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", listDate='" + listDate + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discountPrice=" + discountPrice +
                ", discountOff=" + discountOff +
                ", discountStart=" + discountStart +
                ", discountEnd=" + discountEnd +
                ", coverImg='" + coverImg + '\'' +
                ", introduction='" + introduction + '\'' +
                ", flag='" + flag + '\'' +
                ", outOfStock=" + outOfStock +
                ", appImgs=" + appImgs +
                ", img=" + Arrays.toString(img) +
                ", users=" + users +
                '}';
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getDiscountOff() {
        return discountOff;
    }

    public void setDiscountOff(Integer discountOff) {
        this.discountOff = discountOff;
    }

    public String getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(String discountStart) {
        this.discountStart = discountStart;
    }

    public String getDiscountEnd() {
        return discountEnd;
    }

    public void setDiscountEnd(String discountEnd) {
        this.discountEnd = discountEnd;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(Integer outOfStock) {
        this.outOfStock = outOfStock;
    }

    public ArrayList<AppImgs> getAppImgs() {
        return appImgs;
    }

    public void setAppImgs(ArrayList<AppImgs> appImgs) {
        this.appImgs = appImgs;
    }
}
