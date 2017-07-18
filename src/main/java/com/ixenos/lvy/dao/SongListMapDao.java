package com.ixenos.lvy.dao;

import java.util.List;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;

/**
 * SongListMap表的dao
 * @author ixenos
 *
 */
public interface SongListMapDao {
	/**
	 * 通过歌单id获取歌曲id
	 * @param songListId 歌单id
	 * @return 歌曲id
	 */
	List<Integer> getSongIdByListId(int songListId);
	/**
	 * 通过歌曲id获取歌单id
	 * @param songId 歌曲id
	 * @return 歌单id
	 */
	List<Integer> getListIdBySongId(int songId);
	/**
	 * 通过歌单id获取歌曲名
	 * @param songListId 歌单id
	 * @return 歌曲名
	 */
	List<String> getSongNameByListId(int songListId);
	/**
	 * 通过歌曲id获取歌单名
	 * @param songId 歌曲id
	 * @return 歌单名
	 */
	List<String> getListNameBySongId(int songId);
	/**
	 * 通过歌单id获取歌单歌曲数量
	 * @param songListId 歌单id
	 * @return 歌单歌曲数量
	 */
	int getSongsAmountByListId(int songListId);
	/**
	 * 给歌单和歌曲添加映射
	 * @param song
	 * @param songList
	 * @return
	 */
	boolean addSongAndListMap(Song song, SongList songList);
	/**
	 * 获得无源的歌曲
	 * @param songListId
	 * @return
	 */
	List<Integer> getNoneSrcSongId(int songListId);
	/**
	 * 改变歌曲的源状态
	 * @param songId
	 * @param flag true=有源 1，false=无源2
	 * @return
	 */
	boolean changeNoneSrcFlag(int songId, boolean flag);
}
