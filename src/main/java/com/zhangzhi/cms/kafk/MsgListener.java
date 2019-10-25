/**
 * 
 */
package com.zhangzhi.cms.kafk;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhangzhi.cms.domain.Article;
import com.zhangzhi.cms.service.ArticleService;
import com.zhangzhi.cms.service.ArticleServiceEs;

/**
 * @author zhangzhi 2019年10月17日
 */
public class MsgListener implements MessageListener<String, String> {

	@Autowired
	private ArticleService dao;

	@Autowired
	private ArticleServiceEs daoes;

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String str = data.value();
		
		if (str.startsWith("articleID")) {         //这里用于增加浏览量
			// 在消费端获取文章ID，再执行数据库加1操作。
			String[] split = str.split("=");
			String id = split[1];
			// 从数据库查文章
			Article article = dao.selectByPrimaryKey(Integer.parseInt(id));
			article.setHits(article.getPageview() + 1);
			// 更新到数据库
			dao.updateByPrimaryKeySelective(article);
		}

		Article art = JSON.parseObject(str, Article.class);
		int i = dao.insertSelective(art);
		art.setId(i);
		// 保存到索引库
		daoes.save(art);

		System.out.println(i);

	}

}
