package com.pony.ctrl;

import com.pony.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zelei.fan on 2017/5/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/list")
    public String getList(Model model){
        logger.info("***************** print info log ************************");
        logger.warn("***************** print warn log ************************");
        model.addAttribute("value", "hahahahha");
        return "/test/redirecTest";
    }

    @RequestMapping("/redirectTest")
    @ResponseBody
    public BaseResponse redirect(String value){
        return new BaseResponse(200, "ok", value);
    }

}
