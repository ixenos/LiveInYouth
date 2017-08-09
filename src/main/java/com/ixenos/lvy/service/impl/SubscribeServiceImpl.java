package com.ixenos.lvy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.UserDao;
import com.ixenos.lvy.dao.impl.UserDaoImpl;
import com.ixenos.lvy.service.SubscribeService;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 订阅服务
 * 
 * @author ixenos
 *
 */
public class SubscribeServiceImpl implements SubscribeService {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(SubscribeServiceImpl.class);
	
	/*
	 * UserDao
	 */
	private UserDao userDao;
	/*
	 * 初始化
	 */
	{
		userDao = new UserDaoImpl();
	}

	/**
	 * 订阅按钮更改字段
	 * 
	 * @param userNameOrEmail
	 *            用户名
	 * @return String 订阅是否成功标识JSON
	 */
	@Override
	public String subscribe(User user) {
		Map<String, String> map = new HashMap<>();
		/*
		 * 判断是否已订阅
		 */
		int hasSubsFlag = userDao.getSubsFlagByName(user.getUserName());
		if (hasSubsFlag == -1) {
			map.put("success", "false");
			map.put("type", "selectDao"); // dao查询失败
		} else if (hasSubsFlag == 1) {
			map.put("success", "false");
			map.put("type", "hasSubs"); // 已订阅
		} else if (hasSubsFlag == 0) {
			/*
			 * 未订阅，进行订阅，判断是否成功
			 */
			boolean subsFlag = userDao.updateSubsByName(user.getUserName(), true);// true进行订阅
			if (subsFlag) {
				map.put("success", "true");
				map.put("type", "subs");// 订阅成功
				logger.info("订阅成功");
			} else {
				map.put("success", "false");
				map.put("type", "updateDao");// dao更新失败
			}
		}
		return LvyJsonUtil.simpleMapToJson(map);
	}

	/**
	 * 是否订阅
	 * 
	 * @param userNameOrEmail
	 *            用户名
	 * @return String 订阅是否成功标识JSON
	 */
	@Override
	public String ifSubscribe(User user) {
		Map<String, String> map = new HashMap<>();
		/*
		 * 判断是否已订阅
		 */
		int hasSubsFlag = userDao.getSubsFlagByName(user.getUserName());
		if (hasSubsFlag == -1) {
			map.put("success", "false");
			map.put("type", "selectDao"); // dao查询失败
		} else if (hasSubsFlag == 1) {
			map.put("success", "false");
			map.put("type", "hasSubs"); // 已订阅
		} else if (hasSubsFlag == 0) {
			map.put("success", "false");
			map.put("type", "notSubs");// 未订阅
		}
		return LvyJsonUtil.simpleMapToJson(map);
	}

	/**
	 * 取消订阅
	 * 
	 * @param User 用户
	 * @return String 取消订阅是否成功标识JSON
	 */
	@Override
	public String cancelSubscribe(User user) {
		Map<String, String> map = new HashMap<>();
		/*
		 * 判断是否已订阅
		 */
		int hasSubsFlag = userDao.getSubsFlagByName(user.getUserName());
		if (hasSubsFlag == -1) {
			map.put("success", "false");
			map.put("type", "selectDao"); // dao查询失败
		} else if (hasSubsFlag == 1) {
			/*
			 * 已订阅，进行退订，判断是否成功
			 */
			boolean cancelFlag = userDao.updateSubsByName(user.getUserName(), false);// true进行订阅
			if (cancelFlag) {
				map.put("success", "true");
				map.put("type", "cancel");// 订阅成功
				logger.info("退订成功");
			} else {
				map.put("success", "false");
				map.put("type", "updateDao");// dao更新失败
			}
			
		} else if (hasSubsFlag == 0) {
			map.put("success", "false");
			map.put("type", "hasCancel"); // 已订阅
		}
		return LvyJsonUtil.simpleMapToJson(map);
	}

}
