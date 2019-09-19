package com.zhangzhi.dao;

import com.zhangzhi.domain.Slide;

public interface SlideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);
}