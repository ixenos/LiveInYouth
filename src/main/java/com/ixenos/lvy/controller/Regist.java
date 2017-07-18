package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.bean.UserWithState;
import com.ixenos.lvy.service.RegistService;
import com.ixenos.lvy.service.impl.RegistServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 注册模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Regist() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 当前会话（不管是临时的（一般短时间就销毁会话）还是持久的（setMaxInactiveInterval设定时间））
		 */
		@SuppressWarnings("unused")
		HttpSession session = request.getSession();

		/*
		 * 前后端分离，采用JSON格式通信
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		/*
		 * 给前端的JSON
		 */
		String registInfoJson = null;

		/*
		 * 前端JSON转Bean
		 */
		User user = (UserWithState) LvyJsonUtil.jsonToObj(request, UserWithState.class);

		/*
		 * 进入服务（校验格式、判断账号存不存在、插入数据库、返回注册成功信息）
		 */
		RegistService registService = new RegistServiceImpl();
		registInfoJson = registService.jsonForRegist(user);
		if(registInfoJson==null){
			System.out.println("取不到注册json信息");//TODO
			return;
		}
		// 输出响应
		LvyJsonUtil.jsonToResponse(registInfoJson, response);
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
