package com.zhangzhi.dao;

import java.util.List;

import com.zhangzhi.domain.Article;

public interface ArticleMapper {
	
	
//	列出文章列表
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}