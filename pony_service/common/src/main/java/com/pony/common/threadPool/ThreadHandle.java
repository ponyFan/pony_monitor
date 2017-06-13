package com.pony.common.threadPool;

import javafx.scene.input.DataFormat;

import java.util.Date;

/**
 * Created by zelei.fan on 2017/6/13.
 */
public class ThreadHandle implements Runnable{

    private String index;

    ThreadHandle(String index){
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"startTime"+new Date());
        /*中间一段处理时间*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"endTime"+new Date());
    }
}
