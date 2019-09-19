/**
 * 
 */
package com.zhangzhi.service.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhi.dao.ChannelMapper;
import com.zhangzhi.domain.Channel;
import com.zhangzhi.service.ChannelService;

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
