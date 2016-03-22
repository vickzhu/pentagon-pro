package com.pentagon.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.dao.mapper.UserMapper;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.dao.model.UserExample;
import com.pentagon.system.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserExample> implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	protected BaseMapper<User, UserExample> getMapper() {
		return userMapper;
	}
	
}
