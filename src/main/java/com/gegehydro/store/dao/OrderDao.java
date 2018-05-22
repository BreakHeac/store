package com.gegehydro.store.dao;

import com.gegehydro.store.entity.Order;

/**
 * 订单dao
 *
 * @author sunhao
 * @date 2018/1/8
 */
public interface OrderDao extends BaseDao<Order> {
    /**
     * 接收订单礼物时同步更新订单明细的用户id
     *
     * @param order 订单
     * @return 更新行数
     */
    int updateOrderItemUserId(Order order);
}
