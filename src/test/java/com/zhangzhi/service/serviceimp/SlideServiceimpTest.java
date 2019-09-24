/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhangzhi.domain.Slide;
import com.zhangzhi.service.SlideService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
public class SlideServiceimpTest extends JunitParent{

	@Resource
	private SlideService dao;
	

	@Test
	public void testSelects() {
		List<Slide> se = dao.selects();
		for (Slide slide : se) {
			System.out.println(slide);
		}
	}
	@Test
	public void teest() {
		
	}

}
