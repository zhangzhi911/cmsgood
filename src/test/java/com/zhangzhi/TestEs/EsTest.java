/**
 * 
 */
package com.zhangzhi.TestEs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.service.ArticleService;
import com.zhangzhi.cms.service.ArticleServiceEs;

/**
 * @author zhangzhi
 *2019年10月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class EsTest {
	
	
	@Autowired
	private ArticleServiceEs dao;
	
	@Autowired
	private ArticleService articledao;
	
	
	@Test
	public void TestTmpor2test() {
		Article article=new Article();
		List<Article> list = articledao.selects(article);
		System.out.println(list.size()); 
		
		for (Article article2 : list) {
			dao.save(article2);
		}
	}
	
	@Test
	public void testceil() {
		int pages=0;
		if(14.0%4.0!=0) {
			pages=10/4+1;
		}
		System.out.println(pages);
	}
	
	
	
}
