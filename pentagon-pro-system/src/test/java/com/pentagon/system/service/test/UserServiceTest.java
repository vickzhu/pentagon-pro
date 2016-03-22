package com.pentagon.system.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.pentagon.system.dao.mapper.UserMapper;
import com.pentagon.system.dao.model.UserExample;
import com.pentagon.system.service.UserService;
import com.pentagon.system.test.BaseTest;

public class UserServiceTest extends BaseTest {
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void selectByExampleTest(){
//		userService.selectByExample(new UserExample());
		
		userMapper.selectByExample(null);
	}
	
}
