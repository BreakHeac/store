package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminFirmDao;
import com.gegehydro.store.entity.Firm;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.service.admin.AdminFirmService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ComponentUtil;
import com.gegehydro.store.util.IpUtil;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 厂商服务实现类
 *
 * @author sunhao
 * @date 2018/1/29
 */
@Service
public class AdminFirmServiceImpl implements AdminFirmService {
    private AdminFirmDao adminFirmDao;
    private ComponentUtil componentUtil;
    private int result;

    @Autowired
    public AdminFirmServiceImpl(AdminFirmDao adminFirmDao, ComponentUtil componentUtil) {
        this.adminFirmDao = adminFirmDao;
        this.componentUtil = componentUtil;
    }

    @Override
    public String addFirm(Firm firm, HttpServletRequest request) {
        result = adminFirmDao.count(firm);
        if (0 < result) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.CONFLICT_ERROR));
        }
        firm.setOperateDate(new Date());
        firm.setOperateIp(IpUtil.getIpAddress(request));
        Users users = new Users();
        users.setId(componentUtil.getUserIdFromRequest(request));
        firm.setUsers(users);
        result = adminFirmDao.insert(firm);
        if (0 < result) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, firm));
        }
        return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
    }

    @Override
    public String removeFirm(Firm firm) {
        result = adminFirmDao.update(firm);
        if (0 < result) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS));
        }
        return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
    }

    @Override
    public String getFirm(Firm firm) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, adminFirmDao.findList(firm)));
    }
}
