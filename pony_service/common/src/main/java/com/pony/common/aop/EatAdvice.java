package com.pony.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatAdvice implements MethodInterceptor{

    private final Pattern METHOD = Pattern.compile("^insert.*");


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        /*获取参数*/
        Object o = methodInvocation.getArguments()[0];
        /*获取方法名*/
        String name = methodInvocation.getMethod().getName();
        /*如果是该方法, 对参数对象中的creator属性进行统一的处理*/
        if (METHOD.matcher(name).matches()){
            PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(o.getClass(), "creator");
            Method writeMethod = descriptor.getWriteMethod();
            writeMethod.invoke(o, "haha");
        }
        System.out.println("开始前先洗手ssssssssssssssssssss"+(String)o);
        return methodInvocation.proceed();
    }
}
