package com.pony.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by zelei.fan on 2017/5/26.
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 354894583766387644L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
