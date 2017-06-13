package com.pony.common.threadPool;

import com.beust.jcommander.internal.Lists;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

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
        /*pool.shutdown(); shutdown是等待线程池被所有任务，包括等待的任务执行完成后关闭；shutdownNow是立刻关闭，不管是否还有线程在等待
        pool.shutdownNow();*/
    }


    public static void main(String[] args) {
        /*获取当前系统的CPU 数目*/
        int mun = Runtime.getRuntime().availableProcessors();
        System.out.println(mun);

        System.out.println("主线程开始");

        /*newCachedThreadPool可缓存线程池同时启动了十个线程来执行*/
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i ++){
            pool.execute(new ThreadHandle(String.valueOf(i)));
        }
        pool.shutdown();

        /*newFixedThreadPool固定线程数的线程池同一时刻最多只开启设定的5个线程，只有当前面的线程结束后才会继续执行后面等待的任务*/
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i ++){
            executorService.execute(new ThreadHandle(String.valueOf(i)));
        }
        executorService.shutdown();

        /*newSingleThreadExecutor单线程,同一时刻只有一个线程实例，所有任务都用这个实例执行
        * 相当于Executors.newFixedThreadPool(1)；
        * */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i ++){
            singleThreadExecutor.execute(new ThreadHandle(String.valueOf(i)));
        }

        /*计划类线程池*/
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i ++){
            /*延时10秒执行*/
            scheduledExecutorService.schedule(new ThreadHandle(String.valueOf(i)), 10, TimeUnit.SECONDS);

            /*从0ms开始每隔10秒执行一个任务，需要注意的是这个时间间隔是从上一个线程开始时计算的*/
            scheduledExecutorService.scheduleAtFixedRate(new ThreadHandle(String.valueOf(i)), 0, 10, TimeUnit.SECONDS);

            /*从0ms开始每隔10秒执行一个任务，需要注意的是这个时间间隔是从上一个线程结束后计算的*/
            scheduledExecutorService.scheduleWithFixedDelay(new ThreadHandle(String.valueOf(i)), 0, 10, TimeUnit.SECONDS);
        }
        System.out.println("主线程结束");


        /*将线程放入线程池中，除了使用execute，还可以使用submit,而且能够获取回调
        * submit适用于生产者-消费者模式，和Future一起使用起到没有返回结果就阻塞当前线程，等待线程池返回结果；
        * */
        ExecutorService pool1 = Executors.newCachedThreadPool();
        List<Future<String>> futureList = Lists.newArrayList();
        for (int i = 0; i < 10; i ++){
            Future<String> future = pool1.submit(new CallableTest(i));
            futureList.add(future);
        }
        futureList.forEach(new Consumer<Future<String>>() {
            @Override
            public void accept(Future<String> stringFuture) {
                try {
                    System.out.println(stringFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        /*同时区别一下Future和CompletionService两个的区别
        * 通过上面一个例子可以知道Future是阻塞取出结果的，按顺序执行，如果说正常的前面的线程执行完成后面的线程还在执行中的话，前面线程的结果时可以直接返回的
        * 但是如果后面的线程比前面的线程先执行完成，则后面线程的返回结果需要等待前面线程返回后才能取得结果；
        * 而CompletionService是异步非阻塞的，哪个执行完成有回调了，哪个就能输出结果，看下面的例子
        * */
        CompletionService service = new ExecutorCompletionService(pool1);
        for (int i = 0; i < 10; i ++){
            service.submit(new CallableTest(i));
        }
        for(int i = 0; i < 10; i ++){
            try {
                System.out.println(service.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

     }
}
