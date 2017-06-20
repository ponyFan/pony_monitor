package com.pony.common.ioStream;

import java.io.Serializable;

/**
 * Created by zelei.fan on 2017/6/20.
 */
public class Person implements Serializable{

    private static final long serialVersionUID = 5533482058075757538L;

    private int id;

    private String name;

    private int age;

    Person(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
