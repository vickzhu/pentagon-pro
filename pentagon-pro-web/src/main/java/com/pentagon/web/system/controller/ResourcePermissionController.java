package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.system.dao.model.ResourcePermission;
import com.pentagon.system.dao.model.ResourcePermissionExample;
import com.pentagon.system.dao.model.ResourcePermissionGroup;
import com.pentagon.system.service.ResourcePermissionGroupService;
import com.pentagon.system.service.ResourcePermissionService;

@Controller
@RequestMapping("/system/resourcePermission")
public class ResourcePermissionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourcePermissionController.class);
	
	@Resource
	private ResourcePermissionService resourceService;
	@Resource
	private ResourcePermissionGroupService groupService;
	
	private static int pageSize = 15;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request){
		String pageStr = request.getParameter("curPage");
		int curPage = 1;
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		List<ResourcePermissionGroup> groupList = groupService.selectByExample(null);
		Map<Long, String> groupMap = new HashMap<Long, String>();
		for (ResourcePermissionGroup group : groupList) {
			groupMap.put(group.getGroupId(), group.getGroupName());
		}
		Page<ResourcePermission> page = new Page<ResourcePermission>(curPage, pageSize);
		ResourcePermissionExample example = new ResourcePermissionExample();
		example.setOffset(page.getOffset());
		example.setRows(pageSize);
		example.setOrderByClause("resource_id desc");
		ResourcePermissionExample.Criteria criteria = example.createCriteria();
		String resourceName = request.getParameter("resourceName");
		if(StringUtil.isNotBlank(resourceName)){
			criteria.andResourceNameLike("%resourceName%");
		}
		resourceService.selectByPagination(example, page);
		ModelAndView mav = new ModelAndView("system/resourcePermissionList");
		mav.addObject("page", page);
		mav.addObject("groupMap", groupMap);
		mav.addObject("curPage", pageStr);
		mav.addObject("resourceName", resourceName);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		List<ResourcePermissionGroup> groupList = groupService.selectByExample(null);
		ModelAndView mav = new ModelAndView("system/resourcePermissionAdd");
		mav.addObject("groupList", groupList);
		return mav;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response){
		String resourceName = request.getParameter("resourceName");
		String groupId = request.getParameter("groupId");
		String uris = request.getParameter("uris");
		ResourcePermission resource = new ResourcePermission();
		resource.setResourceName(resourceName);
		resource.setResourceGroupId(Long.valueOf(groupId));
		resource.setUris(uris);
		resourceService.insert(resource);
		return new ModelAndView("redirect:/system/resourcePermission");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String resourceId = request.getParameter("resourceId");
		if(StringUtil.isBlank(resourceId)){
			response.sendError(403);
			return null;
		}
		List<ResourcePermissionGroup> groupList = groupService.selectByExample(null);
		ResourcePermission resource = resourceService.selectByPrimaryKey(Long.valueOf(resourceId));
		ModelAndView mav = new ModelAndView("system/resourcePermissionEdit");
		mav.addObject("resource", resource);
		mav.addObject("groupList", groupList);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String resourceId = request.getParameter("resourceId");
		if(StringUtil.isBlank(resourceId)){
			logger.error("Resource Id is required!!!");
			response.sendError(403);
			return Boolean.FALSE;
		}
		ResourcePermission resource = resourceService.selectByPrimaryKey(Long.valueOf(resourceId));
		String resourceName = request.getParameter("resourceName");
		String groupId = request.getParameter("groupId");
		String uris = request.getParameter("uris");
		resource.setResourceName(resourceName);
		resource.setResourceGroupId(Long.valueOf(groupId));
		resource.setUris(uris);
		int count = resourceService.updateByPrimaryKey(resource);
		return count > 0;
	}
	
	/**
	 * 检查资源是否存在
	 * @param resourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
	public boolean isNotExist(String resourceName, String oldName){
		if(resourceName.equals(oldName)){
			return true;
		}
		ResourcePermissionExample example = new ResourcePermissionExample();
		ResourcePermissionExample.Criteria criteria = example.createCriteria();
		criteria.andResourceNameEqualTo(resourceName);
		List<ResourcePermission> resourceList = resourceService.selectByExample(example);
		return CollectionUtils.isEmpty(resourceList);
	}
	
}
