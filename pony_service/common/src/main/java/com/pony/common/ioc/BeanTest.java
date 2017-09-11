package com.pony.common.ioc;

import com.pony.common.ioc.model.AutowireTest;
import com.pony.common.ioc.model.CollectionInjection;
import com.pony.common.ioc.model.Person;
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
        * 1、byName: 在整个spring环境中查找，不能有重复的<bean>标签的id，如果在spring环境中存在多个id一样的bean则会报错，但是id相同在spring环境中是不能存在的，只要出现spring在编码是就会提示
        * 2、byType：在整个spring环境中查找，不能有重复的<bean>标签的class，如果在spring环境中存在多个类型相同（即class相同）的bean则会报错，而类型相同的bean是允许存在的
        *
        * 总结：1、根据name匹配，这个name是对应AutowireTest类中的private Teacher teacher;private Student student;的teacher和student
        *      只要spring环境中存在id叫teacher和id叫student的bean，就会自动注入，不管bean的类型，如果bean的类型不是所需要的类型则异常
        *
        *      2、根据type匹配，type则是对应Teacher和Student，只要是class对应Teacher或者是Student则自动注入，不管id叫什么，
        *      但是需要注意的是不能出现多个相同类型的bean，否则异常,所以说当前类所依赖的bean必须是单例的，如果非单例的话整个spring
        *      环境中肯定不止一个依赖的类型相同的bean；
        *
        * */
        AutowireTest auto1 = (AutowireTest)applicationContext.getBean("autoTest1");
        AutowireTest auto2 = (AutowireTest)applicationContext.getBean("autoTest2");
        /*AutowireTest auto4 = (AutowireTest)applicationContext.getBean("teacher");*//*这个bean是来测依赖对象为多例的时候，byType装配bean会异常，因为存在多个类型相同的bean*/
        AutowireTest auto3 = (AutowireTest)applicationContext.getBean("autoTest3");
        System.out.println("ref装配bean："+ auto1);
        System.out.println("byName装配bean："+ auto2);
        System.out.println("byType装配bean："+ auto3);


        /*方法注入：主要是用于一个单列bean依赖于一个多列bean的情况下；因为单列bean在初始化的时候实例化后不会再new实例，这样就导致了它所依赖的多例bean也是用的同一个实例；
        *          但是实际需要的是每次调用该单例bean的时候，它所依赖的多例bean都是重新new的，这样就用到了方法注入；
        * lookup：重写容器中bean的抽象方法，返回查找容器中其他bean的结果；
        *
        * 如果使用方法注入注入的bean是一个单例的话，下面打印的是true，这没什么意义，因为是单例的，所以在整个环境中只存在一个实例，就算是用了lookup注入每次注入的也都是同一个实例
        * 但是多例就不一样了，每次注入都是一个新的实例
        * */
        Data data = applicationContext.getBean("data", Data.class);
        Person person4 = data.getPerson();
        Person person5 = data.getPerson();
        System.out.println(person4);
        System.out.println(person4 == person5);

        ThreadLocal threadLocal = new ThreadLocal();
    }
}
