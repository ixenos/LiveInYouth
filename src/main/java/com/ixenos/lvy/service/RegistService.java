package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.User;

/**
 * 注册服务
 * 
 * @author ixenos
 *
 */
public interface RegistService {
	/**
	 * 返回注册成败信息
	 * 
	 * @param user
	 * @return true：注册成功；false：注册失败
	 * 			successInsert:插入成功;
	 * 			notInsert:插入失败;
	 * 			userExist:账号已存在;
	 * 			wrongFormat:格式错误
	 */
	String jsonForRegist(User user);
}
