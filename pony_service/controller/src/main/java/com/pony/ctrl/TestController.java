package com.pony.ctrl;

import com.pony.common.BaseResponse;
import com.pony.common.aop.EatAdvice;
import com.pony.common.aop.EatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zelei.fan on 2017/5/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EatService eatService;

    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse getList(Model model){
        logger.info("***************** print info log ************************");
        logger.warn("***************** print warn log ************************");
        model.addAttribute("value", "hahahahha");
        return new BaseResponse(200, "success", "vvvvvvvvvvvvvvv");
    }

    @RequestMapping("/redirectTest")
    @ResponseBody
    public BaseResponse redirect(String value){
        eatService.lunch("aa");
        eatService.meal("cc");
        return new BaseResponse(200, "ok", value);
    }

}
