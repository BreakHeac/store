package com.gegehydro.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.AppsDao;
import com.gegehydro.store.entity.Apps;
import com.gegehydro.store.service.AppsService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ResultStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author sunhao
 * create on 2017/12/3
 */
@Service
@SuppressWarnings("unchecked")
public class AppsServiceImpl implements AppsService {
    private AppsDao appsDao;
    private BaseResp baseResp;

    @Autowired
    public void setAppsDao(AppsDao appsDao) {
        this.appsDao = appsDao;
    }

    @Override
    public String getAppsList(Apps apps) {
        PageHelper.startPage(apps.getPageNum(), apps.getPageSize());
        ArrayList<Apps> appsList = appsDao.findList(apps);
        PageInfo<Apps> appsPageInfo = new PageInfo<>(appsList);
        baseResp = new BaseResp(ResultStatus.SUCCESS,appsPageInfo);
        return JSON.toJSONString(baseResp);
    }

    @Override
    public String getAppDetail(Apps apps) {
        Apps app = appsDao.find(apps);
        baseResp =  apps != null ? new BaseResp(ResultStatus.SUCCESS,app) : new BaseResp(ResultStatus.EMPTY);
        return JSON.toJSONString(baseResp);
    }
}
