package com.pony.common.thread;

/**
 * Created by zelei.fan on 2017/6/8.
 *
 * 线程：同一类的线程共享数据空间和代码，每个线程有独立运行的栈和程序计数器切换开销小
 * 状态：创建、就绪、执行、阻塞、终止
 * 多线程是指在同一程序中有多个顺序流在执行
 * 实现多线程的方式：
 * 1、继承thread类
 * 2、实现runnable接口
 * 3、实现callable接口（线程池）
 */
public class ThreadTest extends Thread{

    private String name;

    ThreadTest(String name){
        this.name = name;
    }

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTesta = new ThreadTest("A");
        ThreadTest threadTestb = new ThreadTest("B");
        threadTesta.start();
        threadTestb.start();
    }
}
