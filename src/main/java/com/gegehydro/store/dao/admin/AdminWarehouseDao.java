package com.gegehydro.store.dao.admin;

import com.gegehydro.store.dao.BaseDao;
import com.gegehydro.store.entity.Warehouse;

import java.util.ArrayList;

/**
 * 库存管理dao
 *
 * @author sunhao
 * @date 2018/1/22
 */
public interface AdminWarehouseDao extends BaseDao<Warehouse> {
    /**
     * 数出各app全部可用库存
     *
     * @return 各app数量
     */
    ArrayList<Warehouse> countAll();
}
