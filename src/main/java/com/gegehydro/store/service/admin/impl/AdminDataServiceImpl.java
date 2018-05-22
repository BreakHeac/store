package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminDataDao;
import com.gegehydro.store.service.admin.AdminDataService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 信息中心服务实现类
 *
 * @author sunhao
 * @date 2018/3/16
 */
@Service
public class AdminDataServiceImpl implements AdminDataService {
    private AdminDataDao adminDataDao;

    @Autowired
    public AdminDataServiceImpl(AdminDataDao adminDataDao) {
        this.adminDataDao = adminDataDao;
    }

    @Override
    public String loginPopulation() {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, adminDataDao.loginPopulation()));
    }
}
