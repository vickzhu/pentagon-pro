package com.pentagon.system.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.util.Assert;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.dao.model.UserExample;
import com.pentagon.system.service.UserService;
import com.pentagon.system.test.BaseTest;

public class UserServiceTest extends BaseTest {
	
	@Resource
	private UserService userService;
	
	@Test
	public void selectByExampleTest(){
		UserExample userExample = new UserExample();
		userExample.setOffset(0);
		userExample.setRows(1);
		List<User> userList = userService.selectByExample(userExample);
		Assert.assertNotNull(userList);
	}
	
}
