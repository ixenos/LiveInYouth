package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.UserDao;
import com.ixenos.lvy.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 通过用户名更新订阅字段
	 * 
	 * @param userName
	 *            需要更新字段的用户
	 * @param subsFlag
	 *            订阅字段的boolean值，订阅字段的boolean值，false 0 取消订阅； true 1 订阅
	 * @return
	 */
	@Override
	public boolean updateSubsByName(String userName, boolean subsFlag) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "UPDATE USER SET SUBS_FLAG = ? WHERE USER_NAME = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			int subsNum = 0;
			if (subsFlag) {
				subsNum = 1;
			}
			preStmt.setInt(1, subsNum);
			preStmt.setString(2, userName);
			if (preStmt.executeUpdate() != 0) {
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
	 * 通过邮箱更新订阅字段
	 * 
	 * @param userName
	 *            需要更新字段的用户
	 * @param subsFlag
	 *            订阅字段的boolean值，订阅字段的boolean值，false 0 取消订阅； true 1 订阅
	 * @return
	 */
	@Override
	public boolean updateSubsByEmail(String userEmail, boolean subsFlag) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "UPDATE USER SET SUBS_FLAG = ? WHERE USER_EMAIL = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			int subsNum = 0;
			if (subsFlag) {
				subsNum = 1;
			}
			preStmt.setInt(1, subsNum);
			preStmt.setString(2, userEmail);
			if (preStmt.executeUpdate() == 1) {
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
	 * 通过用户名查询订阅字段
	 * 
	 * @param userName
	 *            需要查询的用户
	 * @return 返回查询字段的值 -1表示取不到当前用户，0表示当前用户未订阅，1表示已订阅
	 */
	@Override
	public int getSubsFlagByName(String userName) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUBS_FLAG FROM USER WHERE USER_NAME = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, userName);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("SUBS_FLAG");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return -1;
	}

	/**
	 * 通过邮箱查询订阅字段
	 * 
	 * @param userName
	 *            需要查询的用户
	 * @return 返回查询字段的值 -1表示取不到当前用户，0表示当前用户未订阅，1表示已订阅
	 */
	@Override
	public int getSubsFlagByEmail(String userEmail) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUBS_FLAG FROM USER WHERE USER_EMAIL = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, userEmail);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("SUBS_FLAG");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return -1;
	}

	/**
	 * 通过songListId获取user
	 * 
	 * @param songListId
	 *            歌单表id
	 * @return user
	 */
	@Override
	public User getUserBySongListId(int songListId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USER WHERE SONG_LIST_ID = ?";
		User user = null;
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songListId);
			rs = preStmt.executeQuery();
			user = new User();
			while (rs.next()) {
				user.setAvatarSrc(rs.getString("AVATAR_SRC"));
				user.setSongListId(rs.getInt("SONG_LIST_ID"));
				user.setSubsFlag(rs.getInt("SUBS_FLAG"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				user.setUserPower(rs.getString("USER_POWER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 通过user bean来更新user
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserByBean(User user) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "UPDATE USER SET USER_NAME=?, USER_PASSWORD=?, USER_EMAIL=?, USER_POWER=?, SUBS_FLAG=?, AVATAR_SRC=?, SONG_LIST_ID=? WHERE USER_ID=?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, user.getUserName());
			preStmt.setString(2, user.getUserPassword());
			preStmt.setString(3, user.getUserEmail());
			preStmt.setString(4, user.getUserPower());
			preStmt.setInt(5, user.getSubsFlag());
			preStmt.setString(6, user.getAvatarSrc());
			preStmt.setInt(7, user.getSongListId());
			preStmt.setInt(8, user.getUserId());
			int i = preStmt.executeUpdate();
			if (i != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return false;
	}

}
