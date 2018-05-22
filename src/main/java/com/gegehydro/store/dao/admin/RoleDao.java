package com.gegehydro.store.dao.admin;

import com.gegehydro.store.dao.BaseDao;
import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.assistant.VueAssistant;

import java.util.ArrayList;

/**
 * @author sunhao
 * Create on 2017/12/26
 */
public interface RoleDao extends BaseDao<VueAssistant> {

    /**
     * 获取对应角色下用户列表
     *
     * @param vueAssistant 角色
     * @return 对应角色下用户列表
     */
    ArrayList<Users> selectUsersByRole(VueAssistant vueAssistant);

    /**
     * 更改用户角色
     *
     * @param vueAssistant 角色
     * @return 更改结果
     */
    int updateUserRole(VueAssistant vueAssistant);

    /**
     * 获取用户的所属角色
     *
     * @param users 用户
     * @return 角色
     */
    String selectRoleByUser(Users users);
}
