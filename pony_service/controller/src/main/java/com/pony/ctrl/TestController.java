package com.pony.ctrl;

import com.pony.common.BaseResponse;
import com.pony.common.aop.EatAdvice;
import com.pony.common.aop.EatService;
import com.pony.common.aop.aspect.Person;
import com.pony.model.db.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zelei.fan on 2017/5/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EatService eatService;

    @Autowired
    private Person person;

    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse getList(){
        List<Config> configList = eatService.getService();
        return new BaseResponse(200, "success", configList);
    }

    @RequestMapping("/redirectTest")
    @ResponseBody
    public BaseResponse redirect(String value){
        person.eatBreakfast();
        System.out.println("===================================================");
        person.eatLunch();
        System.out.println("===================================================");
        person.eatSupper();
        System.out.println("===================================================");
        person.drink("可乐");
        System.out.println("===================================================");
        eatService.lunch("aa");
        eatService.meal("cc");
        return new BaseResponse(200, "ok", value);
    }

}
