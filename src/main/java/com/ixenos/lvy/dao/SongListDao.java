package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.SongList;

/**
 * 歌单表的相关DAO操作
 * @author ixenos
 *
 */
public interface SongListDao {
	/**
	 * 通过songListid获取songlist
	 * @param songListId songlistID
	 * @return songlist 返回null时表示songListId不存在
	 */
	SongList getSongListBySongListId(int songListId);
	
	/**
	 * 获取数据库中最热门的歌单
	 * @param amount 取出的数量
	 * @return 数据库中最热门的若干歌单
	 */
	SongList[] getPopuSongList(int amount);
	
	/**
	 * 根据songListId获取songID集
	 * @param songListId 歌单表id
	 * @return song ID集
	 */
	String getSongIdSet(int songListId);
	
	/**
	 * 根据userId获取songListId
	 * @param userId 用户id
	 * @return songListId
	 */
	int getSongListIdByUserId(int userId);
	
	/**
	 * 使用songList的bean对象进行dao更新
	 * @param songList 
	 * @return
	 */
	boolean updateSongListByBean(SongList songList);
	
	/**
	 * 创建新的歌单
	 * @return 返回自增长生成的id
	 */
	int createSongList();
	
	/**
	 * 删除歌单
	 * @param songListId 歌单id
	 * @return 删除成败 
	 */
	boolean deleteSongList(int songListId);
}
