package com.pony.common.reflect;

/**
 * Created by zelei.fan on 2017/6/21.
 */
public class Person {

    private int id;

    private String name;

    private String address;

    public Person(){}

    public Person(int id){
        this.id = id;
    }

    public Person(int id, String name){
        this(id);
        this.name = name;
    }

    public Person(int id, String name, String address){
        this(id, name);
        this.address = address;
    }

    static {
        System.out.println("初始化完成");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
