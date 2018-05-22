package com.gegehydro.store.service;

import com.gegehydro.store.entity.Users;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sunhao
 * create on 2017/11/15
 */
public interface UserService {

    /**
     * 依据用户邮箱、密码进行登录操作
     *
     * @param users              用户实例
     * @param bindingResult      格式绑定结果
     * @param httpServletRequest request请求
     * @return 登录结果
     */
    String userLogin(@Validated Users users, BindingResult bindingResult, HttpServletRequest httpServletRequest);

    /**
     * 依据用户邮箱、密码注册用户
     *
     * @param users              用户实例
     * @param checkPassword      重复密码
     * @param bindingResult      格式绑定结果
     * @param httpServletRequest request请求
     * @return 注册结果
     */
    String userRegister(@Validated Users users, BindingResult bindingResult, String checkPassword, HttpServletRequest httpServletRequest);

    /**
     * 刷信密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);
}
