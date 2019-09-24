/**
 * 
 */
package com.zhangzhi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhi.domain.Article;
import com.zhangzhi.domain.Comment;
import com.zhangzhi.domain.User;
import com.zhangzhi.service.CommentService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
@RequestMapping("comment")
@Controller
public class CommentController {
	
	
	@Resource
	private CommentService dao;
	
	
	@GetMapping("selects")
	public String selects(@ModelAttribute Comment comment,
			@RequestParam(value = "pageNum", defaultValue = "1") String num,HttpServletRequest request,Model model) {
		Article a = new Article();

		User us = (User) request.getSession().getAttribute("user");
		comment.setUserid(us);
		comment.setArticle(a);
		
		PageHelper.startPage(Integer.parseInt(num), 5);
		List<Comment> lists = dao.selects(comment);
		PageInfo<Comment> page = new PageInfo<Comment>(lists);

		model.addAttribute("compage", page);
		model.addAttribute("coms", lists);
		
		return "/my/comments";
	}
	
	
	
}
