package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.pentagon.system.dao.model.Role;
import com.pentagon.system.dao.model.RoleExample;
import com.pentagon.system.service.RoleService;

@Controller
@RequestMapping("/system/role")
public class RoleController {
	
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
		RoleExample example = new RoleExample();
		example.setOffset((page-1) * pageSize);
		example.setRows(pageSize);
		example.setOrderByClause("gmt_create desc");
		RoleExample.Criteria criteria = example.createCriteria();
		String roleName = request.getParameter("roleName");
		if(StringUtil.isNotBlank(roleName)){
			criteria.andRoleNameEqualTo(roleName);
		}
		List<Role> roleList = roleService.selectByExample(example);
		ModelAndView mav = new ModelAndView("system/roleList");
		mav.addObject("roleList", roleList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("system/roleAdd");
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response){
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String enable = request.getParameter("enable");
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setEnable(Integer.valueOf(enable));
		role.setGmtCreate(new Date());
		roleService.insert(role);
		return new ModelAndView("redirect:/system/role");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String roleId = request.getParameter("roleId");
		if(StringUtil.isBlank(roleId)){
			response.sendError(403);
			return null;
		}
		Role role = roleService.selectByPrimaryKey(Long.valueOf(roleId));
		ModelAndView mav = new ModelAndView("system/roleEdit");
		mav.addObject("role", role);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String roleId = request.getParameter("roleId");
		if(StringUtil.isBlank(roleId)){
			response.sendError(403);
			return null;
		}
		Role role = roleService.selectByPrimaryKey(Long.valueOf(roleId));
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setGmtUpdate(new Date());
		roleService.updateByPrimaryKey(role);
		return null;
	}
	
	/**
	 * 检查角色是否存在
	 * @param rolename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
	public boolean isNotExist(String roleName){
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo(roleName);
		List<Role> roleList = roleService.selectByExample(example);
		return CollectionUtils.isEmpty(roleList);
	}
	
}
