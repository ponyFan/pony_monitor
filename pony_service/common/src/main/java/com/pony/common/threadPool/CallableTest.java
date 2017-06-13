package com.pony.common.threadPool;

import java.util.concurrent.Callable;

/**
 * Created by zelei.fan on 2017/6/13.
 */
public class CallableTest implements Callable<String> {

    private int index;

    CallableTest(int index){
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被自动调用, 开始*******" + Thread.currentThread().getName());
        Thread.sleep(10000);
        return "call()方法被自动调用，结束*******" + Thread.currentThread().getName();
    }
}
