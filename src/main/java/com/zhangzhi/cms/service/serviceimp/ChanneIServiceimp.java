/**
 * 
 */
package com.zhangzhi.cms.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.cms.dao.ChannelMapper;
import com.zhangzhi.cms.domain.Channel;
import com.zhangzhi.cms.service.ChannelService;

/**
 * @author zhangzhi
 *2019年9月17日
 */
@Service
public class ChanneIServiceimp  implements ChannelService{

	@Resource
	ChannelMapper dao;

	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return dao.selects();
	}
}
