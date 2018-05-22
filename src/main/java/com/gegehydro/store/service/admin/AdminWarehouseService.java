package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.Warehouse;

import javax.servlet.http.HttpServletRequest;

/**
 * 库存管理服务类
 *
 * @author sunhao
 * @date 2018/1/21
 */
public interface AdminWarehouseService {
    /**
     * 获取库存情况
     *
     * @param warehouse 库存
     * @return 情况
     */
    String getWarehouse(Warehouse warehouse);

    /**
     * 添加库存
     *
     * @param warehouse 库存
     * @param cdKeys    keys
     * @param request   request
     * @return 插入结果
     */
    String addWarehouse(Warehouse warehouse, String[] cdKeys, HttpServletRequest request);

    /**
     * 移除key
     *
     * @param warehouse 库存
     * @return 移除结果
     */
    String deleteWarehouse(Warehouse warehouse);
}
