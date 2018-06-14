package com.pal.controller;

import com.pal.common.JsonData;
import com.pal.exception.ParamException;
import com.pal.param.TestVo;
import com.pal.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("hello.json")
    @ResponseBody
    public JsonData hello() {
        int i = 0;
        int j = 10/i;
        return JsonData.success("test");
    }

    @RequestMapping("validate.json")
    @ResponseBody
    public JsonData validate(TestVo testVo) throws ParamException {
        log.info("validate");
        BeanValidator.check(testVo);
        return JsonData.success("validate");
    }
}
