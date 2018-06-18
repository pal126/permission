package com.pal.controller;

import com.pal.common.ApplicationContextHelper;
import com.pal.common.JsonData;
import com.pal.dao.SysAclModuleMapper;
import com.pal.exception.ParamException;
import com.pal.model.SysAclModule;
import com.pal.param.TestVo;
import com.pal.util.BeanValidator;
import com.pal.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(testVo);
        return JsonData.success("validate");
    }
}
