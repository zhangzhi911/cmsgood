/**
 * 
 */
package com.zhangzhi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhangzhi.domain.User;
import com.zhangzhi.vo.UserVo;

/**
 * @author zhangzhi
 *2019年9月10日
 */
public interface UserService {
	List<User> selects(String name);
	
	User selectByUsername(String username);

	
    int insertSelective(UserVo record);
    //单条查询用
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);
    int updalo(User record);

	/**
	 * @param user
	 */
    //登录的方法
	User dogin(User user);    
}