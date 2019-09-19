/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.dao.CategoryMapper;
import com.zhangzhi.domain.Category;
import com.zhangzhi.service.CategoryService;

/**
 * @author zhangzhi
 *2019年9月17日
 */
@Service
public class CategoryServiceimp implements CategoryService {

	@Resource
	CategoryMapper dao;

	@Override
	public List<Category> selectsBy(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectsBy(id);
	}
	
	
	
	
}
