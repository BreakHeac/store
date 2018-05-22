package com.gegehydro.store.controller.admin;

import com.gegehydro.store.entity.Firm;
import com.gegehydro.store.service.admin.AdminFirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 厂商控制类
 *
 * @author sunhao
 * @date 2018/1/29
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/firm", produces = "application/json;charset=UTF-8")
public class AdminFirmController {
    private AdminFirmService adminFirmService;

    @Autowired
    public AdminFirmController(AdminFirmService adminFirmService) {
        this.adminFirmService = adminFirmService;
    }

    @PutMapping(value = "/addFirm")
    public String addFirm(Firm firm, HttpServletRequest request) {
        return adminFirmService.addFirm(firm, request);
    }

    @DeleteMapping(value = "/removeFirm")
    public String removeFirm(Firm firm) {
        return adminFirmService.removeFirm(firm);
    }

    @GetMapping(value = "/getFirms")
    public String getFirms(Firm firm) {
        return adminFirmService.getFirm(firm);
    }
}
