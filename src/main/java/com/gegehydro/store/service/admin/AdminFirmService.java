package com.gegehydro.store.service.admin;

import com.gegehydro.store.entity.Firm;

import javax.servlet.http.HttpServletRequest;

/**
 * 厂商服务接口
 *
 * @author sunhao
 * @date 2018/1/29
 */
public interface AdminFirmService {
    /**
     * 添加新厂商
     *
     * @param firm    厂商
     * @param request request
     * @return 主键id
     */
    String addFirm(Firm firm, HttpServletRequest request);

    /**
     * 移除厂商
     *
     * @param firm 厂商
     * @return 移除结果
     */
    String removeFirm(Firm firm);

    /**
     * 获取厂商
     *
     * @param firm 厂商
     * @return 厂商
     */
    String getFirm(Firm firm);
}
