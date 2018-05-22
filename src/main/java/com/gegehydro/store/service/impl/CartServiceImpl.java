package com.gegehydro.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.CartDao;
import com.gegehydro.store.entity.Cart;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.service.CartService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ComponentUtil;
import com.gegehydro.store.util.IpUtil;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * 购物车实现类
 *
 * @author sunhao
 * @date 2018/1/4
 */
@Service
public class CartServiceImpl implements CartService {
    private CartDao cartDao;
    private BaseResp baseResp;
    private ComponentUtil componentUtil;
    private int result;
    private int userId;

    @Autowired
    public CartServiceImpl(CartDao cartDao, ComponentUtil componentUtil) {
        this.componentUtil = componentUtil;
        this.cartDao = cartDao;
    }

    @Override
    public String addCart(Cart cart, HttpServletRequest request) {
        if (0 >= cart.getAppId()) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
        userId = componentUtil.getUserIdFromRequest(request);
        cart.setId(userId);
        result = cartDao.count(cart);
        if (0 < result) {
            baseResp = new BaseResp(ResultStatus.CONFLICT_ERROR);
        } else {
            cart.setOperateDate(new Date());
            cart.setOperateIp(IpUtil.getIpAddress(request));
            cart.setFlag(0);
            result = cartDao.insert(cart);
            if (result > 0) {
                baseResp = new BaseResp<>(ResultStatus.SUCCESS, cart.getId());
            } else {
                baseResp = new BaseResp(ResultStatus.ADD_FAIL);
            }
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String deleteCart(Cart cart, HttpServletRequest request) {
        userId = componentUtil.getUserIdFromRequest(request);
        Users users = new Users();
        users.setId(userId);
        cart.setUsers(users);
        result = cartDao.update(cart);
        if (result > 0) {
            baseResp = new BaseResp(ResultStatus.SUCCESS);
        } else {
            baseResp = new BaseResp(ResultStatus.EMPTY);
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String getCart(HttpServletRequest request) {
        Cart cart = new Cart();
        cart.setId(componentUtil.getUserIdFromRequest(request));
        ArrayList<Cart> list = cartDao.findList(cart);
        if (list.size() <= 0) {
            baseResp = new BaseResp(ResultStatus.EMPTY);
        } else {
            baseResp = new BaseResp<>(ResultStatus.SUCCESS, list);
        }
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String getCartNum(HttpServletRequest request) {
        Cart cart = new Cart();
        cart.setId(componentUtil.getUserIdFromRequest(request));
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, cartDao.count(cart)));
    }
}