package com.pentagon.news.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.pentagon.news.dao.model.NewsContent;
import com.pentagon.news.dao.model.NewsContentExample;

public interface NewsContentMapper extends BaseMapper<NewsContent, NewsContentExample> {

    int countByExample(NewsContentExample example);

    int deleteByExample(NewsContentExample example);

    int deleteByPrimaryKey(Long contentId);

    int insert(NewsContent record);

    int insertSelective(NewsContent record);

    List<NewsContent> selectByExampleWithBLOBs(NewsContentExample example);

    List<NewsContent> selectByExample(NewsContentExample example);

    NewsContent selectByPrimaryKey(Long contentId);

    int updateByExampleSelective(@Param("record") NewsContent record, @Param("example") NewsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsContent record, @Param("example") NewsContentExample example);

    int updateByExample(@Param("record") NewsContent record, @Param("example") NewsContentExample example);

    int updateByPrimaryKeySelective(NewsContent record);

    int updateByPrimaryKeyWithBLOBs(NewsContent record);

    int updateByPrimaryKey(NewsContent record);
}
