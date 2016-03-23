package com.pentagon.web.tool;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.SessionHolder;
import com.pentagon.system.dao.model.User;

/**
 * 所有session中存储的值都在这里定义，临时的除外(比如spring mvc中的FlashMap)
 * @author zhuxb
 *
 */
public class SessionStore {
	
	private static final String USER_KEY = "user";
	private static final String CAPTCHA_LOGIN_KEY = "c_l_k";
	
	public static User getUser(){
		Object obj = SessionHolder.getAttributeObj(USER_KEY);
		return obj == null ? null : (User)obj;
	}
	
	public static void setUser(User user){
		SessionHolder.setAttribute(USER_KEY, user);
	}
	
	public static void setLoginCaptcha(String loginCaptcha){
		SessionHolder.setAttribute(CAPTCHA_LOGIN_KEY, loginCaptcha);
	}
	
	public static String getLoginCatpcha(){
		Object obj = SessionHolder.getAttributeObj(CAPTCHA_LOGIN_KEY);
		return obj == null ? StringUtil.EMPTY : String.valueOf(obj);
	}
	
	public static void removeLoginCaptcha(){
		SessionHolder.removeAttribute(CAPTCHA_LOGIN_KEY);
	}
}
