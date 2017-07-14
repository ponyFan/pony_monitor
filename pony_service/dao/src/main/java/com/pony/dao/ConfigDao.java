package com.pony.dao;

import com.pony.model.db.Config;

import java.util.List;

/**
 * Created by zelei.fan on 2017/7/14.
 */
public interface ConfigDao {

    List<Config> selectAll();
}
