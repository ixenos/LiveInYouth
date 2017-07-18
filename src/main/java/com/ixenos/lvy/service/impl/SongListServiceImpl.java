package com.ixenos.lvy.service.impl;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.SongDao;
import com.ixenos.lvy.dao.SongListDao;
import com.ixenos.lvy.dao.impl.SongDaoImpl;
import com.ixenos.lvy.dao.impl.SongListDaoImpl;
import com.ixenos.lvy.service.SongListService;

/**
 * songList表服务
 * @author ixenos
 *
 */
public class SongListServiceImpl implements SongListService {
	/*
	 * songlistDao
	 */
	private SongListDao songListDao;
	/*
	 * songDao
	 */
	private SongDao songDao;
	/*
	 * dao初始化
	 */
	{
		songListDao = new SongListDaoImpl();
		songDao = new SongDaoImpl();
	}
	
	/**
	 * 根据songListId获取song[]
	 * @param songListId 歌单表id
	 * @return Song[] 歌曲数组
	 */
	@Override
	public Song[] getSongsBySongListId(int songListId) {
		//获取songid集
		String songIdSet = songListDao.getSongIdSet(songListId);
		String[] songIds = songIdSet.split(",");
		//获取song集
		Song[] songs = new Song[songIds.length];
		for(int i=0; i<songIds.length; i++){
			int songId = Integer.valueOf(songIds[i]).intValue();
			songs[i] = songDao.getSongById(songId);
		}
		return songs;
	}
	
	/**
	 * 根据userId获取songListId
	 * @param userId 用户id
	 * @return songListId
	 */
	@Override
	public int getSongListIdByUserId(int userId) {
		return songListDao.getSongListIdByUserId(userId);
	}
	
	/**
	 * 获取当前用户歌单歌曲song[]
	 * @param user 当前用户
	 * @return 歌曲
	 */
	@Override
	public Song[] getUserSongs(User user) {
		int userId = user.getUserId();
		int songListId = getSongListIdByUserId(userId);
		return getSongsBySongListId(songListId);
	}
	
	/**
	 * 根据songListId获取songlist
	 * @param songListId songlistId
	 * @return songList
	 */
	@Override
	public SongList getSongListBySongListId(int songListId) {
		
		return null;
	}

}
