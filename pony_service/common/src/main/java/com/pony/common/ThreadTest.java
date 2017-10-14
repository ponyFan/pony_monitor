package com.pony.common;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zelei.fan on 2017/7/23.
 */
public class ThreadTest extends Thread {

    private String name;

    ThreadTest(String name){
        this.name = name;
    }

    ThreadTest(){}

    private int index = 5;

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"线程开始运行");
        while (index > 0) {
            if (3 == index){
                Thread.yield();
                System.out.println(Thread.currentThread().getName()+name + "线程让步");
                index --;
            }else {
                System.out.println(Thread.currentThread().getName()+name + "运行  :  " + index);
                index --;
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        SortThread sortThread = new SortThread("A", a, b);
        SortThread sortThread1 = new SortThread("B", b, a);
        new Thread(sortThread).start();
        Thread.sleep(100);
        new Thread(sortThread1).start();
    }
}

class SortThread implements Runnable{

    private int index = 10;

    private String name;

    private Object prev;

    private Object self;

    SortThread(String name, Object prev, Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        while (index > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    self.notify();
                    index--;
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}





