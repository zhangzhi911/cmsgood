/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhangzhi.domain.Category;
import com.zhangzhi.domain.Channel;
import com.zhangzhi.service.CategoryService;
import com.zhangzhi.service.ChannelService;

/**
 * @author zhangzhi
 *2019年9月17日
 */
public class CategoryServiceimpTest extends JunitParent {

	@Resource
	ChannelService daoch;
	
	@Resource
	CategoryService dao;
	@Test
	public void testSelectsBy() {
		List<Category> list = dao.selectsBy(1);
		for (Category ca: list) {
			System.out.println(ca);
			
		}
	}
	
	@Test
	public void testSelectsCh() {
		List<Channel> list = daoch.selects();
	for (Channel ch : list) {
		System.out.println(ch);
	}
	}
	

}
