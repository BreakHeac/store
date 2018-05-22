package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.Warehouse;
import com.gegehydro.store.service.admin.AdminWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 库存管理控制类
 *
 * @author sunhao
 * @date 2018/1/21
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/adminWarehouse", produces = "application/json;charset=UTF-8")
public class AdminWarehouseController {
    private AdminWarehouseService adminWarehouseService;
    private HttpServletRequest request;

    @Autowired
    public AdminWarehouseController(AdminWarehouseService adminWarehouseService, HttpServletRequest request) {
        this.adminWarehouseService = adminWarehouseService;
        this.request = request;
    }

    @GetMapping(value = "/getWarehouse")
    public String getWarehouse(Warehouse warehouse) {
        return adminWarehouseService.getWarehouse(warehouse);
    }

    @PutMapping(value = "/addWarehouse")
    public String addWarehouse(Warehouse warehouse, String[] cdKeys) {
        return adminWarehouseService.addWarehouse(warehouse, cdKeys, request);
    }

    @DeleteMapping(value = "/deleteWarehouse")
    public String deleteWarehouse(Warehouse warehouse) {
        return adminWarehouseService.deleteWarehouse(warehouse);
    }

}
