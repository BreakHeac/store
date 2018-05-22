package com.gegehydro.store.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实例
 *
 * @author sunhao
 * <p>
 * create on 2017/11/15
 * email 用户邮箱
 * password 密码
 * registerIp 注册ip
 * registerPlace 注册地点
 * registerDate 注册时间
 * status 状态
 */
public class Users extends OperateEntity implements Serializable {
    @Email(message = "10")
    private String email;
    @Size(min = 6, max = 16, message = "20")
    private String password;
    private int status;
    private List<String> roles;

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
