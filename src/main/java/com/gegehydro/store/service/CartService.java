package com.gegehydro.store.service;

import com.gegehydro.store.entity.Cart;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车服务接口
 *
 * @author sunhao
 * @date 2018/1/4
 */
public interface CartService {
    /**
     * 添加app至用户购物车
     *
     * @param cart    购物车
     * @param request request
     * @return 添加结果
     */
    String addCart(Cart cart, HttpServletRequest request);

    /**
     * 删除用户购物车中的app
     *
     * @param cart    购物车
     * @param request request
     * @return 删除结果
     */
    String deleteCart(Cart cart, HttpServletRequest request);

    /**
     * 获取用户购物车列表
     *
     * @param request request
     * @return 列表
     */
    String getCart(HttpServletRequest request);

    /**
     * 获取用户购物车列表数量
     *
     * @param request request
     * @return 数量
     */
    String getCartNum(HttpServletRequest request);
}
