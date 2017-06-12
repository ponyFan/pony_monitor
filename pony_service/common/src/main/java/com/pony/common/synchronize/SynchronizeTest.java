package com.pony.common.synchronize;

/**
 * Created by zelei.fan on 2017/6/12.
 * 同步
 * 1、synchronized aMethod(){}防止多个线程同时访问该方法
 * 如果说该对象实例中有多个synchronized方法，那么有一个线程访问了其中一个synchronized方法，其他线程则不能访问其中的任何方法
 * 但是不同对象的实例synchronized是互不干扰的，其他线程照样可以访问其他实例中的synchronized方法
 *
 * 2、synchronized static aStaticMethod{}防止多个线程同时访问synchronized static方法
 * 该方法是作用于所有该类的实例中的，只要有一个线程访问了，其他所有实例中的该方法都不能被其他线程访问、
 *
 * 3、方法中使用，锁住代码块
 * synchronized(this){区块}，它的作用域是当前对象
 *
 * 4、synchronized不能被继承，
 * 当父类总某个方法是synchronized aMethod{}，则子类中大该方法是aMethod{}
 */
public class SynchronizeTest implements Runnable{

    private static int index = 10;

    private static final String Lock = "lock";

    private byte[] bytes = new byte[0];

    /*锁定方法，当锁定方法时SynchronizeTest的实例调用该方法则会形成互斥，实现线程同步
    * 但是SynchronizeTest的另一个实例可以任意的访问该方法，因为不是同一个实例
    * 当某个线程获取到锁时，等到该线程执行完后才会释放锁
    * */
    public synchronized void synchronizeMethod(){
        if(index > 0){
            System.out.println(Thread.currentThread().getName()+":"+index);
            index--;
        }
    }
    /*上述锁定方法可以更换为锁定代码块*/
    public void synchronizeCode(){
        /*this为调用该方法的对象*/
        synchronized (this){
            if(index > 0){
                System.out.println(Thread.currentThread().getName()+":"+index);
                index--;
            }
        }
    }

    /*同步代码块, 如果多个实例使用的锁定变量是同一个的话，则是线程安全的*/
    public void synchronizeObject(Object o){
        /*通过锁定一个变量，或者常量，或者是一个实例变量*/
        synchronized (o){
            if (index > 0){
                System.out.println(Thread.currentThread().getName()+":"+index);
                index--;
            }
        }
    }

    /*同步static方法*/
    public static synchronized void synchronizeStatic(){
        if(index > 0){
            System.out.println(Thread.currentThread().getName()+":"+index);
            index--;
        }
    }
    public void synchronizeClass(){
        synchronized (SynchronizeTest.class){
            if(index > 0){
                System.out.println(Thread.currentThread().getName()+":"+index);
                index--;
            }
        }
    }


    @Override
    public void run() {
        /*当多线程同时访问index时，不用锁的话，多线程读出的数据是有问题的，如果是调用下面同步方法则避免这种情况*/
        /*System.out.println(index);
        index --;*/

        /*同步方法，虽然是处理同一个静态变量，但是如果有多个实例线程来访问该方法的时候并不能锁住该方法，
        * 不影响其他实例线程在同一时刻访问该方法，线程不安全
        * */
        synchronizeMethod();
        /*synchronizeCode();*/

        /*同步代码块，当锁定是一个静态的变量或者是class类，则该方法是线程同步的，但是如果是一个成员变量则不同步*/
        /*synchronizeObject(Lock);*//*锁定常量*//*
        *//*synchronizeObject(index);锁定变量
        synchronizeObject(bytes);锁定实例变量*/

        /*同步static方法，方法肯定同步*/
        /*synchronizeStatic();*/

        /*锁定class类，方法同步*/
        /*synchronizeClass();*/
    }


    public static void main(String[] args) {
       /*总结：如果是使用的是同一个实例的话，那么在对成员变量做处理的时候只需要加上锁即可，
       * 锁定方法，代码块都可以，或者是锁定全局方法锁；
       *
       * 注意：方法同步和处理静态变量是两回事
       * */
        SynchronizeTest test = new SynchronizeTest();
        SynchronizeTest test1 = new SynchronizeTest();
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
