package com.pony.common.aop.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zelei.fan on 2017/7/17.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Person person = (Person) context.getBean("aspectPerson");
        person.eatBreakfast();
        System.out.println("===================================================");
        person.eatLunch();
        System.out.println("===================================================");
        person.eatSupper();
        System.out.println("===================================================");
        person.drink("可乐");
        System.out.println("===================================================");
    }
}
