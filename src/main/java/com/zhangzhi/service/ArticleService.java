/**
 * 
 */
package com.zhangzhi.service;

import java.util.List;

import com.zhangzhi.domain.Article;

/**
 * @author zhangzhi
 *2019年9月10日
 */
public interface ArticleService {

//	列出文章列表
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);


    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);


}
