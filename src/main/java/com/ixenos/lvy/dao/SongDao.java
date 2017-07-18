package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.Song;

/**
 * 歌曲表的DAO操作
 * @author ixenos
 *
 */
public interface SongDao {
	/**
	 * 通过song表Id获取song对象
	 * 
	 * @param songId song表id
	 * @return Song 歌曲表对象
	 */
	Song getSongById(int songId);
	
	/**
	 * 通过songId获取song名字
	 * @param songId
	 * @return
	 */
	String getNameById(int songId);
	
	/**
	 * 通过song表Id获取songSrc
	 * 
	 * @param songId
	 * @return
	 */
	String getSrcById(int songId);
	
	/**
	 * 通过songId获取歌曲对应其他表的ID
	 * 
	 * @param songId song表id
	 * @param otherIdName	其他表
	 * @return
	 */
	int getOtherIdById(int songId, String otherIdName);
	
	/**
	 * 通过song bean来更新song
	 * @param song
	 * @return
	 */
	boolean updateSongByBean(Song song);

	/**
	 * 创建新的歌曲
	 * @return 返回自增长生成的id
	 */
	int createSong();
}
