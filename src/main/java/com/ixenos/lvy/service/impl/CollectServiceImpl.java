package com.ixenos.lvy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.CollectDao;
import com.ixenos.lvy.dao.impl.CollectDaoImpl;
import com.ixenos.lvy.service.CollectService;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 收藏服务
 * 
 * @author ixenos
 *
 */
public class CollectServiceImpl implements CollectService {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(CollectServiceImpl.class); 
	
	/*
	 * collectDao
	 */
	private CollectDao collectDao;
	{
		collectDao = new CollectDaoImpl();
	}

	/**
	 * 收藏歌单
	 * 
	 * 
	 * 
	 * @param songListId
	 *            被收藏的歌单ID
	 * @param userName
	 *            用户信息，注意可能是邮箱
	 * @return 收藏状态码：0 user为null; 1 已收藏； 2收藏成功; 3插入失败
	 */
	@Override
	public int collectSongList(int songListId, User user) {
		// 用户非空判断
		if (user == null) {
			return 0;
		}
		// 查询是否已有songListId
		if (collectDao.ifSongListCollect(songListId, user)) {
			return 1;
		}
		// 插入songListId
		if (collectDao.collectSongList(songListId, user)) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * list收藏服务返回的json状态信息
	 * 
	 * @param songListId
	 *            歌单id
	 * @param user
	 *            当前用户
	 * @return json状态信息
	 */
	@Override
	public String jsonForListColl(int songListId, User user) {
		int collStatus = collectSongList(songListId, user);
		//String jsonInfo = null;
		Map<String, String> map = new HashMap<>();
		if(collStatus == 0){
			logger.warn("用户为空");
			return null;
		}else if (collStatus == 1) {
			map.put("success", "false");
			map.put("type", "hasColl");//已收藏
			logger.info("已收藏");
		} else if (collStatus == 2) {
			map.put("success", "true");
			map.put("type", "coll");//收藏成功
			logger.info("收藏成功");
		} else if (collStatus == 3) {
			map.put("success", "true");
			map.put("type", "fail");//收藏失败
			logger.info("收藏失败");
		}
		return LvyJsonUtil.simpleMapToJson(map);
	}

}
