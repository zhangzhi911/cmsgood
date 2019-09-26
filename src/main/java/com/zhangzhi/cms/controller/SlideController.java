/**
 * 
 */
package com.zhangzhi.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangzhi.cms.domain.Slide;
import com.zhangzhi.cms.service.SlideService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
@RequestMapping("slidecon")
@Controller
public class SlideController {
	
	@Resource
	private SlideService dao;
	
	@ResponseBody
	@GetMapping("selects")
	public List<Slide> selects() {
		return dao.selects();
	}
	
	
}
