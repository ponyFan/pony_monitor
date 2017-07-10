package com.pony.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Objects;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        String arg = (String)args[0];
        System.out.println("环绕增强：吃"+arg+"之前");
        Object proceed = methodInvocation.proceed();
        System.out.println("环绕增强：吃"+arg+"之后");
        return proceed;
    }
}
