package com.ixenos.lvy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.RegistDao;
import com.ixenos.lvy.dao.impl.RegistDaoImpl;
import com.ixenos.lvy.service.RegistService;
import com.ixenos.lvy.util.FormUtil;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 注册服务
 * 
 * @author ixenos
 *
 */
public class RegistServiceImpl implements RegistService {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(RegistServiceImpl.class);
	
	/*
	 * RegistDao
	 */
	private RegistDao registDao;
	
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
	@Override
	public String jsonForRegist(User user) {
		registDao = new RegistDaoImpl();
		
		Map<String, String> jsonMap = new HashMap<>();
		// 校验格式
		if (FormUtil.validateRegistFormat(user)) {
			// 判断账号存不存在
			if (!registDao.restValidForName(user.getUserName())) {
				// 插入数据库
				if (registDao.insertUser(user)) {
					// 返回注册成功信息 前端提示登录
					jsonMap.put("success", "true");//更改注册成败标志
					jsonMap.put("type", "successInsert");//插入成功
					logger.info("用户："+ user.getUserName() +" 插入成功");
				} else {
					jsonMap.put("success", "false");
					jsonMap.put("type", "notInsert");//插入失败
					logger.error("用户："+ user.getUserName() +" 插入失败");
				}
			} else {
				jsonMap.put("success", "false");
				jsonMap.put("type", "userExist");//账号已存在
				logger.warn("用户："+ user.getUserName() +" 已存在");
			}
		}else{
			jsonMap.put("success", "false");
			jsonMap.put("type", "wrongFormat");//格式错误
			logger.warn("账号、密码或邮箱格式错误");
		}
		return LvyJsonUtil.simpleMapToJson(jsonMap);
	}
}
