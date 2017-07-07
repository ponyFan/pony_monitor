package com.pony.common.ioc.model;

import java.util.List;
import java.util.Map;

/**
 * Created by zelei.fan on 2017/6/29.
 */
public class CollectionInjection {

    private List list;

    private Map map;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
