package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.SongListData;

/**
 * 发现歌单页面服务
 * @author ixenos
 *
 */
public interface DiscoverSongListService {
	/**
	 * 九宫格歌单模块数据提供
	 * 
	 * @param songListData 服务器上的未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	SongListData songListSer(SongListData songListData, String filePath);
}
