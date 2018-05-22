package com.gegehydro.store.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunhao
 * Create on 2017/12/20
 */
public enum ResultStatus {
    /**
     * 拒绝访问
     */
    REJECT_ERROR(401, "not allow"),
    /**
     * 权限不足
     */
    FORBID_ERROR(403, "insufficient permissions"),
    /**
     * 失败
     */
    FAIL(-1, "common fail"),
    /**
     * 添加失败
     */
    ADD_FAIL(101, "fail to add"),
    /**
     * 刷新失败
     */
    REFRESH_FAIL(102, "fail to refresh"),
    /**
     * 成功
     */
    SUCCESS(0, "success"),
    /**
     * 字符串格式错误
     */
    FORMAT_ERROR(10, "wrong format"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(20, "wrong password"),
    /**
     * 密码不匹配
     */
    PASSWORD_DIFFERENT(21, "password not match"),
    /**
     * 不存在错误
     */
    EXIST_ERROR(30, "not exist"),
    /**
     * 冲突错误，即已存在
     */
    CONFLICT_ERROR(31, "already exist"),
    /**
     * 资源已被使用
     */
    USED(32, "be used"),
    /**
     * 资源为空
     */
    EMPTY(33, "resource is empty"),
    /**
     * 资源太大
     */
    LARGE(34, "too big");
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultStatus.class);
    private int code;
    private String msg;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static int getCode(String define) {
        try {
            return ResultStatus.valueOf(define).code;
        } catch (IllegalArgumentException e) {
            LOGGER.error("undefined error code: {}", define);
            return FAIL.getErrorCode();
        }
    }

    public static String getMsg(String define) {
        try {
            return ResultStatus.valueOf(define).msg;
        } catch (IllegalArgumentException e) {
            LOGGER.error("undefined error code: {}", define);
            return FAIL.getErrorMsg();
        }

    }

    public static String getMsg(int code) {
        for (ResultStatus err : ResultStatus.values()) {
            if (err.code == code) {
                return err.msg;
            }
        }
        return "errorCode not defined ";
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getErrorCode() {
        return code;
    }

    public String getErrorMsg() {
        return msg;
    }
}
