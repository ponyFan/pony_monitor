package com.pony.common.aop;

import com.pony.model.db.Config;

import java.util.List;

/**
 * Created by zelei.fan on 2017/7/10.
 */
public interface EatService {

    void meal(String food);

    void lunch(String food);

    void exception();

    List<Config> getService();

}
