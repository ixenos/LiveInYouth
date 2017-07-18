package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.ServicesData;
import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;

/**
 * 我的聆青模块服务
 * @author ixenos
 *
 */
public interface ServicesService {
	/**
	 * 我的聆青初始化数据
	 * @param user 用户
	 * @return servicesData
	 */
	ServicesData initServicesData(User user);
	
	/**
	 * 修改歌单基本数据
	 * @param user
	 * @param modSongList 前端修改的数据
	 * @return Object[]
	 */
	Object[] modBaseList(User user, SongList modSongList);
	
	/**
	 * 修改用户基本数据
	 * @param user
	 * @return boolean
	 */
	boolean modBaseUser(User user);
	
	/**
	 * 修改歌曲基本数据
	 * @param song
	 * @return
	 */
	boolean modBaseSong(User user, Song song);
}
