/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangzhi.cms.dao.ArticleEs;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.service.ArticleServiceEs;

/**
 * @author zhangzhi
 *2019年10月22日
 */
@Service
public class ArticleServiceEsimp implements ArticleServiceEs {

	@Autowired
	private ArticleEs dao;

	@Override
	public void save(Article article) {
		dao.save(article);
	}
	
	
	
}
