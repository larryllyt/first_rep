package com.ly.vo;

import com.ly.commons.Code;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResultVo
 * @Description TODO 统一响应结果类
 * @Author 赖昱
 * @Date 2023/5/16 - 14:57
 */
public class ResultVo implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 承载对象数据
     */
    private Object object;

    /**
     * 承载list集合数据
     */
    private List list;

    /**
     * token
     */
    private String accessToken;

    /**
     * 通过枚举类Code来统一响应码和响应信息
     * @param code
     */
    public void setEnumCode(Code code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public static ResultVo SUCCESS(){
        ResultVo resultVo = new ResultVo();
        resultVo.setEnumCode(Code.SUCCESS);
        return resultVo;
    }

    public static ResultVo FAIL(){
        ResultVo resultVo = new ResultVo();
        resultVo.setEnumCode(Code.FAIL);
        return resultVo;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", object=" + object +
                ", list=" + list +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
