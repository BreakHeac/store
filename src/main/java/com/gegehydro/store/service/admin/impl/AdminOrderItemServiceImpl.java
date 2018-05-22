package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminOrderItemDao;
import com.gegehydro.store.entity.OrderItem;
import com.gegehydro.store.service.admin.AdminOrderItemService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单明细服务实现类
 *
 * @author sunhao
 * @date 2018/2/22
 */
@Service
public class AdminOrderItemServiceImpl implements AdminOrderItemService {
    private AdminOrderItemDao adminOrderItemDao;

    @Autowired
    public AdminOrderItemServiceImpl(AdminOrderItemDao adminOrderItemDao) {
        this.adminOrderItemDao = adminOrderItemDao;
    }

    @Override
    public String orderDetail(OrderItem orderItem) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, adminOrderItemDao.findList(orderItem)));
    }
}
