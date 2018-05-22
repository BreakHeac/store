package com.gegehydro.store.controller;

import com.gegehydro.store.entity.Order;
import com.gegehydro.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单控制类
 *
 * @author sunhao
 * @date 2018/1/8
 */
@CrossOrigin
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RestController
@RequestMapping(value = "/order", produces = "application/json;charset=UTF-8")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/getOrders")
    public String getOrders(HttpServletRequest request, Order order) {
        return orderService.getOrders(request, order);
    }

    @PostMapping(value = "/checkOut")
    public String checkOut(Integer[] cartId, int type, HttpServletRequest request) {
        return orderService.checkOut(cartId, type, request);
    }

    @PostMapping(value = "/receiveBill")
    public String receiveBill(String billing, HttpServletRequest request) {
        return orderService.receiveBill(billing, request);
    }

    @GetMapping(value = "")
    @PreAuthorize("permitAll()")
    public String bill(String billing) {
        return orderService.bill(billing);
    }
}
