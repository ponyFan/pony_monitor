package com.pony.common;

/**
 * Created by zelei.fan on 2017/5/26.
 */
public class BaseResponse extends BaseModel {

    private static final long serialVersionUID = -6169425538847887747L;

    private int code;

    private String message;

    private Object data;

    private static int DEFAULT_CODE = 200;

    private static String DEFAULT_MESSAGE = "success";

    public BaseResponse(){
        this(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    public BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, Object data){
        this(code, message);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
