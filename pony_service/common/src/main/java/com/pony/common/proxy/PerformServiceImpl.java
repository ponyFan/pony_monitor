package com.pony.common.proxy;

/**
 * Created by zelei.fan on 2017/7/7.
 */
public class PerformServiceImpl implements PerformService {

    public void start(String arg){
        System.out.println("开始。。。。，获取参数" + arg);
    }

    public void end(String arg){
        System.out.println("结束。。。。，获取参数" + arg);
    }
}
