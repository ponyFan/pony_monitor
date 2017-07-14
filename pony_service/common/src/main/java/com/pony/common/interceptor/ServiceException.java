package com.pony.common.interceptor;

/**
 * Created by zelei.fan on 2017/7/13.
 */
public class ServiceException extends RuntimeException {

    private int code;

    private String message;

    public ServiceException(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public int getCode(){
        return code;
    }

}
