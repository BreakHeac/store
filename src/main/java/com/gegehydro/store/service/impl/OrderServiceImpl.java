package com.gegehydro.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.AppsDao;
import com.gegehydro.store.dao.CartDao;
import com.gegehydro.store.dao.OrderDao;
import com.gegehydro.store.dao.OrderItemDao;
import com.gegehydro.store.entity.*;
import com.gegehydro.store.service.OrderService;
import com.gegehydro.store.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;


/**
 * 订单实现类
 *
 * @author sunhao
 * @date 2018/1/8
 */
@Service
public class OrderServiceImpl implements OrderService {
    private AppsDao appsDao;
    private CartDao cartDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private ComponentUtil componentUtil;
    private int userId;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderItemDao orderItemDao, AppsDao appsDao, CartDao cartDao, ComponentUtil componentUtil) {
        this.appsDao = appsDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.componentUtil = componentUtil;
        this.cartDao = cartDao;
    }

    @Override
    public String bill(String billing) {
        Order order = new Order();
        order.setBilling(billing.replace(' ', '+'));
        int number = orderDao.count(order);
        if (1 == number) {
            order = orderDao.find(order);
            if (0 == order.getStatus()) {
                return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, order));
            } else {
                return JSON.toJSONString(new BaseResp<>(ResultStatus.USED));
            }
        } else {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public String receiveBill(String billing, HttpServletRequest request) {
        userId = componentUtil.getUserIdFromRequest(request);
        Order order = new Order();
        order.setBilling(billing.replace(' ', '+'));
        int number = orderDao.count(order);
        if (1 == number) {
            order = orderDao.find(order);
            if (0 == order.getStatus()) {
                Users users = new Users();
                users.setId(userId);
                order.setStatus(1);
                order.setUsers(users);
                order.setOperateDate(new Date());
                order.setOperateIp(IpUtil.getIpAddress(request));
                number = orderDao.update(order);
                if (1 == number) {
                    number = orderDao.updateOrderItemUserId(order);
                    if (0 < number) {
                        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS));
                    } else {
                        return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
                    }
                } else {
                    return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
                }
            } else {
                return JSON.toJSONString(new BaseResp<>(ResultStatus.USED));
            }
        } else {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
    }

    @Override
    public String getOrders(HttpServletRequest request, Order order) {
        userId = componentUtil.getUserIdFromRequest(request);
        order.setId(userId);
        PageHelper.startPage(order.getPageNum(), order.getPageSize());
        ArrayList<Order> list = orderDao.findList(order);
        PageInfo<Order> orderPageInfo = new PageInfo<>(list);
        if (0 < orderPageInfo.getSize()) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, orderPageInfo));
        } else {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public String checkOut(Integer[] cartId, int type, HttpServletRequest request) {
        userId = componentUtil.getUserIdFromRequest(request);
        Users users = new Users();
        users.setId(userId);
        Double price = 0.0;
        if (cartId.length <= 0) {
            return JSON.toJSONString(new BaseResp(ResultStatus.EMPTY));
        }
        Cart cart;
        ArrayList<Cart> cartList = new ArrayList<>();
        for (Integer item : cartId) {
            cart = new Cart();
            cart.setId(item);
            cart.setUsers(users);
            cart = cartDao.find(cart);
            if (null == cart || 0 >= cart.getAppId()) {
                return JSON.toJSONString(new BaseResp(ResultStatus.CONFLICT_ERROR));
            }
            cart.setUsers(users);
            cartList.add(cart);
        }
        Apps apps;
        ArrayList<OrderItem> itemList = new ArrayList<>();
        for (Cart item : cartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAppId(item.getAppId());
            apps = new Apps();
            apps.setId(orderItem.getAppId());
            apps = appsDao.find(apps);
            if (0 == apps.getDiscount()) {
                price += apps.getPrice();
                orderItem.setPrice(apps.getPrice());
            } else {
                Date dateNow = new Date();
                if (dateNow.after(DateUtil.getDateFromString(apps.getDiscountStart())) && dateNow.before(DateUtil.getDateFromString(apps.getDiscountEnd()))) {
                    price += apps.getDiscountPrice();
                    orderItem.setPrice(apps.getDiscountPrice());
                } else {
                    price += apps.getPrice();
                    orderItem.setPrice(apps.getPrice());
                }
            }
            itemList.add(orderItem);
        }
        String ip = IpUtil.getIpAddress(request);
        Date nowDate = new Date();
        Order order = new Order();
        String billing = null;
        if (1 == type) {
            billing = ip + price + userId + new Date();
            billing = EncodeUtil.aesEncode(billing);
            order.setBilling(billing);
            order.setOwner(userId);
            userId = 0;
            nowDate = null;
            ip = null;
        }
        order.setType(type);
        order.setStatus(0);
        order.setPrice(price);
        order.setOperateDate(nowDate);
        order.setOperateIp(ip);
        order.setId(userId);
        orderDao.insert(order);
        int orderId = order.getId();
        int result;
        for (OrderItem item : itemList) {
            item.setId(userId);
            item.setOperateDate(nowDate);
            item.setOperateIp(ip);
            item.setOrderId(orderId);
            item.setType(0);
            try {
                result = orderItemDao.insert(item);
                if (0 >= result) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException ex) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
            }
        }
        for (Cart item : cartList) {
            try {
                result = cartDao.update(item);
                if (0 >= result) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
            }
        }
        order = new Order();
        order.setBilling(billing);
        order.setId(orderId);
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, order));
    }

}
