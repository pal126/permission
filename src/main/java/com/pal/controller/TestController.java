package com.pal.controller;

import com.pal.common.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
