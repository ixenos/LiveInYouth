package com.ixenos.lvy.dao;

/**
 * ALBUM表的DAO
 * 
 * @author ixenos
 *
 */
public interface AlbumDao {
	/**
	 * 通过songId从album表中取专辑图片链接
	 * 
	 * @param albumId album表id
	 * @return albumImgSrc 专辑图片链接
	 */
	String getAlbumImgSrcById(int albumId);
}
