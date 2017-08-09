package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.RegistDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 注册相关DAO
 * @author ixenos
 *
 */
public class RegistDaoImpl implements RegistDao{
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(RegistDaoImpl.class);
	
	/**
	 * 判断是否已存在用户
	 * 
	 * @param name
	 * @return boolean 是否已存在用户
	 * 
	 */
	public boolean restValidForName(String userName){
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USER WHERE USER_NAME =?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, userName);
			rs = preStmt.executeQuery();
			while(rs.next()){
				 return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs, preStmt, conn);
		}
		return false;//没有这条记录，用户名不存在
	}
	
	/**
	 * 插入用户
	 * 
	 * 不使用其插入失败来判断用户是否存在，因为这里变量太多
	 * 
	 * @param user 要插入的新用户
	 */
	public boolean insertUser(User user){
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setLong(1, 0);//自增主键，设置0将自动自增
			preStmt.setString(2, user.getUserName());
			preStmt.setString(3, user.getUserPassword());
			preStmt.setString(4, user.getUserEmail());
			preStmt.setLong(5, 0);//用户权限
			preStmt.setLong(6, 0);//订阅标志
			preStmt.setString(7, "");//头像链接
			preStmt.setInt(8, 0);//用户新创建时，还没有创建歌单，此时歌单id为0
			preStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("insertUser失败");
		}finally{
			JdbcUtil.close(null, preStmt, conn);
		}
		return false;//插入失败
	}
	
}
