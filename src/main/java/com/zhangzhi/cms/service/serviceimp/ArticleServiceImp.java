/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.cms.dao.ArticleMapper;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.service.ArticleService;

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

	@Override
	public Article preselectBy(Article article) {
		// TODO Auto-generated method stub
		return dao.preselectBy(article);
	}

	@Override
	public Article nextselectBy(Article article) {
		// TODO Auto-generated method stub
		return dao.nextselectBy(article);
	}

	@Override
	public List<Article> selecthits(Article article) {
		// TODO Auto-generated method stub
		return dao.selecthits(article);
	}

	@Override
	public List<Article> selectcoms(Article article) {
		// TODO Auto-generated method stub
		return dao.selectcoms(article);
	}


}
