package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.encrypt.MD5Util;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.RequestUtil;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.service.UserService;
import com.pentagon.web.exception.PentagonSystemException;
import com.pentagon.web.util.SessionStore;

@Controller
public class SecurityController {
	
	@Resource
	private UserService userService;
	
	private static final String ERROR_MSG = "errorMsg";
	private static final String USERNAME = "username";
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入......................................");
		return "success";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("captchaNum");
		if(StringUtil.isBlank(username) || StringUtil.isBlank(password)||StringUtil.isBlank(verifyCode)){//非法访问
			throw new PentagonSystemException("非法登录");
		}
		ModelAndView mav = new ModelAndView("login");
		String sessionCaptcha = SessionStore.getLoginCatpcha();
		if(!verifyCode.equalsIgnoreCase(sessionCaptcha)){
			mav.addObject(ERROR_MSG, "验证码错误!");
			return mav;
		} else {//验证过需要将改验证码清除
			SessionStore.removeLoginCaptcha();
		}
		User user = userService.selectByUsername(username);
		if(user == null){//用户不存在
			mav.addObject(ERROR_MSG, "用户不存在!");
			return mav;
		}
		String secPwd = MD5Util.md5Hex(password);
		if(!user.getPassword().equals(secPwd)){//密码不正确
			mav.addObject(USERNAME,username);
			mav.addObject(ERROR_MSG, "用户名或密码错误!");
			return mav;
		}
		SessionStore.setUser(user);
		String ip = RequestUtil.getIp(request);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(new Date());
		userService.updateByPrimaryKey(user);
		mav.setViewName("redirect:/index");
		return mav;
	}
	
}
