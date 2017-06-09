package com.pony.common.thread;

/**
 * Created by zelei.fan on 2017/6/8.
 */
public class RunnableTest implements Runnable {

    private String name;

    public RunnableTest(String name){
        this.name = name;
    }

    private int index = 10;

    @Override
    public void run() {
        while (index > 0) {
            System.out.println(Thread.currentThread().getName()+name + "运行  :  " + index--);
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
