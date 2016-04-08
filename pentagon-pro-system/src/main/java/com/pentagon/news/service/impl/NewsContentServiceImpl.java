/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.news.dao.mapper.NewsContentMapper;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsContentExample;
import com.pentagon.news.service.NewsContentService;

/**
 * 类NewsContentServiceImpl.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:29:48
 */
@Service
public class NewsContentServiceImpl extends BaseServiceImpl<NewsContent, NewsContentExample> implements NewsContentService {

    @Resource
    private NewsContentMapper mapper;

    /*
     * (non-Javadoc)
     * @see com.gandalf.framework.mybatis.BaseServiceImpl#getMapper()
     */
    @Override
    protected BaseMapper<NewsContent, NewsContentExample> getMapper() {
        return mapper;
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.news.service.NewsContentService#selectByNewsId(java.lang.Long)
     */
    @Override
    public NewsContent selectByNewsId(long newsId) {
        NewsContentExample example = new NewsContentExample();
        NewsContentExample.Criteria criteria = example.createCriteria();
        criteria.andNewsIdEqualTo(newsId);
        List<NewsContent> list = mapper.selectByExampleWithBLOBs(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

}
