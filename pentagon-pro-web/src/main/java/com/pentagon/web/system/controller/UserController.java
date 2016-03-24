package com.pentagon.web.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.dao.model.UserExample;
import com.pentagon.system.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController {
	@Resource
	private UserService userService;
	private static int pageSize = 10;
	
	@RequestMapping()
	public ModelAndView index(HttpServletRequest request){
		String pageStr = request.getParameter("page");
		int page = 1;
		if(StringUtil.isNotBlank(pageStr)){
			page = Integer.valueOf(pageStr);
		}
		ModelAndView mav = new ModelAndView("system/userList");
		UserExample example = new UserExample();
		example.setOffset((page-1)*pageSize);
		example.setRows(pageSize);
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameNotEqualTo("admin");
		List<User> userList = userService.selectByExample(example);
		mav.addObject("userList", userList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("system/userAdd");
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView();
	}
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isExist", method = RequestMethod.GET)
	public boolean isExist(String username){
		User user = userService.selectByUsername(username);
		return user == null ? Boolean.FALSE :Boolean.TRUE;
	}
	
}
