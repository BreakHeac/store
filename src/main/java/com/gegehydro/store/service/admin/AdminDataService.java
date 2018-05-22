package com.gegehydro.store.service.admin;

/**
 * 信息中心服务接口
 *
 * @author sunhao
 * @date 2018/3/16
 */
public interface AdminDataService {
    /**
     * 获取7日内用户登录人次
     *
     * @return 人次
     */
    String loginPopulation();
}
