/**
 * 
 */
package com.zhangzhi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhangzhi.domain.User;
import com.zhangzhi.service.UserService;
import com.zhangzhi.util.CMSException;
import com.zhangzhi.vo.UserVo;

/**
 * @author zhangzhi
 *2019年9月18日
 */
@RequestMapping("passport")
@Controller
public class PassprotController {
	
	@Resource
	UserService dao;
	
	//执行注册
	@GetMapping("reg")  
	public String reg() {
		
		return "passport/reg";
		
	}
	
	//去注册
	@PostMapping("reg")
	public String reg(@Valid UserVo userVo,Model model,BindingResult bind,RedirectAttributes red){
//		注册，如果没有异常，就跳转到登录页面
		try {
//			如果输入的信息不如何要求则回到注册页面
			if(bind.hasErrors()) {
				return "passport/reg";
			}
			
			 dao.insertSelective(userVo);
			 red.addFlashAttribute("username", userVo.getUsername());
			return "redirect:/passport/login";
		} catch (CMSException e) {
			e.printStackTrace();//给程序员看
			model.addAttribute("errer", e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errer", "注册失败，系统错误");
			
		}
		return "passport/reg";
	}

	
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	

	@PostMapping("login")
	public String login(User user,Model model,BindingResult bind,HttpServletRequest request) {
		try {
			User us = dao.dogin(user);
			//登录成功,根据角色判断当前登录人要进入的页面
			request.getSession().setAttribute("user", us);
			if(us.getRole().equals("0")) {
				return "redirect:/my";
			}
			return "redirect:/admin";
			
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("errer", e.getMessage());;	
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errer", "登录失败,系统错误");;	
		}
		
		return "passport/login";
	}
	
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/passport/login";
	}
	
	
}






















