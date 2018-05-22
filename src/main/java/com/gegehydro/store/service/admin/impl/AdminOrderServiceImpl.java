package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminOrderDao;
import com.gegehydro.store.entity.Order;
import com.gegehydro.store.service.admin.AdminOrderService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ResultStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 订单管理服务实现类
 *
 * @author sunhao
 * @date 2018/1/21
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    private AdminOrderDao adminOrderDao;

    @Autowired
    public AdminOrderServiceImpl(AdminOrderDao adminOrderDao) {
        this.adminOrderDao = adminOrderDao;
    }

    @Override
    public String getOrders(Order order) {
        PageHelper.startPage(order.getPageNum(), order.getPageSize());
        ArrayList<Order> orderArrayList = adminOrderDao.findList(order);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderArrayList);
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, orderPageInfo));
    }

    @Override
    public String changeOrderStatus(Order order, HttpServletRequest request) {
        int result = adminOrderDao.update(order);
        if (0 < result) {
            return JSON.toJSONString(new BaseResp(ResultStatus.SUCCESS));
        }
        return JSON.toJSONString(new BaseResp(ResultStatus.FAIL));
    }
}
