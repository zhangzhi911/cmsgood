/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangzhi
 *2019年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:producer.xml")
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String,String> k;
	
	@Test
	public void tesetdeed() {
		k.send("1705E","你好，我是新来的数2据");
	}
	
}
