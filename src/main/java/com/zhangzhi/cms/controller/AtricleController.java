/**
 * 
 */
package com.zhangzhi.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhi.cms.dao.ArticleEs;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.domain.Channel;
import com.zhangzhi.cms.domain.Comment;
import com.zhangzhi.cms.domain.User;
import com.zhangzhi.cms.service.ArticleService;
import com.zhangzhi.cms.service.ChannelService;
import com.zhangzhi.cms.service.CommentService;
import com.zhangzhi.cms.util.ESUtils;

/**
 * @author zhangzhi 2019年9月10日
 */
@SuppressWarnings("rawtypes")
@RequestMapping("article")
@Controller
public class AtricleController {

	@Resource
	private ChannelService chandao;
	
	@Resource
	ArticleService dao;

//	评论
	@Resource
	CommentService daocom;

	@Resource
	private RedisTemplate redisdao;

	@Resource
	private ArticleEs daoes;

//	kafka的类
	@Autowired
	private KafkaTemplate k;
	
	
	
	@Resource
	private ElasticsearchTemplate elastic;

	// 专高二内容 搜索框
	@RequestMapping("search")
	public String search(String key, Model model,@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		// 查询所有栏目
		List<Channel> list = chandao.selects();
		model.addAttribute("channels", list);

		long start = System.currentTimeMillis();

//		List<Article> articles = daoes.findByTitle(key);
//		在es 中查询，然后在存到 热点文章当中

		AggregatedPage<Article> selectObjects = (AggregatedPage<Article>) ESUtils.selectObjects(elastic, Article.class,
				Integer.parseInt(num), 5, new String[] { "title" }, key);
		List<Article> content = selectObjects.getContent();
		PageInfo<Article> artpage = new PageInfo<Article>(content);
		
		artpage.setPageNum(Integer.parseInt(num));
		artpage.setTotal(selectObjects.getTotalElements());
		int pages=0;
		if(artpage.getTotal()%5!=0) {
			pages=(int)(artpage.getTotal()/5+1);
		}else {
			pages=(int)(artpage.getTotal()/5);
		}
		
		
		
		artpage.setPages(pages);
		System.out.println(num+"  页数:"+artpage.getPages());

		
		long end = System.currentTimeMillis();
		System.err.println("本次搜索用了 " + (end - start));

		model.addAttribute("hotArticles", artpage);
		model.addAttribute("key", key);

//		最新文章
		Article newa = new Article();
		newa.setStatus(1);
		newa.setDeleted(1);
//		专高二的redis
		List<Article> newredis = (List<Article>) redisdao.opsForValue().get("NewArticles");

		if (newredis != null && newredis.size() > 0) {
			model.addAttribute("newart", newredis);
		} else {
			PageHelper.startPage(1, 10);
			List<Article> newart = dao.selects(newa);
			redisdao.opsForValue().set("NewArticles", newart);
			model.addAttribute("newart", newart);
		}

		return "index/index";
	}

//	查询
	@RequestMapping("selects")
	public ModelAndView showjsp(@ModelAttribute Article art,
			@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		art.setDeleted(1); // 查未删除的

		ModelAndView mv = new ModelAndView("admin/atricle");
		PageHelper.startPage(Integer.parseInt(num), 5);

		List<Article> list = dao.selects(art);

		PageInfo<Article> artpage = new PageInfo<Article>(list);
		mv.addObject("statu", art.getStatus());
		mv.addObject("artpage", artpage);
		return mv;
	}

//	跳转到文章详情，管理员查询
	@RequestMapping("select")
	public String select(int id, Model model) {
		Article art = dao.selectByPrimaryKey(id);

		model.addAttribute("art", art);
		
		return "admin/atricles";
	}

//	可能是管理员 通过获驳回
	@ResponseBody
	@RequestMapping("upda")
	public boolean upda(Article article) {
		
		int i = dao.updateByPrimaryKeySelective(article);
		Article art = dao.selectByPrimaryKey(article.getId());
		daoes.save(art);
		
		redisdao.delete("NewArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		redisdao.delete("HotArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		
		return i > 0;
	}

//发布文章
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}

	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article art, MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
//		上传图片
		if (!file.isEmpty()) {
			String orname = file.getOriginalFilename();
			String name = UUID.randomUUID() + orname.substring(orname.lastIndexOf("."));
			String path = "e:/pic/";
			File f = new File(path + name);
			file.transferTo(f);
			art.setPicture(name);
		}

