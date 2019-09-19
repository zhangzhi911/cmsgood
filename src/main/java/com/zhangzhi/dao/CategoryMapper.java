package com.zhangzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangzhi.domain.Category;

/*
 * 分类
 */
public interface CategoryMapper {
	List<Category> selectsBy(@Param("id") Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}