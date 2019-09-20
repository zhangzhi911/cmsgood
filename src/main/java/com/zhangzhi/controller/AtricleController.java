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
import com.zhangzhi.domain.User;
import com.zhangzhi.service.ArticleService;

/**
 * @author zhangzhi 2019年9月10日
 */
@RequestMapping("article")
@Controller
public class AtricleController {

	@Resource
	ArticleService dao;

	@RequestMapping("selects")
	public ModelAndView showjsp(@ModelAttribute Article art,
			@RequestParam(value = "pageNum", defaultValue = "1") String num) {

		ModelAndView mv = new ModelAndView("admin/atricle");
		PageHelper.startPage(Integer.parseInt(num), 5);

		List<Article> list = dao.selects(art);

		PageInfo<Article> artpage = new PageInfo<Article>(list);
		mv.addObject("artpage", artpage);
		return mv;
	}
	
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
		art.setHot(0);//文章为非热门
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
			@RequestParam(value = "pageNum",defaultValue = "1")String num,HttpServletRequest request) {
		PageHelper.startPage(Integer.parseInt(num), 8);

		User us = (User) request.getSession().getAttribute("user");
		
		art.setUserId(us.getId());
		
		List<Article> list = dao.selects(art);

		PageInfo<Article> artpage = new PageInfo<Article>(list);
		model.addAttribute("artpageBy", artpage);
		
		
		return "my/articles";
	}
	
//查看自己单个的文章	
	@RequestMapping("selectsBy")
	public String selectsBy(int id,Model model) {
		Article art = dao.selectByPrimaryKey(id);
		model.addAttribute("art", art);
		return "my/article";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	






}