package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.LoginDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 登录相关DAO操作
 * 
 * @author ixenos
 *
 */
public class LoginDaoImpl implements LoginDao {

	/**
	 * 通过用户名的类别（name或者email）给出对应sql语句
	 * 
	 * @param formatFlag
	 *            1：name是userName 2：name是userEmail
	 * @return
	 */
	public String[] sqlForLogin(int formatFlag) {

		String queryByName = "SELECT * FROM USER WHERE USER_NAME=?";
		String queryByEmail = "SELECT * FROM USER WHERE USER_EMAIL=?";
		String queryByNameAndPassWord = "SELECT * FROM USER WHERE USER_NAME=? AND USER_PASSWORD=?";
		String queryByEmailAndPassWord = "SELECT * FROM USER WHERE USER_EMAIL=? AND USER_PASSWORD=?";
		String nameSql = null;
		String passwordSql = null;

		// 根据输入账号类型，给出相应的sql语句
		if (1 == formatFlag) {
			nameSql = queryByName;
			passwordSql = queryByNameAndPassWord;
		} else if (2 == formatFlag) {
			nameSql = queryByEmail;
			passwordSql = queryByEmailAndPassWord;
		}
		// 数组index 0 是查name， index 1是查password
		return new String[] { nameSql, passwordSql };
	}

	/**
	 * 判断是否已存在用户
	 * 
	 * @param name
	 * @param formatFlag
	 *            1：name是userName 2：name是userEmail
	 * @return boolean 是否已存在用户
	 */
	public boolean validForName(String name, int formatFlag) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			// 0 是nameSQL， 1是loginSQL
			preStmt = conn.prepareStatement(sqlForLogin(formatFlag)[0]);
			preStmt.setString(1, name);
			rs = preStmt.executeQuery();
			// 账号存在
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 登录成功或者失败，都要关闭连接
			JdbcUtil.close(rs, preStmt, conn);
		}
		return false;
	}

	/**
	 * 登录验证 根据输入账号类型(name or email) 用户名和密码
	 * 
	 * @param name
	 * @param password
	 * @param formatFlag
	 *            1:name和value正常 2：name是email
	 * @return 密码是否正确
	 */
	public boolean validForPassword(String name, String password, int formatFlag) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			// 数组index 0 是查name， index 1是查login
			preStmt = conn.prepareStatement(sqlForLogin(formatFlag)[1]);
			preStmt.setString(1, name);
			preStmt.setString(2, password);
			rs = preStmt.executeQuery();
			// 账号存在
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 登录成功或者失败，都要关闭连接
			JdbcUtil.close(rs, preStmt, conn);
		}
		return false;
	}

	/**
	 * 通过userName取user
	 * 
	 * 可用于判断用户是否存在: 这里name只能是name
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		User user = new User();
		String sql = "SELECT * FROM USER WHERE USER_NAME =?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, userName);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserPower(rs.getString("USER_POWER"));
				user.setAvatarSrc(rs.getString("AVATAR_SRC"));
				user.setSubsFlag(rs.getInt("SUBS_FLAG"));
				user.setSongListId(rs.getInt("SONG_LIST_ID"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;// 没有这条记录，用户名不存在
	}

	/**
	 * 通过userEmail取user
	 * 
	 * 可用于判断用户是否存在: 这里name只能是email
	 * 
	 * @param userEmail
	 * @return
	 */
	public User getUserByEmail(String userEmail) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		User user = new User();
		String sql = "SELECT * FROM USER WHERE USER_EMAIL =?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, userEmail);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserPower(rs.getString("USER_POWER"));
				user.setAvatarSrc(rs.getString("AVATAR_SRC"));
				user.setSubsFlag(rs.getInt("SUBS_FLAG"));
				user.setSongListId(rs.getInt("SONG_LIST_ID"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;// 没有这条记录，用户名不存在
	}
}
