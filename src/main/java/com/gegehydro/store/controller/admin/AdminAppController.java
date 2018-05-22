package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.Apps;
import com.gegehydro.store.service.admin.AdminAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员商品控制类
 *
 * @author sunhao
 * @date 2018/2/14
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/adminApp", produces = "application/json;charset=UTF-8")
public class AdminAppController {
    private AdminAppService adminAppService;
    private HttpServletRequest request;

    @Autowired
    public AdminAppController(AdminAppService adminAppService, HttpServletRequest request) {
        this.adminAppService = adminAppService;
        this.request = request;
    }

    @PutMapping(value = "/app")
    public String app(Apps apps) {
        return adminAppService.app(apps, request);
    }

    @GetMapping(value = "/getApp")
    public String getApp(Apps apps) {
        return adminAppService.getApp(apps);
    }
}
