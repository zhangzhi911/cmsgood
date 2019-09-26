/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.cms.dao.SlideMapper;
import com.zhangzhi.cms.domain.Slide;
import com.zhangzhi.cms.service.SlideService;

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
