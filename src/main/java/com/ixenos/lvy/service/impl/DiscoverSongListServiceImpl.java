package com.ixenos.lvy.service.impl;

import java.io.File;

import com.ixenos.lvy.bean.HitList;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.SongListData;
import com.ixenos.lvy.dao.SongListDao;
import com.ixenos.lvy.dao.impl.SongListDaoImpl;
import com.ixenos.lvy.service.DiscoverSongListService;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 发现歌单页面服务
 * @author ixenos
 *
 */
public class DiscoverSongListServiceImpl implements DiscoverSongListService {
	/*
	 * SongListDao
	 */
	private SongListDao songListDao;
	/*
	 * dao初始化
	 */
	{
		songListDao = new SongListDaoImpl();
	}
	
	/**
	 * 九宫格歌单模块数据提供
	 * 
	 * @param serData 服务器上的未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	@Override
	public SongListData songListSer(SongListData songListData, String filePath) {
		String updatedFlag = songListData.getUpdated();
		if ("true".equals(updatedFlag)) {//如果无需更新，那么直接取json文件的数据
			return songListData;
		} else if("false".equals(updatedFlag)){
			
			SongList[] songLists = songListDao.getPopuSongList(9);//从数据库中获取当前最热门的九个歌单
			HitList[] hitLists = songListData.getHitLists();//提取要发送的热评数据
			for(int i=0; i<9; i++){
				hitLists[i].setListIntro(songLists[i].getListIntro());
				hitLists[i].setListName(songLists[i].getListName());
				hitLists[i].setSongListId(songLists[i].getSongListId());
				hitLists[i].setSongListImgSrc(songLists[i].getSongListImgSrc());
				hitLists[i].setUserId(songLists[i].getUserId());
				hitLists[i].setUserName(songLists[i].getUserName());
			}
			
			songListData.setHitLists(hitLists);//更新完数值，set回去
			songListData.setUpdated("true");
			
		} else {
			System.out.println("updated填入了非法参数，请在true和false中选择");
		}
		
		//再写回JSON文件中（将soleMvData这个对象保存到json文件中) //TODO 失败   
		LvyJsonUtil.objToJsonFile(songListData, new File(filePath));
		
		return songListData;
	}

}
