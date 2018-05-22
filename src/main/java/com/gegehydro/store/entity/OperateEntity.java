package com.gegehydro.store.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author sunhao
 * create on 2017/12/3
 */
public class OperateEntity implements Serializable {
    @Id
    private int id;
    private String operateIp;
    private Date operateDate;
    private int pageNum = 1;
    private int pageSize = 16;

    @Override
    public String toString() {
        return "OperateEntity{" +
                "id=" + id +
                ", operateIp='" + operateIp + '\'' +
                ", operateDate=" + operateDate +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
