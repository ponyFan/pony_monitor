package com.pony.common.interceptor;

/**
 * Created by zelei.fan on 2017/7/13.
 */
public class InterceptorTest {

    public static void main(String[] args) {
        throw new ServiceException(404, "interceptor error");
    }
}
