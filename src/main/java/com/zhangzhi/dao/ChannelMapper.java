package com.zhangzhi.dao;

import java.util.List;

import com.zhangzhi.domain.Channel;
/*
 * 栏目
 */
public interface ChannelMapper {
    List<Channel> selects();//所有的栏目
	
	int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}