package com.gegehydro.store.entity.assistant;

import com.gegehydro.store.entity.BaseEntity;

import java.util.Arrays;
import java.util.Date;

/**
 * 用于协助前端使用
 *
 * @author sunhao
 * @date 2018/1/25
 */
public class VueAssistant extends BaseEntity {
    private String value;
    private Integer status;
    private String label;
    private Integer num;
    private String dates;

    @Override
    public String toString() {
        return "VueAssistant{" +
                "value='" + value + '\'' +
                ", status=" + status +
                ", label='" + label + '\'' +
                ", num=" + num +
                ", dates=" + dates +
                ", users=" + users +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
