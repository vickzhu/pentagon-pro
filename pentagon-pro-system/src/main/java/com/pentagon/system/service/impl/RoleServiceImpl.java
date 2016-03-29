package com.pentagon.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.dao.mapper.RoleMapper;
import com.pentagon.system.dao.model.Role;
import com.pentagon.system.dao.model.RoleExample;
import com.pentagon.system.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleExample> implements
		RoleService {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	protected BaseMapper<Role, RoleExample> getMapper() {
		return roleMapper;
	}

}
