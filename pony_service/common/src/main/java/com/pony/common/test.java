package com.pony.common;

import com.beust.jcommander.internal.Lists;
import com.pony.common.collection.Person;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by zelei.fan on 2017/7/27.
 */
public class test {

    public void ship(List list){
        Person p1 = new Person();
        p1.setName("haha");
        p1.setAge(10);
        list.add(p1);

        Person p2 = new Person();
        p2.setName("cc");
        p2.setAge(15);
        list.add(p2);

        Person p3 = new Person();
        p3.setName("ee");
        p3.setAge(12);
        list.add(p3);

        Person p4 = new Person();
        p4.setName("ee");
        p4.setAge(12);
        list.add(p4);
    }

    public static void main(String[] args) {
        test collection = new test();
        test collection2 = new test();
        System.out.println(collection.equals(collection2));
        System.out.println(collection == collection2);
        List<Person> list = Lists.newArrayList();
        collection.ship(list);
        System.out.println(list);
        list = list.stream().sorted((a, b) -> a.getAge() - b.getAge()).collect(Collectors.toList());
        System.out.println(list);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        int i = 10;
        while (i > 0){
            pool.submit(new MapTest(i));
            i --;
        }
    }

    public static void hashMapTest(int i){
        Map map = new HashMap<>();
        map.put("index", i);
        System.out.println(map.get("index"));
    }
}

class MapTest implements Runnable{

    private int i;

    MapTest(int i){
        this.i = i;
    }

    @Override
    public void run() {
        test.hashMapTest(i);
    }
}
