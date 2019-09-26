/**
 * 
 */
package com.zhangzhi.cms.service;

import java.util.List;

import com.zhangzhi.cms.domain.Article;

/**
 * @author zhangzhi
 *2019年9月10日
 */
public interface ArticleService {
//	上一篇
	 Article preselectBy(Article article);
	
//	 下一篇
	 Article nextselectBy(Article article);
	
	
//	列出文章列表
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);


    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

//  点击率排
	List<Article> selecthits(Article article);
	
//	评论排行
	List<Article> selectcoms(Article article);
  
}
