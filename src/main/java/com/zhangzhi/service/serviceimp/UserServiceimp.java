/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.dao.UserMapper;
import com.zhangzhi.domain.User;
import com.zhangzhi.service.UserService;
import com.zhangzhi.util.CMSException;
import com.zhangzhi.util.Md5Util;
import com.zhangzhi.vo.UserVo;

/**
 * @author zhangzhi
 *2019年9月10日
 */
@Service
public class UserServiceimp implements UserService {

	@Resource
	UserMapper dao;
	
	public List<User> selects(String name) {
		// TODO Auto-generated method stub
		return dao.selects(name);
	}

//注册用户
	public int insertSelective(UserVo userVo) {
		
//		两次密码是否一直
		if(!userVo.getPassword().equals(userVo.getRepassword())) {
			throw new CMSException("两次密码不一致");}
		User user = dao.selectByUsername(userVo.getUsername());
		if(user!=null) {
			throw new CMSException("注册用户已经存在");
		}
		
//		对密码进行加密
		String md5password = Md5Util.md5Encoding(userVo.getPassword());
		userVo.setPassword(md5password);
		userVo.setLocked(0);
		userVo.setCreateTime(new Date());
		userVo.setUpdateTime(new Date());
		userVo.setNickname(userVo.getUsername());
		
		return dao.insertSelective(userVo);
	}

	public User selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		return dao.updateByPrimaryKeySelective(record);
	}


	@Override
	public int updalo(User record) {
		// TODO Auto-generated method stub
		return dao.updalo(record);
	}


	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.selectByUsername(username);
	}

//用户登录的
	@Override
	public User dogin(User user) {
		if(user==null) {
			throw new CMSException("用户名不可以为空!");
		}
		if(user.getUsername()==null ||user.getPassword()==null) {
			throw new CMSException("用户名或密码不能为空!");
		}
		User us = dao.selectByUsername(user.getUsername());
		if(us==null) {
			throw new CMSException("用户名不存在!");
		}else if(!us.getPassword().equals(Md5Util.md5Encoding(user.getPassword()))){
			throw new CMSException("密码不正确!");
		}
		
		return us;
	}


}
