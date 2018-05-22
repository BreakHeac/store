package com.gegehydro.store.controller;

import com.gegehydro.store.entity.Users;
import com.gegehydro.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunhao
 * create on 2017/11/15
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private HttpServletRequest httpServletRequest;
    private UserService userService;

    @Autowired
    public UserController(HttpServletRequest httpServletRequest, UserService userService) {
        this.httpServletRequest = httpServletRequest;
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public String login(Users users, BindingResult bindingResult) {
        return userService.userLogin(users, bindingResult, httpServletRequest);
    }

    @PostMapping(value = "/register", params = {"checkPassword"})
    public String register(Users users, BindingResult bindingResult, @RequestParam String checkPassword) {
        return userService.userRegister(users, bindingResult, checkPassword, httpServletRequest);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }
}
