package com.pony.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        /*获取参数*/
        Object o = methodInvocation.getArguments()[0];
        /*获取方法名*/
        String name = methodInvocation.getMethod().getName();
        System.out.println("开始前先洗手ssssssssssssssssssss"+(String)o);
        return methodInvocation.proceed();
    }
}
