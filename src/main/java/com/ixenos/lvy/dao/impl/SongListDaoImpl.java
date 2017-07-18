package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.dao.SongListDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 歌单表的相关DAO操作
 * 
 * @author ixenos
 *
 */
public class SongListDaoImpl implements SongListDao {
	/**
	 * 获取数据库中最热门的歌单
	 * 
	 * @param amount
	 *            取出的数量
	 * @return 数据库中最热门的若干歌单
	 */
	@Override
	public SongList[] getPopuSongList(int amount) {
		String sql = "SELECT * FROM SONG_LIST ORDER BY THUMP_UP_COUNT DESC";// 注意降序时空值先显示，要进行判断
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			rs = preStmt.executeQuery();
			// 封装Comment到Comment[]中
			SongList[] songLists = new SongList[amount];
			/*
			 * 提取n条热门评论
			 */
			int i = 0;
			while (i < amount) {
				if (rs.next()) {
					int thumpUpCount = rs.getInt("THUMP_UP_COUNT");
					if (thumpUpCount == 0) { // 降序排序，空值在前面
						continue; // 不递进，则多进行一次rs.next
					} else {
						// 封装Comment到Comment[]中
						songLists[i] = new SongList();// 这一步别忘了
						songLists[i].setSongListId(rs.getInt("SONG_LIST_ID"));
						songLists[i].setListName(rs.getString("LIST_NAME"));
						songLists[i].setListType(rs.getInt("LIST_TYPE"));
						songLists[i].setUserId(rs.getInt("USER_ID"));
						songLists[i].setUserName(rs.getString("USER_NAME"));
						songLists[i].setListIntro(rs.getString("LIST_INTRO"));
						songLists[i].setSongListImgSrc(rs.getString("SONG_LIST_IMG_SRC"));
						songLists[i].setThumpUpCount(rs.getInt("THUMP_UP_COUNT"));
						songLists[i].setSongIdSet(rs.getString("SONG_ID_SET"));
					}
				}
				i++; // 递进，不能在rs.next条件的包裹内，因为如果最后一次next没有了，i就不++了，于是while死循环
			}
			return songLists;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}

	/**
	 * 根据songListId获取songID集
	 * 
	 * @param songListId
	 *            歌单表id
	 * @return song ID集
	 */
	@Override
	public String getSongIdSet(int songListId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SONG_ID_SET FROM SONG_LIST WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getString("SONG_ID_SET");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}

	/**
	 * 根据userId获取songListId
	 * 
	 * @param userId
	 *            用户id
	 * @return songListId
	 */
	@Override
	public int getSongListIdByUserId(int userId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SONG_LIST_ID FROM SONG_LIST WHERE USER_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, userId);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("SONG_LIST_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return 0;
	}

	/**
	 * 通过songListid获取songlist
	 * 
	 * @param songListId
	 *            songlistID
	 * @return songlist 返回null时表示songListId不存在
	 */
	@Override
	public SongList getSongListBySongListId(int songListId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SONG_LIST WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();
			if (rs.next()) {
				SongList songList = new SongList();
				songList.setListIntro(rs.getString("LIST_INTRO"));
				songList.setListName(rs.getString("LIST_NAME"));
				songList.setListType(rs.getInt("LIST_TYPE"));
				songList.setSongIdSet(rs.getString("SONG_ID_SET"));
				songList.setSongListId(songListId);
				songList.setSongListImgSrc(rs.getString("SONG_LIST_IMG_SRC"));
				songList.setThumpUpCount(rs.getInt("THUMP_UP_COUNT"));
				songList.setUserId(rs.getInt("USER_ID"));
				songList.setUserName(rs.getString("USER_NAME"));
				return songList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}

	/**
	 * 使用songList的bean对象进行dao更新
	 * @param songList
	 * @return
	 */
	@Override
	public boolean updateSongListByBean(SongList songList) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		//别忘了WHERE，否则更改所有记录
		String sql = "UPDATE SONG_LIST SET LIST_NAME=?,SONG_ID_SET=?,USER_ID=?, USER_NAME=?,LIST_TYPE=?,THUMP_UP_COUNT=?,SONG_LIST_IMG_SRC=?,LIST_INTRO=? WHERE SONG_LIST_ID=?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, songList.getListName());
			preStmt.setString(2, songList.getSongIdSet());
			preStmt.setInt(3, songList.getUserId());
			preStmt.setString(4, songList.getUserName());
			preStmt.setInt(5, songList.getListType());
			preStmt.setInt(6, songList.getThumpUpCount());
			preStmt.setString(7, songList.getSongListImgSrc());
			preStmt.setString(8, songList.getListIntro());
			preStmt.setInt(9, songList.getSongListId());
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
	 * 创建新的歌单
	 * @return 返回自增长生成的id
	 */
	@Override
	public int createSongList() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		PreparedStatement tmpStmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO SONG_LIST (SONG_LIST_ID) VALUES (?)";
		String sql2 = "SELECT SONG_LIST_ID FROM SONG_LIST WHERE LIST_NAME IS NULL";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, 0);//set0时id默认自增
			int i = preStmt.executeUpdate();
			if(i!=0){
				tmpStmt = conn.prepareStatement(sql2);
				rs = tmpStmt.executeQuery();
				if(rs.next()){
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(tmpStmt != null){
				try {
					tmpStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcUtil.close(rs, preStmt, conn);
		}
		return -1;
	}

	/**
	 * 删除歌单
	 * @param songListId 歌单id
	 * @return 删除成败 
	 */
	@Override
	public boolean deleteSongList(int songListId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "DELETE FROM SONG_LIST WHERE SONG_LIST_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
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
