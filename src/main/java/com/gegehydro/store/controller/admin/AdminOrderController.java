package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.Order;
import com.gegehydro.store.service.admin.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员订单控制类
 *
 * @author sunhao
 * @date 2018/1/21
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/adminOrder", produces = "application/json;charset=UTF-8")
public class AdminOrderController {
    private AdminOrderService adminOrderService;
    private HttpServletRequest request;

    @Autowired
    public AdminOrderController(AdminOrderService adminOrderService, HttpServletRequest request) {
        this.adminOrderService = adminOrderService;
        this.request = request;
    }

    @GetMapping(value = "/getOrders")
    public String getOrders(Order order) {
        return adminOrderService.getOrders(order);
    }

    @PostMapping(value = "changeOrderStatus")
    public String changeOrderStatus(Order order) {
        return adminOrderService.changeOrderStatus(order,request);
    }
}
