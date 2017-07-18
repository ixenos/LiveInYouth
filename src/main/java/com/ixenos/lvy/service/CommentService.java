package com.ixenos.lvy.service;

/**
 * 针对评论的服务
 * 
 * @author ixenos
 *
 */
public interface CommentService {
	/**
	 * 点赞服务
	 * @return 
	 */
	boolean thumpUp(int commentId);
}
