package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;

/**
 * songList表服务
 * @author ixenos
 *
 */
public interface SongListService {
	/**
	 * 根据songListId获取songlist
	 * @param songListId songlistId
	 * @return songList
	 */
	SongList getSongListBySongListId(int songListId);
	
	/**
	 * 根据songListId获取song[]
	 * @param songListId 歌单表id
	 * @return Song[] 歌曲数组
	 */
	Song[] getSongsBySongListId(int songListId);
	
	/**
	 * 获取当前用户歌单歌曲song[]
	 * @param user 当前用户
	 * @return 歌曲
	 */
	Song[] getUserSongs(User user);
	
	/**
	 * 根据userId获取songListId
	 * @param userId 用户id
	 * @return songListId
	 */
	int getSongListIdByUserId(int userId);
}
