package com.pony.common.aop.aspect;

import org.springframework.stereotype.Service;

/**
 * Created by zelei.fan on 2017/7/17.
 */
@Service
public class PersonImpl implements Person {

    @Override
    public void eatBreakfast() {
        System.out.println("小Baby正在吃早餐");
    }

    @Override
    public void eatLunch() {
        System.out.println("小Baby正在吃午餐");
    }

    @Override
    public void eatSupper() {
        System.out.println("小Baby正在吃晚餐");
    }

    @Override
    public String drink(String name) {
        return "小Baby在喝："+name;
    }
}