		// 默认文章的基本属性
		// false 从request获取session对象如果没有就是null，如果有就是session
		HttpSession session = request.getSession(false);
 
		redisdao.delete("NewArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		redisdao.delete("HotArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		
		
		art.setHot(1);// 文章为非热门
		art.setStatus(0);// 文章为待审核
		art.setHits(0);// 文章点击量默认为0
		art.setDeleted(1);// 文章删除状态0
		art.setCreated(new Date());// 文章发布时间
		art.setUpdated(new Date());// 文章修改时间
		if (session != null) {
			User us = (User) session.getAttribute("user");
			art.setUserId(us.getId());
		} else {
			return false;// 没有登录不能发布
		}
//		daoes.save(art);
		String ktr = JSON.toJSONString(art);
		k.send("cmskaf",ktr);
//		int i = dao.insertSelective(art);
		
//		文章内容保存到图片

		return 1> 0;
	}

//查看个人所有文章
	@RequestMapping("selectsByUser")
	public String selectsByUser(Model model, @ModelAttribute Article art,
			@RequestParam(value = "pageNum", defaultValue = "1") String num, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		art.setDeleted(1);
		art.setStatus(1);

		PageHelper.startPage(Integer.parseInt(num), 8);

		User us = (User) request.getSession().getAttribute("user");

		art.setUserId(us.getId());

		List<Article> list = dao.selects(art);

		PageInfo<Article> artpage = new PageInfo<Article>(list);
		model.addAttribute("artpageBy", artpage);
		model.addAttribute("bytitle", art.getTitle());// 模糊查询

		return "my/articles";
	}

//	判断是否还有上一篇
	@ResponseBody
	@GetMapping("checkPre")
	public Boolean checkPre(Article article) {
		article.setDeleted(1); // 不可以查删除的
		article.setStatus(1); // 不可以审核不过的
		Article a = dao.preselectBy(article);
		return a != null;
	}

//	上一篇
	@RequestMapping("preselectBy")
	public String preselectBy(Article article, Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		article.setDeleted(1); // 不可以查删除的
		article.setStatus(1); // 不可以审核不过的
		Article art = dao.preselectBy(article);

//  查看点击率排行榜		
		PageHelper.startPage(1, 10);
		List<Article> arthits = dao.selecthits(null);

//  查看评论排行榜
		PageHelper.startPage(1, 10);
		List<Article> artcoms = dao.selectcoms(null);

//	评论
		User us = new User(); // 这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);

		model.addAttribute("coments", comlist);
		model.addAttribute("comentpage", pageInfo);

		model.addAttribute("arthits", arthits);// 点击率
		model.addAttribute("artcoms", artcoms);// 评论排行

		model.addAttribute("art", art);

		return "my/article";
	}

//查看自己单个的文章	用于自己查询
	@RequestMapping("selectsBy")
	public String selectsBy(int id, Model model, @RequestParam(value = "pageNum", defaultValue = "1") String num,HttpServletRequest request) {
		
		
//每来一次浏览量都加一
//		将数据发到redis
		Article art = (Article) redisdao.opsForValue().get("Hits_${"+id+"}_${"+request.getRemoteAddr()+"}");
		if(art==null) {
			art = dao.selectByPrimaryKey(id);
			redisdao.opsForValue().set("Hits_${"+id+"}_${"+request.getRemoteAddr()+"}", art,5,TimeUnit.MINUTES);
		}
		
		
		//这个用于浏览量的增加
		Article pagepi = new Article();
		pagepi.setId(id);
		pagepi.setPageview(1);
		dao.updateByPrimaryKeySelective(pagepi);//修改浏览量
		
//		发动到kafka  修改文章浏览量
//		kafka.send("articles","articleID="+id+"");

		
		
		daoes.save(art);//浏览量会修改ES中的数据
		
		
//  查看点击率排行榜		
		PageHelper.startPage(1, 10);
		List<Article> arthits = dao.selecthits(null);

//  查看评论排行榜
		PageHelper.startPage(1, 10);
		List<Article> artcoms = dao.selectcoms(null);

//查看评论		
		User us = new User(); // 这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 10);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);

		model.addAttribute("coments", comlist);// 评论
		model.addAttribute("comentpage", pageInfo);// 评论分页

		model.addAttribute("art", art);// 文章
		model.addAttribute("arthits", arthits);// 点击率
		model.addAttribute("artcoms", artcoms);// 评论排行

		return "my/article";
	}

//	下一篇
	@RequestMapping("nextselectBy")
	public String nextselectBy(Article article, Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") String num) {

		article.setDeleted(1); // 不可以查删除的
		article.setStatus(1); // 不可以审核不过的
		Article art = dao.nextselectBy(article);

		// 查看点击率排行榜
		PageHelper.startPage(1, 10);
		List<Article> arthits = dao.selecthits(null);

		// 查看评论排行榜
		PageHelper.startPage(1, 10);
		List<Article> artcoms = dao.selectcoms(null);

//	评论
		User us = new User(); // 这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);

		model.addAttribute("coments", comlist);
		model.addAttribute("comentpage", pageInfo);

		model.addAttribute("arthits", arthits);// 点击率
		model.addAttribute("artcoms", artcoms);// 评论排行

		model.addAttribute("art", art);

		return "my/article";
	}

//	判断是否还有下一篇
	@ResponseBody
	@GetMapping("chackNext")
	public Boolean chackNext(Article article) {
		article.setDeleted(1); // 不可以查删除的
		article.setStatus(1); // 不可以审核不过的
		Article a = dao.nextselectBy(article);
		return a != null;
	}

//添加评论  文章的评论也会加一
	@ResponseBody
	@PostMapping("coment")
	public boolean coment(Comment c, HttpServletRequest request) {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}

