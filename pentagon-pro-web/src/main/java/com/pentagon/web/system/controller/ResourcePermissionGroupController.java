package com.pentagon.web.system.controller;

import java.io.IOException;
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
import com.pentagon.system.dao.model.ResourcePermissionGroup;
import com.pentagon.system.dao.model.ResourcePermissionGroupExample;
import com.pentagon.system.service.ResourcePermissionGroupService;

@Controller
@RequestMapping("/system/resourcePermissionGroup")
public class ResourcePermissionGroupController {
	
	@Resource
	private ResourcePermissionGroupService groupService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request){
		ResourcePermissionGroupExample example = new ResourcePermissionGroupExample();
		example.setOrderByClause("group_id desc");
		ResourcePermissionGroupExample.Criteria criteria = example.createCriteria();
		String groupName = request.getParameter("groupName");
		if(StringUtil.isNotBlank(groupName)){
			criteria.andGroupNameEqualTo(groupName);
		}
		List<ResourcePermissionGroup> groupList = groupService.selectByExample(example);
		ModelAndView mav = new ModelAndView("system/resourcePermissionGroupList");
		mav.addObject("groupList", groupList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("system/resourcePermissionGroupAdd");
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response){
		String groupName = request.getParameter("groupName");
		ResourcePermissionGroup group = new ResourcePermissionGroup();
		group.setGroupName(groupName);
		groupService.insert(group);
		return new ModelAndView("redirect:/system/resourcePermissionGroup");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String groupId = request.getParameter("groupId");
		if(StringUtil.isBlank(groupId)){
			response.sendError(403);
			return null;
		}
		ResourcePermissionGroup group = groupService.selectByPrimaryKey(Long.valueOf(groupId));
		ModelAndView mav = new ModelAndView("system/resourcePermissionGroupEdit");
		mav.addObject("group", group);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String groupId = request.getParameter("groupId");
		if(StringUtil.isBlank(groupId)){
			response.sendError(403);
			return Boolean.FALSE;
		}
		ResourcePermissionGroup group = groupService.selectByPrimaryKey(Long.valueOf(groupId));
		String groupName = request.getParameter("groupName");
		group.setGroupName(groupName);
		int count = groupService.updateByPrimaryKey(group);
		return count > 0;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public boolean doDel(HttpServletRequest request){
		String groupId = request.getParameter("groupId");
		int count = groupService.deleteByPrimaryKey(Long.valueOf(groupId));
		return count == 1;
	}
	
	/**
	 * 检查角色是否存在
	 * @param GroupName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
	public boolean isNotExist(String groupName){
		ResourcePermissionGroupExample example = new ResourcePermissionGroupExample();
		ResourcePermissionGroupExample.Criteria criteria = example.createCriteria();
		criteria.andGroupNameEqualTo(groupName);
		List<ResourcePermissionGroup> ResourcePermissionGroupList = groupService.selectByExample(example);
		return CollectionUtils.isEmpty(ResourcePermissionGroupList);
	}
	
}
