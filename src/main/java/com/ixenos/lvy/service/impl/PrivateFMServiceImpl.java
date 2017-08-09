package com.ixenos.lvy.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.FmData;
import com.ixenos.lvy.bean.JplayerData;
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
import com.ixenos.lvy.service.PrivateFMService;

/**
 * 私人电台模块服务
 * @author ixenos
 *
 */
public class PrivateFMServiceImpl implements PrivateFMService{
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(PrivateFMServiceImpl.class);
	
	/*
	 * songDao
	 */
	private SongDao songDao;
	/*
	 * songlistDao
	 */
	private SongListDao songListDao;
	/*
	 * songListMapDao
	 */
	private SongListMapDao songListMapDao;
	/*
	 * userDao
	 */
	private UserDao userDao;
	/*
	 * 服务处初始化
	 */
	{
		songDao = new SongDaoImpl();
		songListDao = new SongListDaoImpl();
		songListMapDao = new SongListMapDaoImpl();
		userDao = new UserDaoImpl();
	}
	
	/**
	 * 电台数据获取
	 * 
	 * @param songListId 指定listId
	 * @return FmData
	 */
	@Override
	public FmData fmSer(int songListId) {
		FmData fmData = new FmData();
		
		//先校验songListId存不存在，在songList表校验
		Object flag = songListDao.getSongListBySongListId(songListId);
		
		logger.info("songListId是：　" + songListId);
		if(flag == null){
			logger.info("songListId不存在，默认加载id=1的列表");
			songListId = 1;//不存在的id则默认加载id=1的
		}
		
		//歌单信息 //songList表
		SongList songList = songListDao.getSongListBySongListId(songListId);
		fmData.setListIntro(songList.getListIntro());
		fmData.setListId(String.valueOf(songList.getSongListId()));
		fmData.setListName(songList.getListName());
		
		//用户信息 //user表有songListId字段
		User user = userDao.getUserBySongListId(songListId);
		fmData.setUserAvatar(user.getAvatarSrc());
		fmData.setUserId(String.valueOf(user.getUserId()));
		fmData.setUserName(user.getUserName());
		
		//歌曲信息 //song_list_map表 或 song_list的song_id_set取出分解再查询
		List<Integer> songIds = songListMapDao.getSongIdByListId(songListId);
		if(songIds == null){
			return null;
		}
		JplayerData[] jpData = new JplayerData[songIds.size()];
		//遍历填充数组时要记得实例化数组元素
		for(int i=0; i<songIds.size(); i++){
			jpData[i] = new JplayerData(); //为对象数组赋值时不要忘了还要实例化数组元素
			jpData[i].setTitle(songDao.getNameById(songIds.get(i)));
			jpData[i].setMp3(songDao.getSrcById(songIds.get(i)));
		}
		fmData.setjPlayerDatas(jpData);//set到jPlayerDatas这个变量中
		fmData.setSuccess("true");
		return fmData;
	}

}
