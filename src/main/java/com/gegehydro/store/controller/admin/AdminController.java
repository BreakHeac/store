package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.UserLoginLogs;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.assistant.VueAssistant;
import com.gegehydro.store.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunhao
 * create on 2017/12/6
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class AdminController {
    private HttpServletRequest httpServletRequest;
    private AdminUserService adminUserService;

    @Autowired
    public AdminController(AdminUserService adminUserService, HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.adminUserService = adminUserService;
    }

    /**
     * 新建授权用户
     *
     * @param users         新用户
     * @param bindingResult 绑定结果
     * @param role          角色
     * @return 新建结果
     */
    @PostMapping(value = "/authUser")
    public String authUser(Users users, BindingResult bindingResult, int role) {
        return adminUserService.addAuthUser(users, bindingResult, role, httpServletRequest);
    }

    /**
     * 获取角色
     *
     * @return 角色
     */
    @GetMapping(value = "/getRoles")
    public String getRoles() {
        return adminUserService.getRoles();
    }

    /**
     * 获取对应角色下用户列表
     *
     * @param vueAssistant 角色
     * @return 对应角色下用户列表
     */
    @GetMapping(value = "/getUserRole")
    public String getUserRole(VueAssistant vueAssistant) {
        return adminUserService.getUserRole(vueAssistant);
    }

    /**
     * 获取用户登录记录
     *
     * @param userLoginLogs 用户
     * @return 登录记录
     */
    @GetMapping(value = "/getUserLogin")
    public String getUserLogin(UserLoginLogs userLoginLogs) {
        return adminUserService.getUserLogin(userLoginLogs);
    }

    /**
     * 获取用户的所属角色
     *
     * @param users 用户
     * @return 角色
     */
    @GetMapping(value = "/getRoleUser")
    public String getRoleUser(Users users) {
        return adminUserService.getRoleUser(users);
    }

    /**
     * 获取用户
     *
     * @param users 用户
     * @return 用户详细
     */
    @GetMapping(value = "/getUser")
    public String getUser(Users users) {
        return adminUserService.getUser(users);
    }

    @PostMapping(value = "/changeRole")
    public String changeRole(VueAssistant vueAssistant) {
        return adminUserService.changeRole(vueAssistant);
    }
}
