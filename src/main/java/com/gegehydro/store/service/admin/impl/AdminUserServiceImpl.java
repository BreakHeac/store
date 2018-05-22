package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.UserDao;
import com.gegehydro.store.dao.UserLoginLogsDao;
import com.gegehydro.store.dao.admin.RoleDao;
import com.gegehydro.store.entity.UserLoginLogs;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.assistant.VueAssistant;
import com.gegehydro.store.service.admin.AdminUserService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.IpUtil;
import com.gegehydro.store.util.ResultStatus;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author sunhao
 * Create on 2017/12/26
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private UserLoginLogsDao loginLogsDao;

    @Autowired
    public AdminUserServiceImpl(UserDao userDao, RoleDao roleDao, UserLoginLogsDao loginLogsDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.loginLogsDao = loginLogsDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addAuthUser(@Validated Users users, BindingResult bindingResult, int role, HttpServletRequest httpServletRequest) {
        String result;
        if (bindingResult.hasErrors()) {
            result = bindingResult.getFieldError().getDefaultMessage();
            if ("10".equals(result)) {
                return JSON.toJSONString(new BaseResp(ResultStatus.FORMAT_ERROR));
            } else if ("20".equals(result)) {
                return JSON.toJSONString(new BaseResp(ResultStatus.PASSWORD_ERROR));
            }
        }
        int number = userDao.selectQuantityByEmail(users);
        if (0 != number) {
            return JSON.toJSONString(new BaseResp(ResultStatus.EXIST_ERROR));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        users.setPassword(encoder.encode(users.getPassword()));
        users.setOperateIp(IpUtil.getIpAddress(httpServletRequest));
        BaseResp baseResp;
        try {
            users.setOperateDate(new Date());
            users.setStatus(90);
            number = userDao.insert(users);
            if (1 == number) {
                number = userDao.insertRoles(users.getId(), role);
                if (number > 0) {
                    baseResp = new BaseResp(ResultStatus.SUCCESS);
                } else {
                    throw new RuntimeException();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return JSON.toJSONString(new BaseResp(ResultStatus.ADD_FAIL));
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String getRoles() {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, roleDao.findList(new VueAssistant())));
    }

    @Override
    public String getUserRole(VueAssistant vueAssistant) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, roleDao.selectUsersByRole(vueAssistant)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeRole(VueAssistant vueAssistant) {
        try {
            if (1 == roleDao.updateUserRole(vueAssistant)) {
                return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS));
            }
            return JSON.toJSONString(new BaseResp(ResultStatus.FAIL));
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(new BaseResp(ResultStatus.FAIL));
        }
    }

    @Override
    public String getUserLogin(UserLoginLogs userLoginLogs) {
        PageHelper.startPage(1, 10);
        ArrayList<UserLoginLogs> loginLogs = loginLogsDao.findList(userLoginLogs);
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, loginLogs));
    }

    @Override
    public String getRoleUser(Users users) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, roleDao.selectRoleByUser(users)));
    }

    @Override
    public String getUser(Users users) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, userDao.findUserList(users)));
    }
}
