package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员订单管理服务接口
 *
 * @author sunhao
 * @date 2018/1/21
 */
public interface AdminOrderService {
    /**
     * 获取订单
     *
     * @param order 订单
     * @return 全部订单
     */
    String getOrders(Order order);

    /**
     * 改变订单状态，即退款
     *
     * @param order   订单
     * @param request request
     * @return 结果
     */
    String changeOrderStatus(Order order, HttpServletRequest request);
}
