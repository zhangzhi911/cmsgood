/**
 * 
 */
package com.zhangzhi.service;

import java.util.List;

import com.zhangzhi.domain.Comment;

/**
 * @author zhangzhi
 *2019年9月21日
 */
public interface CommentService {

	List<Comment> selects(Comment comment);
	
//	增加评论
	int insert(Comment comment);
	int upda(Comment comment);


}
