/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.dao.ArticleMapper;
import com.zhangzhi.domain.Article;
import com.zhangzhi.service.ArticleService;

/**
 * @author zhangzhi
 *2019年9月10日
 */
@Service
public class ArticleServiceImp implements ArticleService {

	@Resource
	ArticleMapper dao;
	
	public List<Article> selects(Article article) {
		// TODO Auto-generated method stub
		return dao.selects(article);
	}

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}


	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Article record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}


}
