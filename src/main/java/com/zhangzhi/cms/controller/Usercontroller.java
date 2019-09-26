/**
 * 
 */
package com.zhangzhi.cms.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhi.cms.domain.User;
import com.zhangzhi.cms.service.UserService;

/**
 * @author zhangzhi
 *2019年9月11日
 */
@RequestMapping("user")
@Controller
public class Usercontroller {
	
	@Resource
	UserService dao;
	
	
	@RequestMapping("selects")
	public ModelAndView userjsp(@RequestParam(value = "pageNum",defaultValue = "1") String num,@RequestParam(value = "name",required = false) String name) {
		ModelAndView mv = new ModelAndView("admin/user");
		PageHelper.startPage(Integer.parseInt(num), 3);
		List<User> userlist = dao.selects(name);
		PageInfo<User> userpage = new PageInfo<User>(userlist);
		
		
		mv.addObject("userpage", userpage);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("updalo")
	public Boolean updalo(User user) throws IOException {
		if(dao.updalo(user)>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}