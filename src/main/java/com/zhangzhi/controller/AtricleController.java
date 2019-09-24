/**
 * 
 */
package com.zhangzhi.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhi.domain.Article;
import com.zhangzhi.domain.Comment;
import com.zhangzhi.domain.User;
import com.zhangzhi.service.ArticleService;
import com.zhangzhi.service.CommentService;

/**
 * @author zhangzhi 2019年9月10日
 */
@RequestMapping("article")
@Controller
public class AtricleController {

	@Resource
	ArticleService dao;

//	评论
	@Resource
	CommentService daocom;
	
	@RequestMapping("selects")
	public ModelAndView showjsp(@ModelAttribute Article art,
			@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		art.setDeleted(1); //查未删除的

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
	public String select(int id,Model model) {
		Article art = dao.selectByPrimaryKey(id);
		
		
		model.addAttribute("art", art);
		return "admin/atricles";
	}
	
	@ResponseBody
	@RequestMapping("upda")
	public boolean upda(Article article) {
		return dao.updateByPrimaryKeySelective(article)>0;
	}


//发布文章
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}


	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article art,MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
//		上传图片
		if(!file.isEmpty()) {
			String orname = file.getOriginalFilename();
			String name=UUID.randomUUID()+orname.substring(orname.lastIndexOf("."));
			String path="e:/pic/";
			File f = new File(path+name);
			file.transferTo(f);
			art.setPicture(name);
		}
		
		//默认文章的基本属性
		//false  从request获取session对象如果没有就是null，如果有就是session
		
		HttpSession session = request.getSession(false);
		art.setHot(1);//文章为非热门
		art.setStatus(0);//文章为待审核
		art.setHits(0);//文章点击量默认为0
		art.setDeleted(0);//文章删除状态0
		art.setCreated(new Date());//文章发布时间
		art.setUpdated(new Date());//文章修改时间
		if(session!=null) {
			User us=(User) session.getAttribute("user");
			art.setUserId(us.getId());
		}else {
			return false;//没有登录不能发布  
		}
		
		int i = dao.insertSelective(art);
//		文章内容保存到图片
		
		return i>0;
	}	

//查看个人所有文章
	@RequestMapping("selectsByUser")
	public String selectsByUser(Model model,@ModelAttribute Article art,
			@RequestParam(value = "pageNum",defaultValue = "1")String num,HttpServletRequest request,HttpServletResponse response) throws IOException {
		art.setDeleted(1);
		art.setStatus(1);
		
		PageHelper.startPage(Integer.parseInt(num), 8);

		User us = (User) request.getSession().getAttribute("user");
		
		art.setUserId(us.getId());
		
		
		List<Article> list = dao.selects(art);

		PageInfo<Article> artpage = new PageInfo<Article>(list);
		model.addAttribute("artpageBy", artpage);
		model.addAttribute("bytitle", art.getTitle());//模糊查询
		
		
		return "my/articles";
	}
	
//	判断是否还有上一篇
	@ResponseBody
	@GetMapping("checkPre")
	public Boolean checkPre(Article article) {
		article.setDeleted(1); //不可以查删除的
		article.setStatus(1); //不可以审核不过的
		Article a = dao.preselectBy(article);
		return a!=null;
	}
	
//	上一篇
	@RequestMapping("preselectBy")
	public String preselectBy(Article article,Model model,@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		article.setDeleted(1); //不可以查删除的
		article.setStatus(1); //不可以审核不过的
		Article art = dao.preselectBy(article);

//	评论
		User us = new User();  //这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);
		
		model.addAttribute("coments", comlist);
		model.addAttribute("comentpage", pageInfo);
		
		model.addAttribute("art", art);
		
		return "my/article";
	}
	
	
	
//查看自己单个的文章	用于自己查询
	@RequestMapping("selectsBy")
	public String selectsBy(int id,Model model,@RequestParam(value = "pageNum", defaultValue = "1") String num) {
		Article art = dao.selectByPrimaryKey(id);
		

//查看评论		
		User us = new User();  //这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);
		
		model.addAttribute("coments", comlist);
		model.addAttribute("comentpage", pageInfo);
		
		model.addAttribute("art", art);

		return "my/article";
	}
	
	
//	下一篇
	@RequestMapping("nextselectBy")
	public String nextselectBy(Article article,Model model,@RequestParam(value = "pageNum", defaultValue = "1") String num) {
	
		article.setDeleted(1); //不可以查删除的
		article.setStatus(1); //不可以审核不过的
		Article art = dao.nextselectBy(article);

//	评论
		User us = new User();  //这里new一个user是没什用但是得有
		Comment c = new Comment();
		c.setUserid(us);
		c.setArticle(art);
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> comlist = daocom.selects(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comlist);
		
		model.addAttribute("coments", comlist);
		model.addAttribute("comentpage", pageInfo);
		
		model.addAttribute("art", art);
		
		return "my/article";
	}
	
	
//	判断是否还有下一篇
	@ResponseBody
	@GetMapping("chackNext")
	public Boolean chackNext(Article article) {
		article.setDeleted(1); //不可以查删除的
		article.setStatus(1); //不可以审核不过的
		Article a = dao.nextselectBy(article);
		return a!=null;
	}
	
	
	
//添加评论
	@ResponseBody
	@PostMapping("coment")
	public boolean coment(Comment c,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		if(user==null ) {
			return false;
		}
		c.setUserid(user);  //评论用户
		c.setCreated(new Date());  //日期
		return daocom.insert(c)>0;
	}
	
//	评论点赞
	@ResponseBody
	@PostMapping("comentupda")
	public boolean comentupda(Comment c,HttpServletRequest request) {
		c.setHits(1);//默认点赞加一
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		if(user==null ) {
			return false;
		}
		return daocom.upda(c)>0;
	}
	
//	文章点赞
	@ResponseBody
	@PostMapping("updateArt")
	public boolean updateArt(Article art,HttpServletRequest request) {
		art.setHits(1);//默认点赞加一
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		if(user==null ) {
			return false;
		}
		return dao.updateByPrimaryKeySelective(art)>0;
	}
	
	
//	去修改个人文章
	@GetMapping("update")
	public String update(Integer id,Model model) {
		
		Article art = dao.selectByPrimaryKey(id);
		model.addAttribute("upart",art);
		
		return "/my/articupdate";
	}
	
	@ResponseBody
	@PostMapping("update")
	public boolean update(Article art,MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		
//		上传图片
		if(!file.isEmpty()) {
			String orname = file.getOriginalFilename();
			String name=UUID.randomUUID()+orname.substring(orname.lastIndexOf("."));
			String path="e:/pic/";
			File f = new File(path+name);
			file.transferTo(f);
			art.setPicture(name);
		}
		
		return dao.updateByPrimaryKeySelective(art)>0;
	}
	
	
	
	
	
	
	
	
	
	
	






}