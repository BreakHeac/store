package com.gegehydro.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gegehydro.store.dao.UserDao;
import com.gegehydro.store.dao.UserLoginLogsDao;
import com.gegehydro.store.entity.UserLoginLogs;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.security.JwtTokenUtil;
import com.gegehydro.store.service.UserService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.IpUtil;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author sunhao
 * create on 2017/11/15
 */
@Service
public class UserServiceImpl implements UserService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private BaseResp baseResp;
    private UserDao userDao;
    private UserLoginLogsDao loginLogsDao;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserDao userDao, UserLoginLogsDao loginLogsDao) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
        this.loginLogsDao = loginLogsDao;
    }

    private String getToken(Users users){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword());
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(users.getEmail());
        Users user = userDao.selectUserByEmail(users);
        return jwtTokenUtil.generateToken(userDetails, user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String userLogin(@Validated Users user, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            String result = bindingResult.getFieldError().getDefaultMessage();
            if ("10".equals(result)) {
                baseResp = new BaseResp(ResultStatus.FORMAT_ERROR);
            } else if ("20".equals(result)) {
                baseResp = new BaseResp(ResultStatus.PASSWORD_ERROR);
            }
        } else {
            JSONObject object = new JSONObject();
            Users users = userDao.selectUserByEmail(user);
            if (null == users){
                baseResp = new BaseResp(ResultStatus.EXIST_ERROR);
                return JSON.toJSONString(baseResp);
            }
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            if (!bcryptPasswordEncoder.matches(user.getPassword(),users.getPassword())){
                return JSON.toJSONString(new BaseResp<>(ResultStatus.PASSWORD_ERROR));
            }
            object.put("token", getToken(user));
            UserLoginLogs loginLogs = new UserLoginLogs();
            loginLogs.setId(user.getId());
            loginLogs.setOperateDate(new Date());
            loginLogs.setOperateIp(IpUtil.getIpAddress(httpServletRequest));
            loginLogsDao.insert(loginLogs);
            baseResp = new BaseResp(ResultStatus.SUCCESS, object);
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String userRegister(@Validated Users users, BindingResult bindingResult, String checkPassword, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return JSON.toJSONString(new BaseResp(ResultStatus.FAIL));
        }
        if (!users.getPassword().equals(checkPassword)) {
            return JSON.toJSONString(new BaseResp(ResultStatus.PASSWORD_DIFFERENT));
        }
        int number = userDao.selectQuantityByEmail(users);
        if (0 != number) {
            return JSON.toJSONString(new BaseResp(ResultStatus.CONFLICT_ERROR));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldPassword= users.getPassword();
        users.setPassword(encoder.encode(oldPassword));
        users.setOperateIp(IpUtil.getIpAddress(httpServletRequest));
        try {
            users.setOperateDate(new Date());
            users.setStatus(0);
            number = userDao.insert(users);
            if (0 < number) {
                number = userDao.insertRoles(users.getId(), 1);
                if (number > 0) {
                    JSONObject object = new JSONObject();
                    users.setPassword(oldPassword);
                    object.put("token", getToken(users));
                    baseResp = new BaseResp<>(ResultStatus.SUCCESS,object);
                } else {
                    throw new RuntimeException();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JSON.toJSONString(new BaseResp(ResultStatus.ADD_FAIL));
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        baseResp = new BaseResp(ResultStatus.REFRESH_FAIL);
        if (!jwtTokenUtil.isTokenExpired(token)) {
            baseResp = new BaseResp<>(ResultStatus.SUCCESS, jwtTokenUtil.refreshToken(token));
        }
        return JSON.toJSONString(baseResp);
    }
}
