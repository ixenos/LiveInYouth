package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.User;

/**
 * 收藏表的dao
 * @author ixenos
 *
 */
public interface CollectDao {
	/**
	 * 收藏歌单
	 * @param songListId 歌单ID
	 * @param user 用户对象
	 * @return 成功标识 true
	 */
	boolean collectSongList(int songListId, User user);
	
	/**
	 * 查询是否歌单已收藏
	 * @param songListId 要查询的歌单ID
	 * @param user 收藏用户
	 * @return 存在标识 true
	 */
	boolean ifSongListCollect(int songListId, User user);
}
