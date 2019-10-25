/**
 * 
 */
package com.zhangzhi.redisTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhangzhi.service.serviceimp.JunitParent;

/**
 * @author zhangzhi
 *2019年10月24日
 */
public class TestListenConsummer extends JunitParent {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext clas = new ClassPathXmlApplicationContext("classpath:consummer.xml");
	}
	
	
}
