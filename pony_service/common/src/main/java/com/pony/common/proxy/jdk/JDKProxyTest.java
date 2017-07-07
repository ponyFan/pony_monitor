package com.pony.common.proxy.jdk;

import com.pony.common.proxy.PerformMonitor;
import com.pony.common.proxy.PerformService;
import com.pony.common.proxy.PerformServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zelei.fan on 2017/7/7.
 *
 * jdk动态代理
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        final PerformServiceImpl target = new PerformServiceImpl();
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*在执行前进行通知*/
                PerformMonitor.start(args);
                Object invoke = method.invoke(target, args);
                PerformMonitor.end(args);
                return invoke;
            }
        });
        PerformService performService = (PerformService)o;
        performService.start("qq");
        performService.end("sss");
    }
}
