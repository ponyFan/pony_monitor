package com.pony.common.proxy.CGlib;

import com.pony.common.proxy.PerformMonitor;
import com.pony.common.proxy.PerformService;
import com.pony.common.proxy.PerformServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zelei.fan on 2017/7/7.
 */
public class CGLibTest {

    public static void main(final String[] args) {
        final PerformServiceImpl performService = new PerformServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PerformServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                PerformMonitor.start(objects);
                return method.invoke(performService, objects);
            }
        });

        PerformService performService1 = (PerformService)enhancer.create();
        performService1.start("aa");
        performService1.end("cc");
    }
}
