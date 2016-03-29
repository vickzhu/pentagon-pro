package com.pentagon.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
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

	@Override
	public User selectByUsername(String username) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(userExample);
		if(CollectionUtils.isEmpty(userList)){
			return null;
		}
		return userList.get(0);
	}
	
}
