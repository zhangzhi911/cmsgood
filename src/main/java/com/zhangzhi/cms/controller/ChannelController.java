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

import com.zhangzhi.cms.domain.Channel;
import com.zhangzhi.cms.service.ChannelService;

/**
 * @author zhangzhi
 *2019年9月17日
 */
@RequestMapping("channel")
@Controller
public class ChannelController {

	@Resource
	ChannelService dao;
	
	
	@ResponseBody
	@GetMapping("selecs")
	public List<Channel> selects(){
		return dao.selects();
	}
	
}
