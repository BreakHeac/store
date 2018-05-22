package com.gegehydro.store.dao;

import com.gegehydro.store.entity.Users;
import com.gegehydro.store.entity.assistant.VueAssistant;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;


/**
 * 用户注册、登录等等一系列相关操作
 *
 * @author sunhao
 * create on 2017/11/15
 */
public interface UserDao extends BaseDao<Users> {

    /**
     * 依据邮箱查询users表中该邮箱的数量
     *
     * @param users 用户实例
     * @return 数量
     */
    int selectQuantityByEmail(Users users);

    /**
     * 插入用户角色，默认普通用户
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 更新行数
     */
    int insertRoles(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 依据用户邮箱和密码获取用户实例（id，邮箱，状态）
     *
     * @param users 用户实例
     * @return 用户实例（id，邮箱，状态）
     */
    Users selectUserByEmail(Users users);

    /**
     * 通过邮箱查找用户
     *
     * @param email 邮箱
     * @return 用户
     */
    Users findByEmail(String email);

    /**
     * 依据邮箱获取用户id
     *
     * @param email 邮箱
     * @return id
     */
    int findIdByEmail(String email);
}
