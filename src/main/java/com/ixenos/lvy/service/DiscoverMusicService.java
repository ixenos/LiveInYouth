package com.ixenos.lvy.service;


import com.ixenos.lvy.bean.HitCmtData;
import com.ixenos.lvy.bean.HitSongData;
import com.ixenos.lvy.bean.SoleMvData;

/**
 * 发现音乐页面服务
 * @author ixenos
 *
 */
public interface DiscoverMusicService {
	/**
	 * 独家MV模块数据提供
	 * 
	 * @param serData 服务器上的未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	SoleMvData soleMvSer(SoleMvData serData, String filePath);
	
	/**
	 * 热门单曲模块数据提供
	 * 
	 * @param serData 服务器上的未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	HitSongData hitSongSer(HitSongData hitSongData, String filePath);
	
	/**
	 * 热门评论模块数据提供
	 * 
	 * @param hitCmtData 服务器上未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	HitCmtData hitCmtSer(HitCmtData hitCmtData, String filePath);
}
