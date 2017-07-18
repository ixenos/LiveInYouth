package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.dao.SongListMapDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * SongListMap表的dao
 * 
 * @author ixenos
 *
 */
public class SongListMapDaoImpl implements SongListMapDao {
	/**
	 * 通过歌单id获取歌曲id
	 * 
	 * @param songListId
	 *            歌单id
	 * @return 歌曲id
	 */
	@Override
	public List<Integer> getSongIdByListId(int songListId) {
		// list同步处理
		// List<Integer> list = Collections.synchronizedList(new
		// LinkedList<Integer>());
		List<Integer> list = null;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST_MAP WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();

			list = new LinkedList<>();// 延迟初始化
			while (rs.next()) {
				list.add(rs.getInt("SONG_ID"));// 自动装箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return list;
	}

	/**
	 * 通过歌曲id获取歌单id
	 * 
	 * @param songId
	 *            歌曲id
	 * @return 歌单id
	 */
	@Override
	public List<Integer> getListIdBySongId(int songId) {
		// list同步处理
		// List<Integer> list = Collections.synchronizedList(new
		// LinkedList<Integer>());
		List<Integer> list = null;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST_MAP WHERE SONG_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();

			list = new LinkedList<>();// 延迟初始化
			while (rs.next()) {
				list.add(rs.getInt("SONG_LIST_ID"));// 自动装箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return list;
	}

	/**
	 * 通过歌单id获取歌曲名
	 * 
	 * @param songListId
	 *            歌单id
	 * @return 歌曲名
	 */
	@Override
	public List<String> getSongNameByListId(int songListId) {
		// list同步处理
		// List<Integer> list = Collections.synchronizedList(new
		// LinkedList<Integer>());
		List<String> list = null;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST_MAP WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();

			list = new LinkedList<>();// 延迟初始化
			while (rs.next()) {
				list.add(rs.getString("SONG_NAME"));// 自动装箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return list;
	}

	/**
	 * 通过歌曲id获取歌单名
	 * 
	 * @param songId
	 *            歌曲id
	 * @return 歌单名
	 */
	@Override
	public List<String> getListNameBySongId(int songId) {
		// list同步处理
		// List<Integer> list = Collections.synchronizedList(new LinkedList<Integer>());
		List<String> list = null;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST_MAP WHERE SONG_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();

			list = new LinkedList<>();// 延迟初始化
			while (rs.next()) {
				list.add(rs.getString("LIST_NAME"));// 自动装箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return list;
	}
	
	/**
	 * 通过歌单id获取歌单歌曲数量
	 * @param songListId 歌单id
	 * @return 歌单歌曲数量  -1表示错误
	 */
	@Override
	public int getSongsAmountByListId(int songListId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM SONG_LIST_MAP WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs, preStmt, conn);
		}
		return -1;
	}

	/**
	 * 给歌单和歌曲添加映射
	 * src_flag ，为0表示无源
	 * @param song
	 * @param songList
	 * @return
	 */
	@Override
	public boolean addSongAndListMap(Song song, SongList songList) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "INSERT INTO SONG_LIST_MAP VALUES(?,?,?,?,?,?)";
		/*
		SongListMap songListMap = new SongListMap();
		songListMap.setSongListMapId(0);//自增长id，设置0自动分配
		songListMap.setSongListId(songList.getSongListId());
		songListMap.setListName(songList.getListName());
		songListMap.setSongId(song.getSongId());
		songListMap.setSongName(song.getSongName());
		songListMap.setSrcFlag(0);
		*/
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, 0); //自增长id，设置0自动分配
			preStmt.setInt(2, songList.getSongListId());
			preStmt.setString(3, songList.getListName());
			preStmt.setInt(4, song.getSongId());	
			preStmt.setString(5, song.getSongName());
			preStmt.setInt(6, 0);//src_flag ，为0表示无源
			int i = preStmt.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(preStmt, conn);
		}
		return false;
	}

	/**
	 * 获得无源的歌曲
	 * @param songListId
	 * @return
	 */
	@Override
	public List<Integer> getNoneSrcSongId(int songListId) {
		List<Integer> list = null;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST_MAP WHERE SONG_LIST_ID = ? AND SRC_FLAG='0'";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();

			list = new LinkedList<>();// 延迟初始化
			while (rs.next()) {
				list.add(rs.getInt("SONG_ID"));// 自动装箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return list;
	}

	/**
	 * 改变歌曲的源状态
	 * @param songId
	 * @param flag true=有源 1，false=无源2
	 * @return
	 */
	@Override
	public boolean changeNoneSrcFlag(int songId, boolean flag) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		//别忘了WHERE，否则更改所有记录
		String sql = "UPDATE SONG_LIST SET SRC_FLAG=? WHERE SONG_ID=?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			if(flag){
				preStmt.setInt(1, 1);
			}else{
				preStmt.setInt(1, 0);
			}
			preStmt.setInt(2, songId);
			int i = preStmt.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(preStmt, conn);
		}
		
		return false;
	}
	
	
}
