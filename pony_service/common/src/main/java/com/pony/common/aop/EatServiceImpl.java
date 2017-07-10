package com.pony.common.aop;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public class EatServiceImpl implements EatService{


    @Override
    public void meal(String food) {
        System.out.println("早饭吃："+food);
    }

    @Override
    public void lunch(String food) {
        System.out.println("晚饭吃："+food);
    }

    @Override
    public void exception() {
        System.out.println("执行出现异常");
        throw new RuntimeException();
    }
}
