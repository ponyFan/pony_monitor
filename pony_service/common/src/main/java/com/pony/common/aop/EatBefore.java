package com.pony.common.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zelei.fan on 2017/7/10.
 *
 * 前置增强
 */
public class EatBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String arg = (String)objects[0];
        System.out.println("前置增强：吃"+arg+"前先洗手");
    }
}
