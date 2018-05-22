package com.gegehydro.store.controller;

import com.gegehydro.store.entity.Apps;
import com.gegehydro.store.service.AppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunhao
 * create on 2017/12/3
 */
@CrossOrigin
@RequestMapping(value = "/apps", produces = "application/json;charset=UTF-8")
@RestController
public class AppsController {
    private AppsService appsService;

    @Autowired
    public AppsController(AppsService appsService) {
        this.appsService = appsService;
    }

    @GetMapping(value = {"getAppsList", "getAppsList/**"})
    public String getAppsList(Apps apps) {
        return appsService.getAppsList(apps);
    }

    @GetMapping(value = {"getAppDetail", "getAppDetail/**"})
    public String getAppDetail(Apps apps) {
        return appsService.getAppDetail(apps);
    }
}
