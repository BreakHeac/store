package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.OrderItem;
import com.gegehydro.store.service.admin.AdminOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细控制类
 *
 * @author sunhao
 * @date 2018/2/22
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/adminOrderDetail", produces = "application/json;charset=UTF-8")
public class AdminOrderDetailController {
    private AdminOrderItemService adminOrderItemService;

    @Autowired
    public AdminOrderDetailController(AdminOrderItemService adminOrderItemService) {
        this.adminOrderItemService = adminOrderItemService;
    }

    @GetMapping(value = "/orderDetail")
    public String orderDetail(OrderItem orderItem) {
        return adminOrderItemService.orderDetail(orderItem);
    }
}
