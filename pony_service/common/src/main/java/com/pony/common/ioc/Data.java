package com.pony.common.ioc;

import com.pony.common.ioc.model.Person;

/**
 * Created by zelei.fan on 2017/6/28.
 */
public interface Data {

    /*接口注入*/
    void connect();

    /*方法注入*/
    Person getPerson();
}
