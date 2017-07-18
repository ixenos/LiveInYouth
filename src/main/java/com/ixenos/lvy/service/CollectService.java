package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.User;

/**
 * 收藏服务
 * @author ixenos
 *
 */
public interface CollectService {
	/**
	 * 收藏歌单
	 * 
	 * @param songListId 被收藏的歌单ID
	 * @param User 用户信息
	 * @return 收藏状态码：0 user为null; 1 已收藏； 2收藏成功 ;3收藏（插入）失败
	 */
	int collectSongList(int songListId, User user);

	/**
	 * list收藏服务返回的json状态信息
	 * 
	 * @param songListId 歌单id
	 * @param user 当前用户
	 * @return json状态信息
	 */
	String jsonForListColl(int songListId, User user);
}
