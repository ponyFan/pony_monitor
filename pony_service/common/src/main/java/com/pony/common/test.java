package com.pony.common;

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