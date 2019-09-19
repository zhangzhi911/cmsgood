/**
 * 
 */
package com.zhangzhi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author zhangzhi
 *2019年9月11日
 */

@RequestMapping("admin")
@Controller
public class AdminController {
	
	
	
	@RequestMapping(value = {"/index","/",""})
	public ModelAndView userjsp() {
		
		ModelAndView mv = new ModelAndView("admin/index");
		
		return mv;
	}
	
	
	
}
