package com.pentagon.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.dao.mapper.ResourcePermissionGroupMapper;
import com.pentagon.system.dao.model.ResourcePermissionGroup;
import com.pentagon.system.dao.model.ResourcePermissionGroupExample;
import com.pentagon.system.service.ResourcePermissionGroupService;

@Service
public class ResourcePermissionGroupServiceImpl
		extends
		BaseServiceImpl<ResourcePermissionGroup, ResourcePermissionGroupExample>
		implements ResourcePermissionGroupService {

	@Resource
	private ResourcePermissionGroupMapper groupMapper;
	
	@Override
	protected BaseMapper<ResourcePermissionGroup, ResourcePermissionGroupExample> getMapper() {
		return groupMapper;
	}

}
