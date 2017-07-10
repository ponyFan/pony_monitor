package com.pony.common.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatException implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception ex) throws Throwable {
        System.out.println("拦截到异常，方法method:"+method.getName());
    }
}
