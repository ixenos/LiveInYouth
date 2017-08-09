package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.Comment;
import com.ixenos.lvy.dao.CommentDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 针对comment表的详细dao
 * 
 * @author ixenos
 *
 */
public class CommentDaoImpl implements CommentDao {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(CommentDaoImpl.class);
	
	/**
	 * 点赞
	 */
	public boolean thumpUp(int commentId) {
		String sql = "UPDATE COMMENT SET THUMP_UP_COUNT = THUMP_UP_COUNT + 1 WHERE COMMENT_ID = ?";
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, commentId);
			if (preStmt.executeUpdate() > 0) {
				return true;// 点赞成功
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("更新赞数失败");
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return false;// 点赞失败
	}

	/**
	 * 根据commentID取出comment
	 * 
	 * @param commentId
	 * @return
	 */
	public Comment getCommentByCommentId(int commentId) {
		String sql = "SELECT * FROM COMMENT WHERE COMMENT_ID = ?";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, commentId);
			rs = preStmt.executeQuery();
			// 封装comment
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(commentId);
				comment.setSongId(rs.getInt(2));
				comment.setUserId(rs.getInt(3));
				comment.setUserName(rs.getString(4));
				comment.setCommentType(rs.getInt(5));
				comment.setContent(rs.getString(6));
				comment.setThumpUpCount(rs.getInt(7));
				return comment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}

	/**
	 * 根据songID取出热门评论，可指定数量（降序）
	 * 
	 * @param songId
	 * @param amount
	 *            指定取出的热门评论数量
	 * @return
	 */
	public Comment[] getPopuCommentBySongId(int songId, int amount) {
		String sql = "SELECT * FROM COMMENT WHERE SONG_ID = ? ORDER BY THUMP_UP_COUNT DESC";// 注意降序时空值先显示，要进行判断
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();
			// 封装Comment到Comment[]中
			Comment[] comments = new Comment[amount];
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
						comments[i] = new Comment();// 这一步别忘了
						comments[i].setCommentId(rs.getInt("COMMENT_ID"));
						comments[i].setSongId(songId);
						comments[i].setSongName(rs.getString("SONG_NAME"));
						comments[i].setUserId(rs.getInt("USER_ID"));
						comments[i].setUserName(rs.getString("USER_NAME"));
						comments[i].setCommentType(rs.getInt("COMMENT_TYPE"));
						comments[i].setContent(rs.getString("CONTENT"));
						comments[i].setThumpUpCount(thumpUpCount);
					}
				}
				i++; // 递进，不能在rs.next条件的包裹内，因为如果最后一次next没有了，i就不++了，于是while死循环
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}
	
	
	/**
	 * 获取全表最热门的评论，可指定数量（降序）
	 * 
	 * @param amount 指定取出的热门评论数量
	 * @return Comment数组
	 */
	@Override
	public Comment[] getPopuComment(int amount) {
		String sql = "SELECT * FROM COMMENT ORDER BY THUMP_UP_COUNT DESC";// 注意降序时空值先显示，要进行判断
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 封装Comment到Comment[]中
			Comment[] comments = new Comment[amount];
			/*
			 * 提取n条热门评论
			 */
			int i = 0;
			while (i < amount) {
				if (rs.next()) {
					int thumpUpCount = rs.getInt("THUMP_UP_COUNT");
					if (thumpUpCount == 0) { // 降序排序时，空值在前面
						continue; // 不递进，则多进行一次rs.next
					} else {
						// 封装Comment到Comment[]中
						comments[i] = new Comment();// 这一步一定要
						comments[i].setCommentId(rs.getInt("COMMENT_ID"));
						comments[i].setSongId(rs.getInt("SONG_ID"));
						comments[i].setSongName(rs.getString("SONG_NAME"));
						comments[i].setUserId(rs.getInt("USER_ID"));
						comments[i].setUserName(rs.getString("USER_NAME"));
						comments[i].setCommentType(rs.getInt("COMMENT_TYPE"));
						comments[i].setContent(rs.getString("CONTENT"));
						comments[i].setThumpUpCount(thumpUpCount);
					}
				}
				i++; // 递进，不能在rs.next条件的包裹内，因为如果最后一次next没有了，i就不++了，于是while死循环
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stmt, conn);
		}
		return null;
	}

}
