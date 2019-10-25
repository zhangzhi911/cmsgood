/**
 * 
 */
package com.zhangzhi.redisTest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.service.serviceimp.JunitParent;

/**
 * @author zhangzhi
 *2019年10月24日
 */
public class TestRedisProduct extends JunitParent {

	
	@Resource
	private RedisTemplate redao;
	
	
	@Resource
	private KafkaTemplate kafdao;
	
	@Test
	public void TestRedis() {
		String str = (String) redao.opsForValue().get("50art");
		List<Article> arr = JSON.parseArray(str,Article.class);
		int a=0;
		for (Article ar : arr) {
			a++;
			if(a>=20) {
				break;
			}
			String st = JSON.toJSONString(ar);
			kafdao.send("cmskaf", st);
		}
		
		
	}
	
	
	
	
}
