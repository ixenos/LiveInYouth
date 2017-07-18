package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.User;

/**
 * 订阅服务
 * @author ixenos
 *
 */
public interface SubscribeService {
	/**
	 * 订阅
	 * 
	 * @param User 用户
	 * @return String 订阅是否成功标识JSON
	 */
	String subscribe(User user);
	
	/**
	 * 取消订阅
	 * 
	 * @param User 用户
	 * @return String 取消订阅是否成功标识JSON
	 */
	String cancelSubscribe(User user);
	
	/**
	 * 是否订阅
	 * 
	 * @param User 用户
	 * @return String 订阅是否成功标识JSON
	 */
	String ifSubscribe(User User);
}
