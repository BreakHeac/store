package com.gegehydro.store.controller;

import com.gegehydro.store.entity.OrderItem;
import com.gegehydro.store.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单明细控制类
 *
 * @author sunhao
 * @date 2018/1/11
 */
@CrossOrigin
@RestController
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping(value = "/orderItem", produces = "application/json;charset=UTF-8")
public class OrderItemController {
    private OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping(value = "/getOrderItems")
    public String getOrderItems(OrderItem orderItem, HttpServletRequest request) {
        return orderItemService.getOrderItems(orderItem, request);
    }

    @GetMapping(value = "/getKey")
    public String getKey(OrderItem orderItem, HttpServletRequest request) {
        return orderItemService.getKey(orderItem, request);
    }

}
