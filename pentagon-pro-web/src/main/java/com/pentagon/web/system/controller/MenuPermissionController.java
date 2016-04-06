/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.List;

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
import com.gandalf.framework.web.tool.AjaxResult;
import com.pentagon.system.dao.model.MenuPermission;
import com.pentagon.system.dao.model.MenuPermissionExample;
import com.pentagon.system.service.MenuPermissionService;

/**
 * 类MenuPermissionController.java的描述：
 * 
 * @author gandalf 2016年4月6日 上午9:24:33
 */

@Controller
@RequestMapping("/system/menuPermission")
public class MenuPermissionController {

    private static final Logger   logger = LoggerFactory.getLogger(MenuPermissionController.class);

    @Resource
    private MenuPermissionService menuService;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("system/menuPermissionList");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("system/menuPermissionAdd");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("redirect:/system/menuPermission");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groupId = request.getParameter("groupId");
        if (StringUtil.isBlank(groupId)) {
            response.sendError(403);
            return null;
        }
        MenuPermission group = menuService.selectByPrimaryKey(Long.valueOf(groupId));
        ModelAndView mav = new ModelAndView("system/menuPermissionEdit");
        mav.addObject("group", group);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groupId = request.getParameter("groupId");
        if (StringUtil.isBlank(groupId)) {
            logger.error("Group Id required");
            response.sendError(403);
            return Boolean.FALSE;
        }
        MenuPermission group = menuService.selectByPrimaryKey(Long.valueOf(groupId));
        int count = menuService.updateByPrimaryKey(group);
        return count > 0;
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AjaxResult doDel(long groupId) {
        int count = menuService.deleteByPrimaryKey(Long.valueOf(groupId));
        return new AjaxResult(count > 0, null);
    }

    /**
     * 检查角色是否存在
     * 
     * @param GroupName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
    public boolean isNotExist(String groupName) {
        MenuPermissionExample example = new MenuPermissionExample();
        List<MenuPermission> MenuPermissionList = menuService.selectByExample(example);
        return CollectionUtils.isEmpty(MenuPermissionList);
    }

}
