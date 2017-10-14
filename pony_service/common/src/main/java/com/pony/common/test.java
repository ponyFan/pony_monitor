package com.pony.common;

<<<<<<< HEAD
/**
 * Created by zelei.fan on 2017/7/23.
 */
public class test implements Runnable{

    private static int index = 10;

    public static final String Lock = "lock";/*常量*/

    private byte[] bytes = new byte[0];/*实例变量*/

    private String lock = "synchronized";/*成员变量*/

    public void synchronizeObject(){
        /*通过锁定一个变量，或者常量，或者是一个实例变量*/
        synchronized (Lock){
            if (index > 0){
                System.out.println(Thread.currentThread().getName()+":"+index);
                index--;
            }
        }
    }

    @Override
    public void run() {
        /*同步方法，虽然是处理同一个静态变量，但是如果有多个实例线程来访问该方法的时候并不能锁住该方法，
        * 不影响其他实例线程在同一时刻访问该方法，线程不安全
        * */
        synchronizeObject();
    }

    public static void main(String[] args) {

        test1 test11 = new test1();
        new Thread(test11).start();

        test test = new test();
        test test1 = new test();
        new Thread(test).start();
        new Thread(test1).start();
        new Thread(test).start();
        new Thread(test1).start();
        new Thread(test).start();
        new Thread(test1).start();
        new Thread(test).start();
        new Thread(test1).start();
        new Thread(test).start();
        new Thread(test1).start();

    }
}


class test1 implements Runnable{

    @Override
    public void run() {
        synchronized (test.Lock){
            throw new RuntimeException();
        }
    }
}
=======
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
>>>>>>> 33d88e7a270f084101c39ed1013264c7e4914c28
