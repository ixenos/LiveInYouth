package com.ixenos.lvy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC简要封装
 * 
 * @author ixenos
 *
 */
public class JdbcUtil {
	/*
	 * 数据库连接相关信息
	 */
	//MySQL在高版本需要指明是否进行SSL连接，用useSSL=true
	private static String url = "jdbc:mysql://localhost:3306/liveinyouth?useSSL=true&useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "10613";

	/**
	 * 打开数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 1.驱动注册程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取连接对象
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭结果集，关闭Statement，关闭连接
	 * 后打开的先关闭
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(stmt, conn);
	}
	
	/**
	 * 关闭Statement，关闭连接
	 * 后打开的先关闭
	 */
	public static void close(Statement stmt, Connection conn){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
