package com.pony.common.ioc;

import com.pony.common.ioc.model.Person;

/**
 * Created by zelei.fan on 2017/6/29.
 */
public class DataImpl implements Data {
    @Override
    public void connect() {
        System.out.println("接口注入调用");
    }

    @Override
    public Person getPerson() {
        return null;
    }
}