		c.setUserid(user); // 评论用户
		c.setCreated(new Date()); // 日期

		Article art = new Article();
		art.setId(c.getArticle().getId());
		art.setComments(1); // 默认一次只加一条评论
		dao.updateByPrimaryKeySelective(art);// 给文章的评论加一

		return daocom.insert(c) > 0;
	}

//	评论点赞
	@ResponseBody
	@PostMapping("comentupda")
	public boolean comentupda(Comment c, HttpServletRequest request) {
		c.setHits(1);// 默认点赞加一
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return daocom.upda(c) > 0;
	}

//	文章点赞
	@ResponseBody
	@PostMapping("updateArt")
	public boolean updateArt(Article art, HttpServletRequest request) {
		art.setHits(1);// 默认点赞加一
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
//		在点赞加一后  存到ES
		int i = dao.updateByPrimaryKeySelective(art);
		Article arti = dao.selectByPrimaryKey(art.getId());
		daoes.save(arti);
		
		redisdao.delete("NewArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		redisdao.delete("HotArticles");// 因为涉及到数据库的更新，所以要删除redis中的数据
		
		
		
		
		return i> 0;
	}

//	去修改个人文章
	@GetMapping("update")
	public String update(Integer id, Model model) {

		Article art = dao.selectByPrimaryKey(id);
		model.addAttribute("upart", art);

		return "/my/articupdate";
	}

	@ResponseBody
	@PostMapping("update")
	public boolean update(Article art, MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
//		上传图片
		if (!file.isEmpty()) {
			String orname = file.getOriginalFilename();
			String name = UUID.randomUUID() + orname.substring(orname.lastIndexOf("."));
			String path = "e:/pic/";
			File f = new File(path + name);
			file.transferTo(f);
			art.setPicture(name);
		}

		daoes.save(art);
		
		
		return dao.updateByPrimaryKeySelective(art) > 0;
	}
}