package com.monitor.datamonitorsystem.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 外放接口统一返回
 * @author zxl
 * @创建时间 2019-12-18 15:03
 */
@Setter
@Getter
@JsonSerialize
public class BaseResponse<T> implements Serializable {
    /** 请求成功返回码为：00 */
    public static final String successCode = "0";
    /** 请求成功返回信息为：成功 */
    private static final String successMsg = "成功";
    /** 请求失败返回码为：-1 */
    private static final String errorCode = "-1";
    /** 返回码 */
    private String code;
    /** 返回描述 */
    private String msg;
    /** 返回数据 */
    private T data;

    public BaseResponse(){
        this.code = successCode;
        this.msg = successMsg;
    }

    public BaseResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public BaseResponse(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponse(T data){
        this.code = successCode;
        this.msg = successMsg;
        this.data = data;
    }

    public static BaseResponse error(String msg){
        return new BaseResponse(errorCode,msg);
    }

    public BaseResponse success(T data){
        return new BaseResponse(data);
    }


}
