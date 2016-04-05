package com.pentagon.system.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.system.dao.model.ResourcePermission;
import com.pentagon.system.dao.model.ResourcePermissionExample;

public interface ResourcePermissionService extends BaseService<ResourcePermission, ResourcePermissionExample> {
	
	public void selectByPagination(ResourcePermissionExample example, Page<ResourcePermission> page);
	
}
