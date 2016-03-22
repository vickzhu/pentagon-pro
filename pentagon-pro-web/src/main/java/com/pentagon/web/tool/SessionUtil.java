package com.pentagon.web.tool;

import com.gandalf.framework.web.tool.SessionHolder;
import com.pentagon.system.dao.model.User;

/**
 * 所有session中存储的值都在这里定义，临时的除外(比如spring mvc中的FlashMap)
 * @author zhuxb
 *
 */
public class SessionUtil {
	
	private static final String USER_KEY = "user";
	
	public static User getUser(){
		Object obj = SessionHolder.getAttributeObj(USER_KEY);
		return obj == null ? null : (User)obj;
	}
	
	public static void setUser(User user){
		SessionHolder.setAttribute(USER_KEY, user);
	}
}
