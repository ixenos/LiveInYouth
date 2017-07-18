package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.CollectDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 收藏表的dao
 * 
 * @author ixenos
 *
 */
public class CollectDaoImpl implements CollectDao {
	/**
	 * 收藏歌单
	 * 
	 * @param songListId
	 *            歌单ID
	 * @param user
	 *            用户对象
	 * @return 成功标识 true
	 */
	@Override
	public boolean collectSongList(int songListId, User user) {
		Connection conn = null;
		PreparedStatement preStmt = null;

		int userId = user.getUserId();
		String userName = user.getUserName();
		String sql = "INSERT INTO COLLECT values(?,?,?,?,?,?)";

		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, 0);// 本表id自增，设置0即可
			preStmt.setInt(2, 2);// 2表示收藏歌单
			preStmt.setInt(3, 0);// 被收藏的歌曲id，跟本业务无关，默认0
			preStmt.setInt(4, songListId);// 被收藏的歌单id，系本业务
			preStmt.setInt(5, userId);// 收藏用户id
			preStmt.setString(6, userName);// 收藏用户名
			int count = preStmt.executeUpdate();
			if (count != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return false;
	}
	
	/**
	 * 查询是否歌单已收藏
	 * @param songListId 要查询的歌单ID
	 * @param user 收藏用户
	 * @return 存在标识 true
	 */
	@Override
	public boolean ifSongListCollect(int songListId, User user) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		
		int userId = user.getUserId();
		String sql = "SELECT * FROM COLLECT WHERE SONG_LIST_ID = ? AND USER_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);// 歌单id
			preStmt.setInt(2, userId);// 用户id
			rs = preStmt.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return false;
	}

}
