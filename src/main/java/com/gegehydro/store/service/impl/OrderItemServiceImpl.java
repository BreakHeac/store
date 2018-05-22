package com.gegehydro.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.OrderItemDao;
import com.gegehydro.store.dao.WarehouseDao;
import com.gegehydro.store.entity.OrderItem;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.Warehouse;
import com.gegehydro.store.service.OrderItemService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ComponentUtil;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 订单明细服务实现类
 *
 * @author sunhao
 * @date 2018/1/11
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;
    private WarehouseDao warehouseDao;
    private ComponentUtil componentUtil;
    private int userId;

    @Autowired
    public OrderItemServiceImpl(OrderItemDao orderItemDao, WarehouseDao warehouseDao, ComponentUtil componentUtil) {
        this.orderItemDao = orderItemDao;
        this.warehouseDao = warehouseDao;
        this.componentUtil = componentUtil;
    }

    @Override
    public String getOrderItems(OrderItem orderItem, HttpServletRequest request) {
        userId = componentUtil.getUserIdFromRequest(request);
        Users users = new Users();
        users.setId(userId);
        orderItem.setUsers(users);
        ArrayList<OrderItem> list = orderItemDao.findList(orderItem);
        if (0 < list.size()) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, list));
        } else {
            return JSON.toJSONString(new BaseResp(ResultStatus.EMPTY));
        }
    }


    @Override
    public String makeGift(OrderItem orderItem, HttpServletRequest request) {
        // TODO
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String getKey(OrderItem orderItem, HttpServletRequest request) {
        userId = componentUtil.getUserIdFromRequest(request);
        Users users = new Users();
        users.setId(userId);
        orderItem.setUsers(users);
        orderItem = orderItemDao.find(orderItem);
        if (null == orderItem) {
            return JSON.toJSONString(new BaseResp(ResultStatus.EMPTY));
        }
        if (1 == orderItem.getExplored()) {
            return JSON.toJSONString(new BaseResp(ResultStatus.CONFLICT_ERROR));
        } else {
            Warehouse warehouse = new Warehouse();
            warehouse.setAppId(orderItem.getAppId());
            warehouse = warehouseDao.find(warehouse);
            if (null == warehouse) {
                return JSON.toJSONString(new BaseResp(ResultStatus.EMPTY));
            }
            try {
                orderItem.setKey(warehouse.getKey());
                orderItem.setType(1);
                int result = orderItemDao.update(orderItem);
                if (0 >= result) {
                    throw new RuntimeException();
                }
                result = warehouseDao.update(warehouse);
                if (0 >= result) {
                    throw new RuntimeException();
                }
                result = orderItemDao.updateOrderStatus(orderItem);
                if (0 >= result) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return JSON.toJSONString(new BaseResp(ResultStatus.FAIL));
            }
            orderItem = new OrderItem();
            orderItem.setKey(warehouse.getKey());
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, orderItem));
        }
    }
}
