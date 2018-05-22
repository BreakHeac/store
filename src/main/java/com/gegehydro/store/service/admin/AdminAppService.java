package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.Apps;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员商品服务接口
 *
 * @author sunhao
 * @date 2018/2/14
 */
public interface AdminAppService {
    /**
     * 商品新增/修改
     *
     * @param apps    apps实例
     * @param request request
     * @return 结果
     */
    String app(Apps apps, HttpServletRequest request);

    /**
     * 获取商品
     *
     * @param apps apps实例
     * @return appList
     */
    String getApp(Apps apps);
}
