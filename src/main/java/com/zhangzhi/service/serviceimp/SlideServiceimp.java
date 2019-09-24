/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.dao.SlideMapper;
import com.zhangzhi.domain.Slide;
import com.zhangzhi.service.SlideService;

/**
 * @author zhangzhi
 *2019年9月21日
 */
@Service
public class SlideServiceimp  implements SlideService{

	@Resource
	private SlideMapper dao;
	
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return dao.selects();
	}

}
