package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import com.pentagon.system.dao.model.Role;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.dao.model.UserExample;
import com.pentagon.system.service.RoleService;
import com.pentagon.system.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	private static int pageSize = 10;
	
	@RequestMapping
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
		example.setOrderByClause("gmt_create desc");
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameNotEqualTo("admin");
		List<User> userList = userService.selectByExample(example);
		mav.addObject("userList", userList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		List<Role> roleList = roleService.selectByExample(null);		
		ModelAndView mav = new ModelAndView("system/userAdd");
		mav.addObject("roleList", roleList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		if(StringUtil.isBlank(username)){
			
		}
		String password = request.getParameter("password");
		if(StringUtil.isBlank(password)){
			
		}
		String cmfPwd = request.getParameter("cmfPwd");
		if(password.equals(cmfPwd)){
			
		}
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String enable = request.getParameter("enable");
		if(StringUtil.isBlank(enable)){
			
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5Util.md5Hex(password));
		user.setEmail(email);
		user.setPhone(phone);
		user.setEnable(Integer.valueOf(enable));
		//user.setCreator(creator);
		user.setGmtCreate(new Date());
		userService.insert(user);
		return new ModelAndView("redirect:/system/user");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userId = request.getParameter("userId");
		if(StringUtil.isBlank(userId)){
			response.sendError(403);
			return null;
		}
		User user = userService.selectByPrimaryKey(Long.valueOf(userId));
		ModelAndView mav = new ModelAndView("system/userEdit");
		mav.addObject("user", user);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userIdStr = request.getParameter("userId");
		if(StringUtil.isBlank(userIdStr)){
			response.sendError(403);
			return Boolean.FALSE;
		}
		User user = userService.selectByPrimaryKey(Long.valueOf(userIdStr));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String enable = request.getParameter("enable");
		user.setPhone(phone);		
		user.setEmail(email);
		user.setEnable(Integer.valueOf(enable));
		user.setGmtUpdate(new Date());
		int count = userService.updateByPrimaryKey(user);
		return count > 0;
	}
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
	public boolean isNotExist(String username){
		User user = userService.selectByUsername(username);
		return user == null ? Boolean.TRUE :Boolean.FALSE;
	}
	
}
