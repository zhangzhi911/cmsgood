package com.zhangzhi.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangzhi.cms.domain.User;

public interface UserMapper {
	
	List<User> selects(@Param("name") String name);
//	判断是否存在
	User selectByUsername(String username);
	
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int updalo(User record);    
}