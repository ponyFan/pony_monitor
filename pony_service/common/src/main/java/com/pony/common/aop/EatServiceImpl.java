package com.pony.common.aop;

import org.springframework.stereotype.Service;

/**
 * Created by zelei.fan on 2017/7/10.
 */
@Service
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
