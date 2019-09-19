/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhangzhi.dao.ArticleMapper;
import com.zhangzhi.domain.Article;
import com.zhangzhi.domain.User;
import com.zhangzhi.service.UserService;

/**
 * @author zhangzhi
 *2019年9月16日
 */
public class ArticleServiceImpTest extends JunitParent{

	@Resource
	UserService dao;
	
	@Resource
	ArticleMapper daoar;
	
	@Test
	public void testSelects() {
		List<User> list = dao.selects(null);
		System.out.println(list);
	}

	/**
	 * Test method for {@link com.zhangzhi.service.serviceimp.ArticleServiceImp#deleteByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteByPrimaryKey() {
		Article a=new Article();
		List<Article> list = daoar.selects(a);
		for (Article ar : list) {
			System.out.println(ar.getStatus());
		}
	}

	/**
	 * Test method for {@link com.zhangzhi.service.serviceimp.ArticleServiceImp#insertSelective(com.zhangzhi.domain.Article)}.
	 */
	@Test
	public void testInsertSelective() {
//		Article a = daoar.selectByPrimaryKey(1);
		Article a=new Article();
		a.setId(1);
		a.setStatus(1);
		
		int b= daoar.updateByPrimaryKeySelective(a);
	System.out.println(b);
		}

	/**
	 * Test method for {@link com.zhangzhi.service.serviceimp.ArticleServiceImp#selectByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.service.serviceimp.ArticleServiceImp#updateByPrimaryKeySelective(com.zhangzhi.domain.Article)}.
	 */
	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

}
