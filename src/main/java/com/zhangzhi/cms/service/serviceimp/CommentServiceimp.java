/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.cms.dao.CommentMapper;
import com.zhangzhi.cms.domain.Comment;
import com.zhangzhi.cms.service.CommentService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
@Service
public class CommentServiceimp implements CommentService{

	@Resource
	private CommentMapper dao;
	
	
	@Override
	public List<Comment> selects(Comment comment) {
		// TODO Auto-generated method stub
		return dao.selects(comment);
	}

	@Override
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		return dao.insert(comment);
	}

	@Override
	public int upda(Comment comment) {
		// TODO Auto-generated method stub
		return dao.upda(comment);
	}

}
