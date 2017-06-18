package com.pony.common.queue;

import java.util.Deque;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zelei.fan on 2017/6/14.
 * deque(双向队列)：能在两端插入和删除元素
 * 在queue的基础上实现了能取出第一个元素(offerFirst()/offerLast(),addFirst()/addLast()),
 * 取出最后一个元素(pollFirst()/pollLast(),removeFirst()/removeLast())
 *
 * 正因为deque能够选择队尾或者队头，所以deque不仅能实现FIFO，同时还能实现LIFO；
 */
public class DequeTest {


    /*实现类有ArrayDeque和LinkedList
    * ArrayDeque并不是定长，每次队列满了后都会在增长一倍，索引更快，不需要entry
    *
    * */
    public static void main(String[] args) {
        /*主要验证下LIFO;FIFO和queue的实现方式一样*/
        Deque deque = new LinkedBlockingDeque<>(10);
        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(new ProducerDeque(deque));
        for(int i = 0; i < 3; i ++){
            pool.submit(new ConsumerDeque(deque));
        }
    }
}

class ProducerDeque implements Runnable{

    private Deque deque;

    private AtomicInteger count = new AtomicInteger();

    private boolean isRunning = true;

    ProducerDeque(Deque deque){
        this.deque = deque;
    }

    @Override
    public void run() {
        String data;
        while (isRunning){
            data = "data:" + count.incrementAndGet();
            System.out.println("将数据：" + data + "放入队列...");
            deque.push(data);/*push()/pop()是沿用的栈中的用法*/
            /*deque.addFirst(data);*//*addFirst()/removeFirst()是用的deque中的方法，两种都可以使用*/
        }
    }
}

class ConsumerDeque implements Runnable{

    private Deque deque;

    private boolean isRunning = true;

    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    ConsumerDeque(Deque deque){
        this.deque = deque;
    }

    @Override
    public void run() {
        while (isRunning){
            String data = (String)deque.pop();
            Random r = new Random();
            /*String data = (String)deque.removeFirst();*/
            if (null != data){
                System.out.println("获取数据" + data);
                try {
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("数据获取失败");
            }
        }
    }
}