package com.pony.common.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by zelei.fan on 2017/7/17.
 */
@Component
@Aspect
public class AspectTest {

    /*拦截PersonImpl中所有方法*/
    @Before("execution(* com.pony.common.aop.aspect.PersonImpl.*(..))")
    public void beforeEat(){
        System.out.println("-------------------这里是前置增强，吃饭之前先洗手！--------------------");
    }

    /*拦截项目中所有eatLunch方法*/
    @After("execution(* eatLunch(..))")
    public void afterEat(){
        System.out.println("-------------------这里是后置增强，午饭吃完要睡午觉！--------------------");
    }

    /*环绕增强*/
    @Around("execution(* com.pony.common.aop.aspect.PersonImpl.eatSupper(..))")
    public Object aroundEat(ProceedingJoinPoint point) throws Throwable {
        System.out.println("-------------------这里是环绕增强，吃晚饭前先玩一玩！-------------------");
        Object proceed = point.proceed();
        System.out.println("-------------------这里是环绕增强，晚饭吃完后要得睡觉了！-------------------");
        return proceed;
    }


    @AfterReturning(returning = "o", pointcut = "execution(* com.pony.common.aop.aspect.PersonImpl.drink(..))")
    public void log(Object o){
        System.out.println("-------------------这里是AfterReturning增强-------------------");
        System.out.println("获取小Baby正在喝的饮料"+o);
        System.out.println("记录每天喝的饮料容量");
    }
}
