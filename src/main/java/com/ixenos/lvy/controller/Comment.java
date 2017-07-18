package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		System.out.println("get commentId: " + commentId);// TODO test
		String thumpUp = request.getParameter("thumpUp");
		System.out.println("get thumpUp: " + thumpUp);// TODO test
		
		if ("1".equals(thumpUp)) {
			System.out.println("开始点赞");// TODO test
			if (cmtService.thumpUp(Integer.valueOf(commentId))) {
				System.out.println("点赞成功");// TODO test
			} else {
				System.out.println("点赞失败"); // TODO test
			}
			System.out.println("结束点赞");// TODO test
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
