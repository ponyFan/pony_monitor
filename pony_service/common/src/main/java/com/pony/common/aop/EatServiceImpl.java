package com.pony.common.aop;

import com.pony.common.interceptor.ServiceException;
import com.pony.dao.ConfigDao;
import com.pony.model.db.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zelei.fan on 2017/7/10.
 */
@Service
public class EatServiceImpl implements EatService{

    @Autowired
    private ConfigDao configDao;

    @Override
    public void meal(String food) {
        System.out.println("早饭吃："+food);
    }

    @Override
    public List<Config> lunch(String food) {
        List<Config> configList = configDao.selectAll();
        return configList;
    }

    @Override
    public void exception() {
        System.out.println("执行出现异常");
        throw new RuntimeException();
    }
}
