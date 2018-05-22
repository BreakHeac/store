package com.gegehydro.store.dao;

import com.gegehydro.store.entity.assistant.VueAssistant;

import java.util.ArrayList;

/**
 * @author sunhao
 * create on 2017/12/1
 */
public interface BaseDao<T> {
    /**
     * 协助前端使用dao
     *
     * @param entity 泛类
     * @return 列表
     */
    ArrayList<VueAssistant> findUserList(T entity);

    /**
     * 数数量
     *
     * @param entity 泛类
     * @return 数量
     */
    int count(T entity);

    /**
     * 插入数据
     *
     * @param entity 泛类
     * @return 更新行数
     */
    int insert(T entity);

    /**
     * 更新数据
     *
     * @param entity 泛类
     * @return 更新行数
     */
    int update(T entity);

    /**
     * 获取列表
     *
     * @param entity 泛类
     * @return 数组
     */
    ArrayList<T> findList(T entity);

    /**
     * 获取单个类
     *
     * @param entity 泛类
     * @return 泛类
     */
    T find(T entity);
}
