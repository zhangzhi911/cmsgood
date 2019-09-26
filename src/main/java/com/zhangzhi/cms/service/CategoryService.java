/**
 * 
 */
package com.zhangzhi.cms.service;

import java.util.List;

import com.zhangzhi.cms.domain.Category;

/**
 * @author zhangzhi
 *2019年9月17日
 */
public interface CategoryService {
	List<Category> selectsBy(Integer id);
	//分类
}
