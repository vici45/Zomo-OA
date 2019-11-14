package com.zomo.oa.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
public class ServiceResponse<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ServiceResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResponse(Integer code) {
        this.code = code;
    }

    public ServiceResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    @JsonIgnore
    public boolean isSuccess(){
        return code==ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServiceResponse<T> createBySuccess(){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServiceResponse<T> createBySuccessMsg(String msg){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServiceResponse<T> createBySuccess(T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServiceResponse<T> createBySuccessMsg(String msg,T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServiceResponse<T> createByError(){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg());
    }

    public static <T> ServiceResponse<T> createByErrorMsg(String msg){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServiceResponse<T> createByErrorCodeMsg(Integer errorCode,String msg){
        return new ServiceResponse<T>(errorCode,msg);
    }
}
