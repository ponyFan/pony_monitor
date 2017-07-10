package com.pony.common.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatAfter implements AfterReturningAdvice{
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        String arg = (String)objects[0];
        System.out.println("后置增强：吃完"+arg+"后要擦嘴");
    }
}
