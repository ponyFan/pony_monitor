package com.pony.common.threadPool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zelei.fan on 2017/6/13.
 */
public class ThreadHandle implements Runnable{

    private String index;

    public ThreadHandle(String index){
        this.index = index;
    }

    @Override
    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = df.format(date);
        System.out.println(Thread.currentThread().getName()+"Start Time"+time);
        /*中间一段处理时间*/
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date1 = new Date();
        String time1 = df.format(date1);
        System.out.println(Thread.currentThread().getName()+"End Time"+time1);
    }
}
