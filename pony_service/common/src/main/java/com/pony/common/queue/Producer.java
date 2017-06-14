package com.pony.common.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zelei.fan on 2017/6/14.
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;

    private BlockingQueue queue;

    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        String data = null;
        System.out.println("启动生产者线程！");
        try {
            while (isRunning) {
                data = "data:" + count.incrementAndGet();
                System.out.println("将数据：" + data + "放入队列...");
                queue.put(data);
                /*if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }
}
