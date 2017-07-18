package com.ixenos.lvy.service.impl;

import com.ixenos.lvy.bean.ServicesData;
import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.SongDao;
import com.ixenos.lvy.dao.SongListDao;
import com.ixenos.lvy.dao.SongListMapDao;
import com.ixenos.lvy.dao.UserDao;
import com.ixenos.lvy.dao.impl.SongDaoImpl;
import com.ixenos.lvy.dao.impl.SongListDaoImpl;
import com.ixenos.lvy.dao.impl.SongListMapDaoImpl;
import com.ixenos.lvy.dao.impl.UserDaoImpl;
import com.ixenos.lvy.service.ServicesService;

/**
 * 我的聆青模块服务
 * @author ixenos
 *
 */
public class ServicesServiceImpl implements ServicesService {
	/*
	 * songListDao
	 */
	private SongListDao songListDao;
	/*
	 * userDao
	 */
	private UserDao userDao;
	/*
	 * songDao
	 */
	private SongDao songDao;
	/*
	 * songListMapDao
	 */
	private SongListMapDao songListMapDao;
	
	/*
	 * dao初始化
	 */
	{
		songListDao = new SongListDaoImpl();
		userDao = new UserDaoImpl();
		songDao = new SongDaoImpl();
		songListMapDao = new SongListMapDaoImpl();
	}
	
	/**
	 * 我的聆青初始化数据
	 * @param user 用户
	 * @return servicesData
	 */
	@Override
	public ServicesData initServicesData(User user) {
		ServicesData serData = new ServicesData();
		SongList songList = songListDao.getSongListBySongListId(user.getSongListId());
		if(songList == null){
			serData.setSuccess("false");
			serData.setUser(user);
			System.out.println("订阅标志是：  " + user.getSubsFlag());//TODO
			return serData;
		}
		serData.setSongList(songList);
		serData.setUser(user);
		serData.setSuccess("true");
		return serData;
	}
	
	/**
	 * 修改歌单基本数据
	 * @param user
	 * @param modSongList 前端修改的数据
	 * @return
	 */
	@Override
	public Object[] modBaseList(User user, SongList modSongList) {
		SongList songList = songListDao.getSongListBySongListId(user.getSongListId());
		//新歌单创建
		if(songList == null){
			//新用户，创建新的id
			int newListId = songListDao.createSongList();
			if(newListId != -1){
				System.out.println("创建歌单成功，歌单id是：" + newListId);//TODO
				songList = new SongList();
				songList.setSongListId(newListId);
				songList.setUserId(user.getUserId());
				songList.setUserName(user.getUserName());
				songList.setListType(1);//用户歌单
				
				user.setSongListId(newListId);//顺序
			}else{
				System.out.println("创建歌单失败");//TODO
				return new Object[]{false, user};
			}
		}
		songList.setListName(modSongList.getListName());
		songList.setListIntro(modSongList.getListIntro());
		
		boolean listFlag = songListDao.updateSongListByBean(songList);//id==null时娶不到
		
		boolean userFlag = userDao.updateUserByBean(user);
		
		return new Object[]{listFlag, user, userFlag};
	}

	/**
	 * 修改用户基本数据
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean modBaseUser(User user) {
		return userDao.updateUserByBean(user);
	}

	/**
	 * 修改歌曲基本数据
	 * @param song
	 * @return
	 */
	@Override
	public boolean modBaseSong(User user, Song song) {
		SongList songList = songListDao.getSongListBySongListId(user.getSongListId());
		
		int newSongId = songDao.createSong();//创建新的song记录
		if(newSongId != -1){
			System.out.println("创建歌曲成功，id是：" + newSongId);//TODO
			song.setSongId(newSongId);
			//更新songlistmap
			songListMapDao.addSongAndListMap(song, songList);
		}else{
			System.out.println("创建歌单失败");//TODO
			return false;
		}
		return songDao.updateSongByBean(song);//更新songDao
	}
}
