package com.pony.common.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zelei.fan on 2017/7/7.
 * spring提供了5种类型的通知：
 * Before Advice（前置通知）、
 * After Returning Advice（后置通知）、
 * Interception Around Advice（周围通知)、
 * Throws Advice（异常通知）、
 * Introduction Advice（引介通知）
 */
public class AopTest {

    public static void main(String[] args) {
        /*************1、前置增强****************/
        EatServiceImpl eatService = new EatServiceImpl();
        EatBefore eatBefore = new EatBefore();

        ProxyFactory factory = new ProxyFactory();
        /*设置代理目标，默认使用cglib代理*/
        factory.setTarget(eatService);
        /*添加增强类*/
        factory.addAdvice(eatBefore);

        /*下面是使用接口代理，默认是用jdk的代理，setOptimize改为true的话是使用cglib*/
        /*factory.setInterfaces(eatService.getClass().getInterfaces());
        factory.setOptimize(true);*/

        EatService proxy = (EatService)factory.getProxy();
        proxy.meal("香蕉");
        proxy.lunch("苹果");

        /*************2、后置增强****************/
        EatAfter after = new EatAfter();
        factory.addAdvice(after);

        EatService proxy1 = (EatService) factory.getProxy();
        proxy1.meal("香蕉");
        proxy1.lunch("苹果");

        /*************3、环绕增强****************/
        EatInterceptor eatInterceptor = new EatInterceptor();
        factory.addAdvice(eatInterceptor);

        EatService proxy2 = (EatService) factory.getProxy();
        proxy2.meal("香蕉");
        proxy2.lunch("苹果");

        /*************4、异常增强****************/
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        EatService eat = (EatService) applicationContext.getBean("eat");
        /*eat.exception();*/

        /*拦截*/
        EatServiceImpl eatServiceImpl = new EatServiceImpl();
        eatServiceImpl.meal("aaa");
        eatServiceImpl.lunch("ccc");
    }
}
