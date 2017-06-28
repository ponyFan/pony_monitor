package com.pony.common.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zelei.fan on 2017/6/28.
 */
public class BeanTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Person person = (Person)applicationContext.getBean("test");
        Person person1 = (Person)applicationContext.getBean("test");
        Person person2 = (Person)applicationContext.getBean("test");
        System.out.println(person);
        System.out.println(person1);

        Person person3 = new Person("哈哈", 12, "男");
        /*当bean是默认情况下时，打印的是true，但是如果把这个bean改成多例则是false（即加上scope="prototype"）*/
        System.out.println(person == person1);
        /*注意：只要bean的id不同就是不同的bean，不管所对应的类是否相同*/
        System.out.println(person == person2);
        /*下面则是返回的false，因为这是在内存空间里重新new的一个对象实例*/
        System.out.println(person == person3);



    }
}
