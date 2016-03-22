package com.pentagon.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gandalf.framework.web.tool.RequestUtil;
import com.pentagon.system.dao.model.User;
import com.pentagon.web.tool.SessionUtil;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	User user = SessionUtil.getUser();
    	//ajax请求处理
    	if(RequestUtil.isAjaxRequest(request)){
			
		}
    	return Boolean.TRUE;
    }
}
