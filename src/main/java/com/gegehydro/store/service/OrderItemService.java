package com.gegehydro.store.service;

import com.gegehydro.store.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单明细服务类
 *
 * @author sunhao
 * @date 2018/1/11
 */
public interface OrderItemService {
    /**
     * 获取订单明细
     *
     * @param orderItem 订单明细
     * @param request   request
     * @return 订单明细
     */
    String getOrderItems(OrderItem orderItem, HttpServletRequest request);

    /**
     * 生成礼物
     * TODO
     * @param orderItem 订单明细
     * @param request   request
     * @return 礼物链接
     */
    String makeGift(OrderItem orderItem, HttpServletRequest request);

    /**
     * 生成key
     *
     * @param orderItem 订单明细
     * @param request   request
     * @return key
     */
    String getKey(OrderItem orderItem, HttpServletRequest request);
}
