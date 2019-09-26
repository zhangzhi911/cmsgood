/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;

import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.domain.Comment;
import com.zhangzhi.cms.domain.User;
import com.zhangzhi.cms.service.CommentService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
public class UserServiceimpTest extends JunitParent{

	@Resource
	private CommentService dao;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Test
	public void testSelects() {
		Article ar = new Article();
		ar.setId(2);
		Comment c = new Comment();
		c.setArticle(ar);
		List<Comment> list = dao.selects(c);
		 for (Comment comment : list) {
			System.out.println(comment);
		}
	}

	@Test
	public void testInsertSelective() {
		User user = new User();
		user.setId(125);
		Article ar = new Article();
		ar.setId(3);
		Comment c = new Comment();
		c.setUserid(user);
		c.setArticle(ar);
		c.setContent("西北玄天一片云\n乌鸦落在凤凰群\n满朝都是英雄汉\n谁是君来谁是臣");
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		c.setCreated(new Date());
		
		int i = dao.insert(c);
		System.out.println(i);
		
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.UserServiceimp#selectByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.UserServiceimp#updateByPrimaryKeySelective(com.zhangzhi.cms.domain.User)}.
	 */
	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.UserServiceimp#updalo(com.zhangzhi.cms.domain.User)}.
	 */
	@Test
	public void testUpdalo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.UserServiceimp#selectByUsername(java.lang.String)}.
	 */
	@Test
	public void testSelectByUsername() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.UserServiceimp#dogin(com.zhangzhi.cms.domain.User)}.
	 */
	@Test
	public void testDogin() {
		fail("Not yet implemented");
	}

}
