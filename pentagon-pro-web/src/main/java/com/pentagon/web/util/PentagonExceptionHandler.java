package com.pentagon.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * spring mvc 异常处理
 * 
 * @author zhuxb
 */
public class PentagonExceptionHandler extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(PentagonExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        // logger.error("url:" + request.getRequestURI(), ex);
        // //ajax异常
        // if(RequestUtil.isAjaxRequest(request)){
        //
        // }
        return super.resolveException(request, response, handler, ex);
    }

}
