package com.gegehydro.store.util;

import com.gegehydro.store.dao.UserDao;
import com.gegehydro.store.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制层帮助类
 *
 * @author sunhao
 * @date 2018/1/9
 */
@Component
public class ComponentUtil {
    private JwtTokenUtil jwtTokenUtil;
    private UserDao userDao;

    @Autowired
    public ComponentUtil(JwtTokenUtil jwtTokenUtil, UserDao userDao) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
    }

    /**
     * 从request请求中获取用户id
     *
     * @param request request请求
     * @return 用户id
     */
    public Integer getUserIdFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null) {
                return userDao.findIdByEmail(username);
            }
        }
        return 0;
    }
}
