package com.pentagon.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gandalf.framework.web.tool.RequestUtil;
import com.pentagon.system.dao.model.User;
import com.pentagon.web.tool.SessionStore;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	User user = SessionStore.getUser();
    	//ajax请求处理
    	if(RequestUtil.isAjaxRequest(request)){
			response.setStatus(499);//我们将session过期的http状态码定义为499
		}
    	return Boolean.TRUE;
    }
}
