/**
 * 
 */
package com.zhangzhi.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.domain.Category;
import com.zhangzhi.cms.domain.Channel;
import com.zhangzhi.cms.domain.Slide;
import com.zhangzhi.cms.service.ArticleService;
import com.zhangzhi.cms.service.CategoryService;
import com.zhangzhi.cms.service.ChannelService;
import com.zhangzhi.cms.service.SlideService;

/**
 * @author zhangzhi
 *2019年9月19日
 */
@RequestMapping(value = {"index","","/"})
@Controller
public class IndexController {
	
	@Resource
	private ChannelService dao;
	
	@Resource
	private ArticleService artdao;
	
	@Resource
	private CategoryService cadao;
	
	@Resource
	private SlideService daosli;
	
	@GetMapping(value = "") //第一次进来的地方
	public String index(Model model,Article art,@RequestParam(value = "pageNum", defaultValue = "1") String num) {
//		查询审核过的文章
		art.setStatus(1);
		art.setDeleted(1);
		//查询所有栏目
		List<Channel> list = dao.selects();
		model.addAttribute("channels", list);
//		如果栏目id空的则热门文章
		if(art.getChannelId()==null) {
			art.setHot(1);
			PageHelper.startPage(Integer.parseInt(num), 5);
			List<Article> artlist = artdao.selects(art);
			PageInfo<Article> artpage = new PageInfo<Article>(artlist);
			model.addAttribute("hotArticles", artpage);
		}
//		如果不空，则具体文章  这里只是去掉了setHot（1）
		if(art.getChannelId()!=null) {
//			这里查分类			
			List<Category> categorys = cadao.selectsBy(art.getChannelId());
			model.addAttribute("categorys", categorys); //分类的集合
				
//			这里是查栏目分类下的文章 分类的id  art也会自动接收
			PageHelper.startPage(Integer.parseInt(num), 5);
			List<Article> artlist = artdao.selects(art);
			PageInfo<Article> artpage = new PageInfo<Article>(artlist);
			model.addAttribute("hotArticles", artpage);
		}
		
//		最新文章
		PageHelper.startPage(1, 10);
		Article newa = new Article();
		newa.setStatus(1);
		newa.setDeleted(1);
		List<Article> newart = artdao.selects(newa);
		model.addAttribute("newart", newart);
		
//		广告
		List<Slide> slides = daosli.selects();
		System.out.println(slides);
		model.addAttribute("slides", slides);
		
		model.addAttribute("article", art); //把对象传过去 拿着art的栏目id去js中整
		
		return "index/index";
	}
	
	
	
	
}

























