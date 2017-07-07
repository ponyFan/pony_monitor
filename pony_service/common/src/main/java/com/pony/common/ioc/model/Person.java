package com.pony.common.ioc.model;

/**
 * Created by zelei.fan on 2017/6/28.
 */
public class Person {

    private String name;

    private int age;

    private String sex;


    /*在不同的包中，new一个重写构造函数的对象时，要加public，因为默认的是protect（只作用在当前包范围内）*/
    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public Person(String name, int age){
        this(name);
        this.age = age;
    }

    public Person(String name, int age, String sex){
        this(name, age);
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
