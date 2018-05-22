package com.gegehydro.store.dao.admin;

import com.gegehydro.store.dao.BaseDao;
import com.gegehydro.store.entity.assistant.VueAssistant;

/**
 * 信息中心Dao
 *
 * @author sunhao
 * @date 2018/3/16
 */
public interface AdminDataDao extends BaseDao {
    /**
     * 登录人次
     *
     * @return 人次
     */
    VueAssistant[] loginPopulation();
}
