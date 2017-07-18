package com.ixenos.lvy.service.impl;


import com.ixenos.lvy.dao.CommentDao;
import com.ixenos.lvy.dao.impl.CommentDaoImpl;
import com.ixenos.lvy.service.CommentService;

/**
 * 评论服务工具类
 * 
 * @author ixenos
 *
 */
public class CommentServiceImpl implements CommentService {
	CommentDao comDao = new CommentDaoImpl();
	
	/**
	 * 点赞服务
	 */
	@Override
	public boolean thumpUp(int commentId) {
		return comDao.thumpUp(commentId);
	}
	
}
