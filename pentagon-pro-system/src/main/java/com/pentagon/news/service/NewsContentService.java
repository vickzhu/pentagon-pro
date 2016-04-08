/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.service;

import com.gandalf.framework.mybatis.BaseService;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsContentExample;

/**
 * 类NewsContent.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:27:05
 */
public interface NewsContentService extends BaseService<NewsContent, NewsContentExample> {

    public NewsContent selectByNewsId(long newsId);

}
