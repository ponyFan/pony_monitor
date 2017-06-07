package com.pony.common.collection.list;

import com.beust.jcommander.internal.Lists;

import java.util.List;

/**
 * Created by zelei.fan on 2017/6/7.
 */
public class ListTest {

    /**
     * list的方法
     */
    public void arrayListTest(){
        List<Integer> list = Lists.newArrayList();
        list.size();/*长度*/
        list.clear();;
    }
}
