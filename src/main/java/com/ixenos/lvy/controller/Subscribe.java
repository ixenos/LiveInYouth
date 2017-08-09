package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.service.SubscribeService;
import com.ixenos.lvy.service.impl.SubscribeServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 订阅模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/Subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(Subscribe.class);

	/*
	 * SubscribeService
	 */
	private SubscribeService subsService;

	/*
	 * 服务初始化
	 */
	{
		subsService = new SubscribeServiceImpl();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscribe() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();// 获取当前JSESSIONID对应的会话
		/*
		 * 前后端分离，采用JSON格式通信
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String type = request.getParameter("type");// 前端参数获取
		
		/*
		 * 根据会话中是否存在指定用户会话来进行判断
		 */
		User user = (User) session.getAttribute("user");

		String jsonInfo = null;
		if (user == null) {
			logger.fatal("用户未登录，进入此流程非法；可能是服务器重启了，也可能是前台在试探");
			jsonInfo = "{\"success\":\"false\" , \"type\":\"sessionNull\"}";
		} else {
			if ("subs".equals(type)) {
				jsonInfo = subsService.subscribe(user);
				if (jsonInfo == null) {
					logger.error("订阅失败，后台错误");
					return;
				}
			} else if ("ifSubs".equals(type)) {
				jsonInfo = subsService.ifSubscribe(user);
				if (jsonInfo == null) {
					logger.error("查询是否订阅失败，后台错误");
					return;
				}
			} else if("cancelSubs".equals(type)){
				jsonInfo = subsService.cancelSubscribe(user);
				if(jsonInfo == null){
					logger.error("退订失败，后台错误");
					return;
				}
				
			} else{
				logger.warn("非法参数");
				return;
			}
		}
		LvyJsonUtil.jsonToResponse(jsonInfo, response);
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
