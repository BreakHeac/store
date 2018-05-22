package com.gegehydro.store.service;

import com.gegehydro.store.entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单服务类
 *
 * @author sunhao
 * @date 2018/1/8
 */
public interface OrderService {
    /**
     * 依据礼物序列获取礼物信息
     *
     * @param billing 礼物序列号
     * @return 订单信息
     */
    String bill(String billing);

    /**
     * 依据礼物序列接收礼物信息
     *
     * @param billing 礼物序列号
     * @param request request
     * @return 接受结果
     */
    String receiveBill(String billing, HttpServletRequest request);

    /**
     * 依据用户，获取订单
     *
     * @param request 请求
     * @param order   订单
     * @return 订单
     */
    String getOrders(HttpServletRequest request, Order order);

    /**
     * 完成下单
     *
     * @param cartId  cart的id
     * @param type    订单类型
     * @param request request
     * @return 订单信息
     */
    String checkOut(Integer[] cartId, int type, HttpServletRequest request);

}
