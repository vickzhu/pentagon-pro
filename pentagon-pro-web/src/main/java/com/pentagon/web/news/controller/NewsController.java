/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.web.news.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.news.common.NewsState;
import com.pentagon.news.common.OnlineEnum;
import com.pentagon.news.dao.model.News;
import com.pentagon.news.dao.model.NewsCategory;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsExample;
import com.pentagon.news.service.NewsCategoryService;
import com.pentagon.news.service.NewsContentService;
import com.pentagon.news.service.NewsService;
import com.pentagon.web.util.SessionStore;

/**
 * 类NewsController.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午2:35:14
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    private static final Logger logger   = LoggerFactory.getLogger(NewsController.class);

    @Resource
    private NewsCategoryService categoryService;
    @Resource
    private NewsService         newsService;
    @Resource
    private NewsContentService  contentService;

    private static int          pageSize = 15;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request) {
        String pageStr = request.getParameter("curPage");
        int curPage = 1;
        if (StringUtil.isNotBlank(pageStr)) {
            curPage = Integer.valueOf(pageStr);
        }
        String title = request.getParameter("title");
        String categoryId = request.getParameter("categoryId");
        List<NewsCategory> categoryList = categoryService.selectByExample(null);
        Map<Long, String> categoryMap = new HashMap<Long, String>();
        for (NewsCategory category : categoryList) {
            categoryMap.put(category.getCategoryId(), category.getCategoryName());
        }
        ModelAndView mav = new ModelAndView("news/newsList");
        Page<News> page = new Page<News>(curPage, pageSize);
        NewsExample example = new NewsExample();
        example.setOffset(page.getOffset());
        example.setRows(pageSize);
        example.setOrderByClause("gmt_create desc");
        NewsExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (StringUtil.isNotBlank(categoryId)) {
            criteria.andNewsCategoryEqualTo(Long.valueOf(categoryId));
        }
        newsService.selectByPagination(example, page);
        mav.addObject("page", page);
        mav.addObject("curPage", pageStr);
        mav.addObject("title", title);
        mav.addObject("categoryId", categoryId);
        mav.addObject("categoryList", categoryList);
        mav.addObject("categoryMap", categoryMap);
        mav.addObject("onlineEnum", OnlineEnum.class);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        List<NewsCategory> categoryList = categoryService.selectByExample(null);
        ModelAndView mav = new ModelAndView("news/newsAdd");
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        if (StringUtil.isBlank(title)) {

        }
        String category = request.getParameter("category");
        if (StringUtil.isBlank(category)) {

        }
        String source = request.getParameter("source");
        String online = request.getParameter("online");
        String priority = request.getParameter("priority");
        String sourceTitle = request.getParameter("sourceTitle");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        String keywords = request.getParameter("keywords");
        String description = request.getParameter("description");

        News news = new News();
        news.setGmtCreate(new Date());
        news.setNewsCategory(Long.valueOf(category));
        news.setOnline(Integer.valueOf(online));
        news.setPriority(Integer.valueOf(priority));
        news.setPublisher(SessionStore.getUser().getUserId());
        news.setSource(source);
        news.setState(NewsState.APPROVED.getCode());
        news.setTitle(title);

        NewsContent newsContent = new NewsContent();
        newsContent.setBrief(brief);
        newsContent.setContent(content);
        newsContent.setSourceTitle(sourceTitle);
        newsContent.setKeywords(keywords);
        newsContent.setDescription(description);

        newsService.insert(news, newsContent);

        return new ModelAndView("redirect:/news");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newsIdStr = request.getParameter("newsId");
        if (StringUtil.isBlank(newsIdStr)) {
            logger.error("News Id is required!!!");
            response.sendError(403);
            return null;
        }
        long newsId = Long.valueOf(newsIdStr);
        News news = newsService.selectByPrimaryKey(newsId);
        NewsContent newsContent = contentService.selectByNewsId(newsId);

        ModelAndView mav = new ModelAndView("system/userEdit");
        mav.addObject("news", news);
        mav.addObject("newsContent", newsContent);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public boolean doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newsIdStr = request.getParameter("newsId");
        if (StringUtil.isBlank(newsIdStr)) {
            response.sendError(403);
            return Boolean.FALSE;
        }
        long newsId = Long.valueOf(newsIdStr);
        String title = request.getParameter("title");
        if (StringUtil.isBlank(title)) {

        }
        String category = request.getParameter("category");
        if (StringUtil.isBlank(category)) {

        }
        String source = request.getParameter("source");
        String online = request.getParameter("online");
        String priority = request.getParameter("priority");
        String sourceTitle = request.getParameter("sourceTitle");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");

        News news = newsService.selectByPrimaryKey(newsId);
        news.setGmtUpdate(new Date());
        news.setNewsCategory(Long.valueOf(category));
        news.setOnline(Integer.valueOf(online));
        news.setPriority(Integer.valueOf(priority));
        news.setPublisher(SessionStore.getUser().getUserId());
        news.setSource(source);
        news.setState(NewsState.APPROVED.getCode());
        news.setTitle(title);

        NewsContent newsContent = contentService.selectByNewsId(newsId);
        newsContent.setBrief(brief);
        newsContent.setContent(content);
        newsContent.setSourceTitle(sourceTitle);

        newsService.update(news, newsContent);

        return true;
    }

}
