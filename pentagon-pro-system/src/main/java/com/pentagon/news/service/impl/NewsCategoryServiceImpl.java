/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.news.dao.mapper.NewsCategoryMapper;
import com.pentagon.news.dao.model.NewsCategory;
import com.pentagon.news.dao.model.NewsCategoryExample;
import com.pentagon.news.service.NewsCategoryService;

/**
 * 类NewsCategoryServiceImpl.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:28:10
 */
@Service
public class NewsCategoryServiceImpl extends BaseServiceImpl<NewsCategory, NewsCategoryExample> implements NewsCategoryService {

    @Resource
    private NewsCategoryMapper mapper;

    /*
     * (non-Javadoc)
     * @see com.gandalf.framework.mybatis.BaseServiceImpl#getMapper()
     */
    @Override
    protected BaseMapper<NewsCategory, NewsCategoryExample> getMapper() {
        return mapper;
    }

}
