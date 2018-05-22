package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminAppDao;
import com.gegehydro.store.dao.admin.AdminAppImgDao;
import com.gegehydro.store.entity.AppImgs;
import com.gegehydro.store.entity.Apps;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.service.admin.AdminAppService;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.ComponentUtil;
import com.gegehydro.store.util.IpUtil;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 管理员商品服务实现类
 *
 * @author sunhao
 * @date 2018/2/14
 */
@Service
public class AdminAppServiceImpl implements AdminAppService {
    private AdminAppDao adminAppDao;
    private AdminAppImgDao adminAppImgDao;
    private ComponentUtil componentUtil;

    @Autowired
    public AdminAppServiceImpl(AdminAppDao adminAppDao, AdminAppImgDao adminAppImgDao, ComponentUtil componentUtil) {
        this.adminAppDao = adminAppDao;
        this.adminAppImgDao = adminAppImgDao;
        this.componentUtil = componentUtil;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String app(Apps apps, HttpServletRequest request) {
        int result;
        Date date = new Date();
        String operaterIp = IpUtil.getIpAddress(request);
        int operatorId = componentUtil.getUserIdFromRequest(request);
        Users users = new Users();
        users.setId(operatorId);
        try {
            apps.setOperateIp(operaterIp);
            apps.setOperateDate(date);
            apps.setUsers(users);
            if (0 < apps.getId()) {
                result = adminAppDao.update(apps);
            } else {
                result = adminAppDao.insert(apps);
            }
            if (0 >= result) {
                throw new RuntimeException();
            }
            if (null != apps.getImg() && 0 < apps.getImg().length) {
                AppImgs appImgs = new AppImgs();
                appImgs.setId(apps.getId());
                result = adminAppImgDao.update(appImgs);
                if (0 > result) {
                    throw new RuntimeException();
                }
                for (String img : apps.getImg()) {
                    appImgs = new AppImgs();
                    appImgs.setId(apps.getId());
                    appImgs.setImg(img);
                    appImgs.setUsers(users);
                    appImgs.setOperateDate(date);
                    appImgs.setOperateIp(operaterIp);
                    result = adminAppImgDao.insert(appImgs);
                    if (0 >= result) {
                        throw new RuntimeException();
                    }
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JSON.toJSONString(new BaseResp<>(ResultStatus.ADD_FAIL));
        }
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, apps.getId()));
    }

    @Override
    public String getApp(Apps apps) {
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, adminAppDao.findUserList(apps)));
    }
}
