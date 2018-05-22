package com.gegehydro.store.controller.admin;

import com.gegehydro.store.service.admin.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 信息中心控制类
 *
 * @author sunhao
 * @date 2018/3/16
 */
@RestController
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/adminData", produces = "application/json;charset=UTF-8")
public class AdminDataController {
    private AdminDataService adminDataService;

    @Autowired
    public AdminDataController(AdminDataService adminDataService) {
        this.adminDataService = adminDataService;
    }

    @GetMapping("/loginPopulation")
    public String loginPopulation() {
        return adminDataService.loginPopulation();
    }
}
