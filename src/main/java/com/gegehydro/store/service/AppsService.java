package com.gegehydro.store.service;

import com.gegehydro.store.entity.Apps;

/**
 * @author sunhao
 * create on 2017/12/3
 */
public interface AppsService {
    /**
     * 获取app简单列表，仅包括基础信息
     *
     * @param apps app实例
     * @return JSON数组
     */
    String getAppsList(Apps apps);

    /**
     * 获取单个app的详细信息
     *
     * @param apps app实例
     * @return app信息
     */
    String getAppDetail(Apps apps);
}
