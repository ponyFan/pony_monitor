package com.pony.common.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zelei.fan on 2017/6/28.
 */
public class BeanTest {

    private static Data data;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        /******************************注入 start*********************************/
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

        /*接口注入
        * BeanTest类是依赖于Data这个接口的
        *       从传统的方式看，如果需要使用Data接口的实例，就需要在代码中创建实现类的实例，然后赋给Data；
        *    这样的话再编译时BeanTest就依赖Data这个接口
        *       接口注入的话，就能实现他们在编译时的分离，根据配置的实现类的类名，动态加载实现类；如下
        * */
        try {
            Object o = Class.forName("com.pony.common.ioc.DataImpl").newInstance();
            data = (Data)o;
            data.connect();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        /*集合注入*/
        CollectionInjection injection = (CollectionInjection)applicationContext.getBean("collection");
        System.out.println(injection.getList());
        System.out.println(injection.getMap());

        /******************************注入 end*********************************/

        /******************************自动装配 start*********************************/
        /*主要有一下几种装配方式：
        * 1、byName: 在整个spring环境中查找，不能有重复的<bean>标签的id
        * 2、byType：在整个spring环境中查找，不能有重复的<bean>标签的class
        * */

    }
}
