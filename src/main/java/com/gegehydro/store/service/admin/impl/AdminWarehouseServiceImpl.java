package com.gegehydro.store.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.dao.admin.AdminWarehouseDao;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.Warehouse;
import com.gegehydro.store.service.admin.AdminWarehouseService;
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
 * 库存管理服务实现类
 *
 * @author sunhao
 * @date 2018/1/21
 */
@Service
public class AdminWarehouseServiceImpl implements AdminWarehouseService {
    private AdminWarehouseDao adminWarehouseDao;
    private ComponentUtil componentUtil;

    @Autowired
    public AdminWarehouseServiceImpl(AdminWarehouseDao adminWarehouseDao, ComponentUtil componentUtil) {
        this.adminWarehouseDao = adminWarehouseDao;
        this.componentUtil = componentUtil;
    }

    @Override
    public String getWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addWarehouse(Warehouse warehouse, String[] cdKeys, HttpServletRequest request) {
        if (0 >= warehouse.getAppId()) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
        if (0 >= cdKeys.length) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EMPTY));
        }
        warehouse.setOperateDate(new Date());
        Users users = new Users();
        users.setId(componentUtil.getUserIdFromRequest(request));
        warehouse.setUsers(users);
        warehouse.setOperateIp(IpUtil.getIpAddress(request));
        int result;
        for (String cdKey : cdKeys) {
            warehouse.setKey(cdKey);
            result = adminWarehouseDao.insert(warehouse);
            if (0 >= result) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS));
    }

    @Override
    public String deleteWarehouse(Warehouse warehouse) {
        return null;
    }
}
