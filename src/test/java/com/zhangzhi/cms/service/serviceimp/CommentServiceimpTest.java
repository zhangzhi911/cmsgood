/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;

import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.domain.Comment;
import com.zhangzhi.cms.domain.User;
import com.zhangzhi.cms.service.CommentService;
import com.zhangzhi.service.serviceimp.JunitParent;

/**
 * @author zhangzhi
 *2019年9月25日
 */
public class CommentServiceimpTest extends JunitParent {

	@Resource
	private CommentService dao;


	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.CommentServiceimp#selects(com.zhangzhi.cms.domain.Comment)}.
	 */
	@Test
	public void testSelects() {
		Comment c = new Comment();
		c.setArticle(new Article());
		c.setUserid(new User());
		List<Comment> lists = dao.selects(c);
		for (Comment comment : lists) {
			System.out.println(comment);
		}
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.CommentServiceimp#insert(com.zhangzhi.cms.domain.Comment)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.CommentServiceimp#upda(com.zhangzhi.cms.domain.Comment)}.
	 */
	@Test
	public void testUpda() {
		fail("Not yet implemented");
	}

}
