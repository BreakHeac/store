package com.gegehydro.store.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义401返回值
 * 自定义身份验证失败返回值
 *
 * @author sunhao
 * Create on 2017/12/10
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        httpServletResponse.setHeader("Access-Control-Allow_Origin", "*");
        httpServletResponse.setStatus(401);
    }
}
