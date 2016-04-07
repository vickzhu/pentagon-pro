package com.pentagon.web.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.AjaxResult;
import com.pentagon.system.common.PermissionType;
import com.pentagon.system.dao.model.MenuPermission;
import com.pentagon.system.dao.model.ResourcePermission;
import com.pentagon.system.dao.model.ResourcePermissionGroup;
import com.pentagon.system.dao.model.Role;
import com.pentagon.system.dao.model.RoleExample;
import com.pentagon.system.dao.model.RolePermission;
import com.pentagon.system.service.MenuPermissionService;
import com.pentagon.system.service.ResourcePermissionGroupService;
import com.pentagon.system.service.ResourcePermissionService;
import com.pentagon.system.service.RolePermissionService;
import com.pentagon.system.service.RoleService;

@Controller
@RequestMapping("/system/role")
public class RoleController {

    private static final Logger            logger   = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService                    roleService;
    @Resource
    private ResourcePermissionService      resourceService;
    @Resource
    private ResourcePermissionGroupService resourceGroupService;
    @Resource
    private MenuPermissionService          menuService;
    @Resource
    private RolePermissionService          rolePermissionService;

    private static int                     pageSize = 10;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        int page = 1;
        if (StringUtil.isNotBlank(pageStr)) {
            page = Integer.valueOf(pageStr);
        }
        RoleExample example = new RoleExample();
        example.setOffset((page - 1) * pageSize);
        example.setRows(pageSize);
        example.setOrderByClause("gmt_create desc");
        RoleExample.Criteria criteria = example.createCriteria();
        String roleName = request.getParameter("roleName");
        if (StringUtil.isNotBlank(roleName)) {
            criteria.andRoleNameEqualTo(roleName);
        }
        List<Role> roleList = roleService.selectByExample(example);
        ModelAndView mav = new ModelAndView("system/roleList");
        mav.addObject("roleList", roleList);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("system/roleAdd");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response) {
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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        if (StringUtil.isBlank(roleId)) {
            logger.error("Role id required");
            response.sendError(403);
            return null;
        }
        Role role = roleService.selectByPrimaryKey(Long.valueOf(roleId));
        ModelAndView mav = new ModelAndView("system/roleEdit");
        mav.addObject("role", role);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        if (StringUtil.isBlank(roleId)) {
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
     * 
     * @param rolename
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
    public boolean isNotExist(String roleName) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        List<Role> roleList = roleService.selectByExample(example);
        return CollectionUtils.isEmpty(roleList);
    }

    /**
     * 分配资源权限
     * 
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/resourcePermission", method = RequestMethod.GET)
    public ModelAndView assignRezPerm(long roleId) {
        List<ResourcePermission> resourceList = resourceService.selectByExample(null);
        List<ResourcePermissionGroup> resourceGroupList = resourceGroupService.selectByExample(null);
        RolePermission permission = rolePermissionService.selectByPermissionType(roleId, PermissionType.RESOURCE);
        Map<Long, List<ResourcePermission>> resourceMap = new HashMap<Long, List<ResourcePermission>>();
        for (ResourcePermission resource : resourceList) {
            long groupId = resource.getResourceGroupId();
            List<ResourcePermission> rl = resourceMap.get(groupId);
            if (CollectionUtils.isEmpty(rl)) {
                rl = new ArrayList<ResourcePermission>();
                resourceMap.put(groupId, rl);
            }
            rl.add(resource);
        }
        Map<Long, String> permissionMap = new HashMap<Long, String>();
        String permissionStr = permission.getPermissionIds();
        if (StringUtil.isNotBlank(permissionStr)) {
            String[] permissionArr = permissionStr.split(SymbolConstant.COMMA);
            for (String str : permissionArr) {
                permissionMap.put(Long.valueOf(str), str);
            }
        }
        Role role = roleService.selectByPrimaryKey(roleId);
        ModelAndView mav = new ModelAndView("system/roleAssignRezPerm");
        mav.addObject("resourceGroupList", resourceGroupList);
        mav.addObject("resourceMap", resourceMap);
        mav.addObject("permissionMap", permissionMap);
        mav.addObject("role", role);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/resourcePermission", method = RequestMethod.POST)
    public AjaxResult doAssignRezPerm(HttpServletRequest request, Long roleId) {
        String[] resourceId = request.getParameterValues("resourceId");
        String resourceIds = StringUtil.join(resourceId, SymbolConstant.COMMA);
        RolePermission rolePermission = rolePermissionService.selectByPermissionType(roleId, PermissionType.RESOURCE);
        AjaxResult result = null;
        if (rolePermission == null) {
            rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionIds(resourceIds);
            rolePermission.setPermissionType(PermissionType.RESOURCE.getCode());
            int count = rolePermissionService.insert(rolePermission);
            result = new AjaxResult(count == 1, null);
        } else {
            rolePermission.setPermissionIds(resourceIds);
            int count = rolePermissionService.updateByPrimaryKey(rolePermission);
            result = new AjaxResult(count == 1, null);
        }
        return result;
    }

    /**
     * 分配菜单权限
     * 
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/menuPermission", method = RequestMethod.GET)
    public ModelAndView assignMenuPerm(long roleId) {
        List<MenuPermission> menuList = menuService.selectByExample(null);
        ModelAndView mav = new ModelAndView("system/roleAssignRezPerm");
        mav.addObject("menuList", menuList);
        return mav;
    }
}
