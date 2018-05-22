package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * @author sunhao
 * create on 2017/11/30
 * <p>
 * id id
 * users 用户
 * operateIp 操作IP
 * operatePlace 操作地址
 * operateDate 操作日期
 */
public class BaseEntity extends OperateEntity implements Serializable {

    protected Users users;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "users=" + users +
                '}';
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
