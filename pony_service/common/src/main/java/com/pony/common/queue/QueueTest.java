package com.pony.common.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zelei.fan on 2017/6/14.
 * 队列，特点就是先进先出
 */
public class QueueTest {

    /*BlockingQueue介绍：
    * 常用的实现类有：ArrayBlockingQueue，LinkedBlockingQueue，SynchronousQueue
    * 常用方法：
    * 1）放入任务：
    *           1、add(object);如果队列没有满的话，放入成功，否则抛异常；
    *           2、offer(object);如果可能的话，即队列没满的时候加入，并且返回true，否则返回false；
    *           3、offer(Object, timeOut, TimeUnit);设置等待时间，如果在指定时间内返回false则失败；
    *           4、put(object);加入队列，如果没有空间的话则调用该方法的线程阻塞，当队列有空间时再次执行；
    * 2）获取任务：
    *           1、poll(time);取走排在首位的对象，如果不能立即取出则可以等待设置的time时间，超过设定时间还没有返回则返回null；
    *           2、take();取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入;
    *           3、drainTo();取走所有可用数据，提高数据获取效率；
    *
    * */

    public static void main(String[] args) throws InterruptedException {
        /*创建一个队列LinkedBlockingDeque，如果没有参数则是个无界队列，加参数的话则是一个有界的队列*/
        BlockingDeque queue = new LinkedBlockingDeque<>(10);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i ++){
            /*启动20个生产者线程，往队列中加参数*/
            service.execute(new Producer(queue));
            /*启动20个消费者线程，从队列中取出参数，当队列中没有参数的时候，消费者则阻塞*/
            service.execute(new Consumer(queue));
        }
        Thread.sleep(10 * 1000);

        // 退出Executor
        service.shutdown();

    }

}
