package com.gegehydro.store.entity;

import java.io.Serializable;

/**
 * 用户登录记录实例
 *
 * @author sunhao
 * create on 2017/11/15
 */
public class UserLoginLogs extends BaseEntity implements Serializable {
    @Override
    public String toString() {
        return "UserLoginLogs{" +
                "users=" + users +
                '}';
    }
}
