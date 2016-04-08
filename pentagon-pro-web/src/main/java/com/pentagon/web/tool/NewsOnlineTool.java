/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.web.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gandalf.framework.velocity.tool.AbstractTool;
import com.pentagon.news.common.OnlineEnum;

/**
 * 类NewsOnlineTool.java的描述：新闻是否在线
 * 
 * @author gandalf 2016年4月8日 下午5:51:47
 */
@DefaultKey("onlineTool")
@ValidScope(Scope.REQUEST)
public class NewsOnlineTool extends AbstractTool {

    public String getDesc(int code) {
        return OnlineEnum.getDesc(code);
    }
}
