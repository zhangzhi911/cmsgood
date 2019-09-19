/**
 * 
 */
package com.zhangzhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangzhi
 *2019年9月17日
 */
@RequestMapping("my")
@Controller
public class MyController {

//个人中心的首页	
	@GetMapping({"/","","/index"})
	public String index() {
		

		return "my/index";
	}
	
}
