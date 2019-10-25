/**
 * 
 */
package com.zhangzhi.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zhangzhi.cms.domain.Article;

/**
 * @author zhangzhi
 *2019年10月22日
 */
public interface ArticleEs extends ElasticsearchRepository<Article, Integer> {
	List<Article> findByTitle(String key);
	
	
}
