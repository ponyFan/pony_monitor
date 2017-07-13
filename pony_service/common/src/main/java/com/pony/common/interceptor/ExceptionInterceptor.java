package com.pony.common.interceptor;

import com.pony.common.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zelei.fan on 2017/7/13.
 */
@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public BaseResponse consoleException(ServiceException e){
        return new BaseResponse(e.getCode(), e.getMessage());
    }
}
