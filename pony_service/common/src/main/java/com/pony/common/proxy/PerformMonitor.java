package com.pony.common.proxy;

/**
 * Created by zelei.fan on 2017/7/7.
 */
public class PerformMonitor {

    public static void start(Object[] args){
        System.out.println("执行前操作，获取参数" + args);
    }

    public static void end(Object[] args){
        System.out.println("执行后操作，获取参数" + args);
    }
}
