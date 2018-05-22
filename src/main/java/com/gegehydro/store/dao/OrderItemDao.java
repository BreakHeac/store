package com.gegehydro.store.dao;

import com.gegehydro.store.entity.OrderItem;

/**
 * 订单明细dao
 *
 * @author sunhao
 * @date 2018/1/8
 */
public interface OrderItemDao extends BaseDao<OrderItem> {
    /**
     * 改变父订单状态为已使用
     *
     * @param orderItem 订单明细
     * @return 更新行数
     */
    int updateOrderStatus(OrderItem orderItem);
}
