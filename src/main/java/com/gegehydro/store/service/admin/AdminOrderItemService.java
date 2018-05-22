package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.OrderItem;

/**
 * 订单明细服务接口
 *
 * @author sunhao
 * @date 2018/2/22
 */
public interface AdminOrderItemService {
    /**
     * 获取订单明细
     *
     * @param orderItem 订单明细
     * @return 订单明细
     */
    String orderDetail(OrderItem orderItem);
}
