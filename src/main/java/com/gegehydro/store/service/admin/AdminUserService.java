package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.UserLoginLogs;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.assistant.VueAssistant;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunhao
 * Create on 2017/12/26
 */
public interface AdminUserService {
    /**
     * 管理员新增权限用户
     *
     * @param users              新用户
     * @param bindingResult      校验结果
     * @param role               角色
     * @param httpServletRequest httprequest
     * @return 结果
     */
    String addAuthUser(@Validated Users users, BindingResult bindingResult, int role, HttpServletRequest httpServletRequest);

    /**
     * 获取权限组
     *
     * @return 权限组
     */
    String getRoles();

    /**
     * 获取对应角色下用户列表
     *
     * @param vueAssistant 角色
     * @return 对应角色下用户列表
     */
    String getUserRole(VueAssistant vueAssistant);

    /**
     * 更改用户角色
     *
     * @param users    用户
     * @param vueAssistant 角色
     * @return 更改结果
     */
    String changeRole(VueAssistant vueAssistant);

    /**
     * 获取用户登录记录
     *
     * @param userLoginLogs 用户
     * @return 登录记录
     */
    String getUserLogin(UserLoginLogs userLoginLogs);

    /**
     * 获取用户的所属角色
     *
     * @param users 用户
     * @return 角色
     */
    String getRoleUser(Users users);

    /**
     * 搜索用户
     *
     * @param users 用户
     * @return 用户
     */
    String getUser(Users users);
}
