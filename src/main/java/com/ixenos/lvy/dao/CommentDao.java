package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.Comment;

/**
 * 针对comment表的详细dao
 * 
 * @author ixenos
 *
 */
public interface CommentDao {
	/**
	 * 点赞功能；
	 * 
	 * 根据评论ID，更新赞数字段；
	 * 
	 * @param commentId
	 * @return
	 */
	boolean thumpUp(int commentId);
	
	/**
	 * 根据commentID取出comment
	 * @param commentId
	 * @return
	 */
	Comment getCommentByCommentId(int commentId);
	
	/**
	 * 根据songID取出热门评论，可指定数量（降序）
	 * 
	 * @param songId
	 * @param amount 指定取出的热门评论数量
	 * @return Comment数组
	 */
	Comment[] getPopuCommentBySongId(int songId, int amount);
	
	/**
	 * 获取全表最热门的评论，可指定数量（降序）
	 * 
	 * @param amount 指定取出的热门评论数量
	 * @return Comment数组
	 */
	Comment[] getPopuComment(int amount);
}
