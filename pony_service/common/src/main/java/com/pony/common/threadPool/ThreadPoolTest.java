package com.pony.common.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by zelei.fan on 2017/6/12.
 * 线程池，创建一个线程集合，池子的大小取决于内存数量；当需要执行一个任务的时候是重用一个等待的线程，而不是重新开启一个线程；
 * 当任务执行完成后继续回到池子中等待下一个任务
 *
 * 优点：
 * 1、减少在创建和销毁线程上所花的时间和资源开销
 * 2、余主线程隔离，实现异步执行
 *
 * 注意：池中的线程数不是越多越好，线程休眠同样也会占用资源，所以要合理的选择线程池大小
 */
public class ThreadPoolTest {

    /*线程池中执行任务比每个任务都开一个线程优势多得多，减少很多的系统开销*/
    public void createThreadPool(){
        Executors.newFixedThreadPool(2);/*创建一个固定大小的线程池，当线程达到最大数时，大小不再变化，适用于固定的稳定的并发编程*/
        Executors.newCachedThreadPool();/*创建一个可缓存的线程池，当线程越多线程池规模也随之扩大,默认超时时间是60s，如果超过会自动终止该线程*/
        Executors.newSingleThreadExecutor();/*创建一个当线程，串行执行*/
        Executors.newScheduledThreadPool(2);/*创建一个固定大小的线程，延时或者定时执行*/
    }


    public static void main(String[] args) {
        /*获取当前系统的CPU 数目*/
        int mun = Runtime.getRuntime().availableProcessors();
        System.out.println(mun);

        System.out.println("主线程开始");
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i ++){
            pool.execute(new ThreadHandle(String.valueOf(i)));
        }
        pool.shutdown();
        System.out.println("主线程结束");
    }
}
