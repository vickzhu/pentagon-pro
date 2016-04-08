/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.web.news.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.pentagon.news.dao.model.NewsCategory;
import com.pentagon.news.dao.model.NewsCategoryExample;
import com.pentagon.news.service.NewsCategoryService;

/**
 * 类NewsCategoryController.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:33:44
 */
@Controller
@RequestMapping("/news/category")
public class NewsCategoryController {

    @Resource
    private NewsCategoryService categoryService;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request) {
        NewsCategoryExample example = new NewsCategoryExample();
        example.setOrderByClause("category_id desc");
        NewsCategoryExample.Criteria criteria = example.createCriteria();
        String category = request.getParameter("category");
        if (StringUtil.isNotBlank(category)) {
            criteria.andCategoryNameEqualTo(category);
        }
        List<NewsCategory> categoryList = categoryService.selectByExample(example);
        ModelAndView mav = new ModelAndView("news/newsCategoryList");
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("news/newsCategoryAdd");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("categoryName");
        NewsCategory category = new NewsCategory();
        category.setCategoryName(categoryName);
        categoryService.insert(category);
        return new ModelAndView("redirect:/news/category");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryId = request.getParameter("categoryId");
        if (StringUtil.isBlank(categoryId)) {
            response.sendError(403);
            return null;
        }
        NewsCategory category = categoryService.selectByPrimaryKey(Long.valueOf(categoryId));
        ModelAndView mav = new ModelAndView("news/newsCategoryEdit");
        mav.addObject("category", category);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryId = request.getParameter("categoryId");
        if (StringUtil.isBlank(categoryId)) {
            response.sendError(403);
            return Boolean.FALSE;
        }
        NewsCategory category = categoryService.selectByPrimaryKey(Long.valueOf(categoryId));
        String categoryName = request.getParameter("categoryName");
        category.setCategoryName(categoryName);
        int count = categoryService.updateByPrimaryKey(category);
        return count > 0;
    }

    /**
     * 检查角色是否存在
     * 
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isNotExist", method = RequestMethod.GET)
    public boolean isNotExist(String categoryName) {
        NewsCategoryExample example = new NewsCategoryExample();
        NewsCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryNameEqualTo(categoryName);
        int count = categoryService.countByExample(example);
        return count == 0;
    }
}
