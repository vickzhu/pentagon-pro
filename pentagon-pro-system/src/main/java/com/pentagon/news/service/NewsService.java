/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.news.dao.model.News;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsExample;

/**
 * 类NewsService.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:26:41
 */
public interface NewsService extends BaseService<News, NewsExample> {

    public void selectByPagination(NewsExample example, Page<News> page);

    public void insert(News news, NewsContent newsContent);

    public void update(News news, NewsContent newsContent);

}
