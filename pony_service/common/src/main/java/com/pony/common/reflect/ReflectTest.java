package com.pony.common.reflect;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zelei.fan on 2017/6/21.
 * 反射
 * 首先了解类的生命周期，装载，连接，初始化，卸载类型
 * 1、创建类的实例
 * 2、方法某个类或接口的静态变量，或者对该静态变量赋值
 * 3、调用类的静态方法
 * 4、反射（如 Class.forName(“com.itzhai.Test”)）
 * 5、初始化一个类的子类
 * 6、Java虚拟机启动时被标明为启动类的类（Main Class）
 * 类的初始化：
 *          1、默认的构造函数。或者带参数的构造函数
 *          2、实现InitializingBean，重写afterPropertiesSet方法
 *          3、在spring.xml中配置该类的bean，并且指定init的方法
 */
public class ReflectTest {

    public void classLoadTest(){
        /*类的加载,打印出来的都相同，说明类只加载了一次
        * 1、类.class
        * 2、Class.forName
        * 3、实例.getClass
        * */
        /*1,类.class不对类进行初始化动作，其余两个会，但是如果已经初始化了，就不需要再次初始化*/
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        /*2*/
        try {
            Class<?> aClass = Class.forName("com.pony.common.reflect.Person");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*3*/
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println(aClass);
    }

    public void classInstanceTest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        /*实例化
        * 使用new的时候，该类还没有被加载；但是使用newInstance时必须先确保类已经被加载了
        * 其实实质上new就是被分成了两步，首先是加载class对象，然后再实例化；
        * 区别：
        *     1、newInstance: 弱类型。低效率。只能调用无参构造。 new: 强类型。相对高效。能调用任何public构造
        *    2、newInstance()是实现IOC、反射、面对接口编程和依赖倒置等技术方法的必然选择，new只能实现具体类的实例化，不适合于接口编程。
        * */
        Person person = Person.class.newInstance();
        System.out.println(person);

        Object o = Class.forName("com.pony.common.reflect.Person").newInstance();
        System.out.println(o);

        Person person1 = new Person();
        System.out.println(person1);

        Person person2 = person1.getClass().newInstance();
        System.out.println(person2);
    }

    /*反射
    * 基本方法：getDeclaredMethods() 获取所有的方法
    *          getReturnType() 获取方法返回类型
    *          getParameterTypes() 获取方法传入参数类型
    *          getDeclaredMethod("方法名",参数类型.class,……) 获取特定方法
    *
    *          getDeclaredConstructors() 获取构造方法
    *          getDeclaredConstructor(参数类型.class,……) 获取特定的构造方法
    *
    *          getSuperclass() 获取父类
    *          getInterfaces() 获取实现类
    *
    * */
    public void reflectTest() throws Exception{
        /*获取方法
        * 函数类型对应：PUBLIC: 1
·       *    PRIVATE: 2
        *    PROTECTED: 4
        *    STATIC: 8
        *    FINAL: 16
        * */
        Person person = new Person();
        Method[] methods = person.getClass().getDeclaredMethods();/*getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法*/
        for (Method method : methods){
            System.out.println(method);
            System.out.println("函数名："+ method.getName()
                    +"   函数类型:"+ method.getModifiers()
                    +"   函数返回："+ method.getReturnType()
                    +"   函数参数个数："+ method.getParameterCount());
        }


        /*构造函数,只有public的才能获取到*/
        Constructor<?>[] constructors = person.getClass().getConstructors();
        for (Constructor<?> constructor : constructors){
            System.out.println(constructor);
        }
        /*获取单个构造函数*/
        Constructor constructor = person.getClass().getConstructor(new Class[]{int.class, String.class});
        Person o = (Person)constructor.newInstance(1, "哈哈");
        System.out.println(o);

        /*方法的执行*/
        Method method = person.getClass().getMethod("setId", int.class);
        Person person1 = person.getClass().getConstructor(new Class[]{int.class, String.class, String.class}).newInstance(10, "gg", "nanjing");
        method.setAccessible(true);/*访问私有方法*/
        method.invoke(person1, 26);
        System.out.println(person1);

        /*获取字段值*/
        Person person2 = new Person();
        Field[] fields = person2.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            System.out.println(field+"--属性："+field.getName()+"+类型："+field.getType()+"和值："+field.get(o));

            /*通过属性来拼接set、get方法*/
            String property = field.getName();
            String getName = "get"+property.substring(0, 1).toUpperCase()+property.substring(1);
            String setName = "set"+property.substring(0, 1).toUpperCase()+property.substring(1);

            Method getMethod = person2.getClass().getDeclaredMethod(getName);
            Method setMethod = person2.getClass().getDeclaredMethod(setName, new Class[]{field.getType()});

            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(person2.getClass(), property);
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();


            Object o1 = getMethod.invoke(person2);
            if(field.getType().toString().equals("int")){
                o1 = (int)o1 + (int)o1;
            }
            if(field.getType().toString().equals("class java.lang.String")){
                o1 = (String)o1 + (String)o1;
            }
            setMethod.invoke(person2, o1);
        }

    }

    public static void main(String[] args) throws Exception{
        ReflectTest test = new ReflectTest();
        /*test.classLoadTest();*/
        /*test.classInstanceTest();*/
        /*test.reflectTest();*/

        Person person = new Person(1, "dd", "nanjing");
        People people = new People();
        BeanCopyUtil.copy(person, people);
        System.out.println(people);
    }
}
