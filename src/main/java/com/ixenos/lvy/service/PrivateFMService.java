package com.ixenos.lvy.service;

import com.ixenos.lvy.bean.FmData;

/**
 * 私人电台模块服务
 * @author ixenos
 *
 */
public interface PrivateFMService {
	/**
	 * 电台数据获取
	 * 
	 * @param songListId 指定listId
	 * @return FmData
	 */
	FmData fmSer(int songListId);
}
