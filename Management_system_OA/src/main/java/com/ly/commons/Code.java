package com.ly.commons;

/**
 * 根据响应场景添加枚举类型
 */
public enum Code {

    SUCCESS(1000,"请求成功"),
    FAIL(9999,"请求失败"),
    USERNAME_NOT_FOUND(2001,"用户不存在"),
    LOGIN_SUCCESS(2002,"登录成功"),
    LOGOUT_SUCCESS(2003,"登出成功"),
    USER_DISABLED(2004,"账户被禁用"),
    USER_EXPIRED(2005,"账号过期"),
    PSW_EXPIRED(2006,"密码过期"),
    PSW_ERROR(2007,"密码错误"),
    USER_LOCKED(2008,"账户被锁定"),
    TOKEN_ILLEGAL(3001,"token非法"),
    TOKEN_NOT_FOUND(3002,"token不存在"),
    TOKEN_HAS_CLEAN(3003,"token被注销"),
    NOT_HAS_AUTH(4001,"您没有权限"),
    NOT_FOUND_PARAM(5001,"操作成功，但写入参数为空"),
    NOT_GET_PARAM(5002,"操作成功，但没有查询到信息"),
    GET_SUCCESS(5003,"信息查询成功"),
    ADD_SUCCESS(5004,"信息添加成功"),
    CUT_SUCCESS(5005,"信息删除成功"),
    EDIT_SUCCESS(5006,"信息修改成功"),
    GET_FAIL(5007,"信息查询失败"),
    ADD_FAIL(5008,"信息添加失败"),
    CUT_FAIL(5009,"信息删除失败"),
    EDIT_FAIL(5010,"信息修改失败"),
    EXPORT_SUCCESS(6001,"信息导出成功"),
    EXPORT_FAIL(6002,"信息导出失败"),
    EXPORT_RECORDS_FAIL(7003,"信息导出记录失败"),
    ;
    private Integer code;
    private String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
