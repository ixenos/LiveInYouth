package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ixenos.lvy.service.CommentService;
import com.ixenos.lvy.service.impl.CommentServiceImpl;

/**
 * 评论模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(Comment.class); 
	
	/*
	 * comment服务
	 */
	CommentService cmtService;
	/*
	 * 服务初始化
	 */
	{
		cmtService = new CommentServiceImpl();
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Comment() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commentId = request.getParameter("commentId");
		logger.info("get commentId: " + commentId);
		String thumpUp = request.getParameter("thumpUp");
		logger.info("get thumpUp: " + thumpUp);
		
		if ("1".equals(thumpUp)) {
			logger.info("开始点赞");
			if (cmtService.thumpUp(Integer.valueOf(commentId))) {
				logger.info("点赞成功");
			} else {
				logger.error("点赞失败");
			}
			logger.info("结束点赞");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
