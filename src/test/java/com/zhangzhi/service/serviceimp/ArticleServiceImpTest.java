/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.lf5.util.StreamUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.zhangzhi.cms.dao.ArticleMapper;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.domain.Category;
import com.zhangzhi.cms.domain.Comment;
import com.zhangzhi.cms.domain.User;
import com.zhangzhi.cms.service.ArticleService;
import com.zhangzhi.cms.service.CategoryService;
import com.zhangzhi.cms.service.ChannelService;
import com.zhangzhi.cms.service.CommentService;
import com.zhangzhi.cms.service.UserService;
import com.zhangzhi.myUtil.DateUtil;
import com.zhangzhi.myUtil.RandomUtil;
import com.zhangzhi.myUtil.StreamUtil;

/**
 * @author zhangzhi
 */
public class ArticleServiceImpTest extends JunitParent{

	@Resource
	UserService dao;
	
	@Resource
	ArticleMapper daoar;
	
	@Resource
	CommentService daocom;
	
	@Resource
	private ChannelService daochannel;
	
	@Resource
	private CategoryService daocate;
	
	@Resource
	private KafkaTemplate kafdao;
	
	@Resource
	private RedisTemplate redao;
	
	@Autowired
	private ArticleService daoart;
	
	@Test
	public void testSelects() {
		List<User> list = dao.selects(null);
		System.out.println(list);
	}

	/**
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.ArticleServiceImp#deleteByPrimaryKey(java.lang.Integer)}.
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
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.ArticleServiceImp#insertSelective(com.zhangzhi.cms.domain.Article)}.
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
	 * Test method for {@link com.zhangzhi.cms.service.serviceimp.ArticleServiceImp#selectByPrimaryKey(java.lang.Integer)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSelectByPrimaryKey() {
		Comment c = new Comment();
		c.setId(1);
		c.setHits(2);
		int i = daocom.upda(c);
		System.out.println(i);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		Comment comment=new Comment();
		Article a = new Article();
		User user = new User();
		user.setId(13);
		comment.setArticle(a);
		comment.setUserid(user);
		
		
		List<Comment> liset = daocom.selects(comment);
	
		for (Comment c : liset) {
			System.out.println(c.getUserid());
		}
	}
	
//	解析文章
	@SuppressWarnings("unchecked")
	@Test
	public void testFile() {
		File files = new File("E:\\aa\\abcd");
		File[] fi = files.listFiles();
		List<Article> arr = new ArrayList();
		int a=0;
		for (File file : fi) {
			a++;
			if(a>=50) {
				break;
			}
			Article art = new Article();
			String title = file.getName();//标题
			art.setTitle(title.substring(0,title.lastIndexOf(".")));
			
			
			int channel = RandomUtil.random(1, 9);
			art.setChannelId(channel);
			//根据栏目id查询分类
			
			
			List<Category> list = daocate.selectsBy(channel);
			
			Category category = list.get(RandomUtil.random(0, list.size()-1));
			art.setCategoryId(category.getId());
			
			String con = StreamUtil.readTextFile(file);//内容
			art.setContent(con);
			
			art.setSummary(con.substring(0,140));//摘要
			
			
			Calendar c = Calendar.getInstance();
			c.set(2019, 0, 1, 0, 0, 0);
			Date date = DateUtil.randomDate(c.getTime(), new Date());
			art.setCreated(new Date());//创建日期
			art.setUpdated(date);
			
//			art.setHot(RandomUtil.random(0, 1));//是否热
			art.setHot(1);
			art.setPicture("98576e31-6eef-4b40-9e62-0b495d9bf29b.png");//默认的一照片
			art.setHits(RandomUtil.random(0, 100000));//点击量
			art.setStatus(1);//已审核
			art.setDeleted(1);//未删除
			art.setUserId(125);
			art.setHits(0);
			art.setPageview(0);
			
//			daoar.insertSelective(art);
			arr.add(art);
		}
		System.out.println(a);
		String str = JSON.toJSONString(arr);
		redao.opsForValue().set("50art", str);

		
//		String str = StreamUtil.readTextFile(new File("E:\\aa\\日2.txt"));
//		System.out.println(str.trim());
	}
	
	
	@Test
	public void pretest() {
		Article article=new Article();
		article.setId(33);
		article.setChannelId(4);
		Article art = daoar.preselectBy(article);
		System.out.println(art.getId());
	}
	
	@Test
	public void nexttest() {
		Article article=new Article();
		article.setId(40);
		article.setChannelId(4);
		Article art = daoar.nextselectBy(article);
		System.out.println(art.getId());
	}
	
	@Test
	public void testmyfile() {
		File file = new File("E:\\aa\\dao");
		File[] lists = file.listFiles();
		for (File fi : lists) {
			Article art = new Article();
			
			art.setTitle(fi.getName());

			String str = StreamUtil.readTextFile(fi);
			art.setContent(str);
			
			art.setHot(1);
			art.setStatus(1);
			art.setDeleted(1);
			art.setCreated(new Date());
			art.setUpdated(new Date());
			daoar.insertSelective(art);
		}
		
		
		
		
	}

	
	
	
	
	
	
	
	
//	测试点击率
	@Test
	public void selecthist() {
		
		List<Article> list = daoar.selecthits(null);
		for (Article article : list) {
			System.out.println(article.getHits());
		}
	}
	
//	测试点击率
	@Test
	public void selectcoms() {
		
		List<Article> list = daoar.selectcoms(null);
		for (Article article : list) {
			System.out.println(article.getComments());
		}
	}
	
//	测试文章评论加一
	@Test
	public void selectadd() {
		
		Article art = new Article();
		art.setComments(1); //默认一次只加一条评论
//		daoar.updateByPrimaryKeySelective(art);//给文章的评论加一
		art.setTitle("这是测试的");
		int channel = RandomUtil.random(1, 9);
		art.setChannelId(channel);
		//根据栏目id查询分类
		
		art.setContent("56");
		art.setSummary("445");//摘要
		Calendar c = Calendar.getInstance();
		c.set(2019, 0, 1, 0, 0, 0);
		Date date = DateUtil.randomDate(c.getTime(), new Date());
		art.setCreated(new Date());//创建日期
		art.setUpdated(date);
		
//		art.setHot(RandomUtil.random(0, 1));//是否热
		art.setHot(1);
		art.setHits(RandomUtil.random(0, 100000));//点击量
		art.setStatus(1);//已审核
		art.setDeleted(1);//未删除
		art.setUserId(125);
		art.setHits(0);
		art.setPageview(0);
		
		
		daoart.insertSelective(art);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
