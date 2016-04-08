/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.news.dao.mapper.NewsMapper;
import com.pentagon.news.dao.model.News;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsExample;
import com.pentagon.news.service.NewsContentService;
import com.pentagon.news.service.NewsService;

/**
 * 类NewsServiceImpl.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:32:22
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News, NewsExample> implements NewsService {

    @Resource
    private NewsMapper         mapper;
    @Resource
    private NewsContentService contentService;

    /*
     * (non-Javadoc)
     * @see com.gandalf.framework.mybatis.BaseServiceImpl#getMapper()
     */
    @Override
    protected BaseMapper<News, NewsExample> getMapper() {
        return mapper;
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.news.service.NewsService#selectByPagination(com.pentagon.news.dao.model.NewsExample,
     * com.gandalf.framework.web.tool.Page)
     */
    @Override
    public void selectByPagination(NewsExample example, Page<News> page) {
        example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<News> newsList = mapper.selectByExample(example);
        page.setRecords(newsList);
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.news.service.NewsService#insert(com.pentagon.news.dao.model.News,
     * com.pentagon.news.dao.model.NewsContent)
     */
    @Override
    public void insert(News news, NewsContent newsContent) {
        mapper.insert(news);
        newsContent.setNewsId(news.getNewsId());
        contentService.insert(newsContent);
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.news.service.NewsService#update(com.pentagon.news.dao.model.News,
     * com.pentagon.news.dao.model.NewsContent)
     */
    @Override
    public void update(News news, NewsContent newsContent) {
        mapper.updateByPrimaryKey(news);
        contentService.updateByPrimaryKeyWithBLOBs(newsContent);
    }

}
