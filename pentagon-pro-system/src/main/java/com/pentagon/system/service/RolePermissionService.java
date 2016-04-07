/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.system.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.pentagon.system.common.PermissionType;
import com.pentagon.system.dao.model.RolePermission;
import com.pentagon.system.dao.model.RolePermissionExample;

/**
 * 类RolePermissionService.java的描述：角色权限service
 * 
 * @author gandalf 2016年4月7日 下午12:37:00
 */
public interface RolePermissionService extends BaseService<RolePermission, RolePermissionExample> {

    public List<RolePermission> selectByRole(Long roleId);

    public RolePermission selectByPermissionType(Long roleId, PermissionType permissionType);

}
