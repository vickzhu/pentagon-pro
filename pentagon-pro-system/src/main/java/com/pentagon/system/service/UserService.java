package com.pentagon.system.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.system.dao.model.User;
import com.pentagon.system.dao.model.UserExample;

public interface UserService extends BaseService<User, UserExample> {
	
	public User selectByUsername(String username);
	
	public void selectByPagination(UserExample example,Page<User> page);
	
}
