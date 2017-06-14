package com.pony.common.queue;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zelei.fan on 2017/6/14.
 */
public class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("启动消费者线程！");
        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("拿到数据：" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                    System.out.println("没有取到数据");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出消费者线程！");
        }
    }
}
