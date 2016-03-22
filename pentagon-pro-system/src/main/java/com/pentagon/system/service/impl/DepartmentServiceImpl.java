package com.pentagon.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.dao.mapper.DepartmentMapper;
import com.pentagon.system.dao.model.Department;
import com.pentagon.system.dao.model.DepartmentExample;
import com.pentagon.system.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends
		BaseServiceImpl<Department, DepartmentExample> implements
		DepartmentService {
	
	@Resource
	private DepartmentMapper deptMapper;

	@Override
	protected BaseMapper<Department, DepartmentExample> getMapper() {
		return deptMapper;
	}

}
