package com.pony.common.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yoho on 2017/3/31.
 */
public class ReflectTest {
    private int age;

    private String name;

    private int grade;

    public ReflectTest(){

    }

    public ReflectTest(int age, String name, int grade){
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    public int getAge() {
        System.out.println("我的年龄是："+age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        System.out.println("我的名字叫"+name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        System.out.println("我的年级是："+grade);
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ClassTest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

}

class TT{

    private final Logger logger = LoggerFactory.getLogger(TT.class);

    public static void main(String[] args) {
        ReflectTest test = new ReflectTest(30, "哈哈", 6);
        TT tt = new TT();
        tt.getClassInfo(test);
    }

    public void getClassInfo(Object o){
        Class clazz = o.getClass();
        try {
            Constructor constructor = clazz.getConstructor(new Class[]{int.class, String.class, int.class});
            Object o1 = constructor.newInstance(1, "dddddd", 11);
            Constructor[] constructor1 = clazz.getDeclaredConstructors();
            for(Constructor con : constructor1){
                System.out.println(con);
            }
        }catch (NoSuchMethodException e){
            logger.warn("not find this constructor");
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }

        try {
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);
            System.out.println("name的值为"+field.get(o));
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields){
                f.setAccessible(true);
                System.out.println(f+"--属性："+f.getName()+"+类型："+f.getType()+"和值："+f.get(o));

                String property = f.getName();
                String getName = "get"+property.substring(0, 1).toUpperCase()+property.substring(1);
                String setName = "set"+property.substring(0, 1).toUpperCase()+property.substring(1);

                Method getMethod = clazz.getDeclaredMethod(getName);
                Method setMethod = clazz.getDeclaredMethod(setName, f.getType());
                Object o1 = getMethod.invoke(o);
                if(f.getType().toString().equals("int")){
                    o1 = (int)o1 + (int)o1;
                }
                if(f.getType().toString().equals("class java.lang.String")){
                    o1 = (String)o1 + (String)o1;
                }
                setMethod.invoke(o, o1);
            }
        }catch (NoSuchFieldException e){
            logger.warn("not find this filed");
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

        try {
            Method method = clazz.getMethod("setName", String.class);
            method.setAccessible(true);
            method.invoke(o, "hhh");

            Method method1 = clazz.getDeclaredMethod("getName");
            method1.setAccessible(true);
            method1.invoke(o);
        }catch (NoSuchMethodException e){
            logger.warn("not find this method");
            e.printStackTrace();
        }catch (InvocationTargetException e){
            logger.warn("fail in invoke this method");
            e.printStackTrace();
        }catch (IllegalAccessException e){
            logger.warn("this method is private");
            e.printStackTrace();
        }

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            try {
                method.invoke(o);
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }
}
