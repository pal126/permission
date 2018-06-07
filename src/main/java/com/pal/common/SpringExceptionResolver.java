package com.pal.common;

import com.pal.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring全局异常处理
 * @author pal
 * @data 2018/06/05
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURI();
        ModelAndView mv;

        if (url.endsWith(".json")) {
            if (e instanceof PermissionException) {
                log.error("exception url:" + url, e.getMessage());
                JsonData jsonData = JsonData.error(e.getMessage());
                mv = new ModelAndView("jsonView", jsonData.toMap());
                return mv;
            } else {
                log.error("exception url:" + url, e);
                JsonData jsonData = JsonData.error(CodeMsg.SERVER_ERROR.getMsg());
                mv = new ModelAndView("jsonView", jsonData.toMap());
                return mv;
            }
        } else if (url.endsWith(".page")) {
            log.error("exception url:" + url, e);
            JsonData jsonData = JsonData.error(CodeMsg.SERVER_ERROR.getMsg());
            mv = new ModelAndView("exception", jsonData.toMap());
            return mv;
        } else {
            log.error("exception url:" + url, e);
            JsonData jsonData = JsonData.error(CodeMsg.SERVER_ERROR.getMsg());
            mv = new ModelAndView("jsonView", jsonData.toMap());
            return mv;
        }
    }

}
