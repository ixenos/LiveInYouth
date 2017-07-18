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
import com.ixenos.lvy.service.LoginService;
import com.ixenos.lvy.service.impl.LoginServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 登录模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 登录服务
	 */
	private LoginService lgService;
	/*
	 * 服务初始化
	 */
	{
		lgService = new LoginServiceImpl();
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		 * 会自动判断HTTP请求报文的Cookie字段的SessionId，如果不存在匹配的，就创建新的会话
		 * 
		 * 所以要重新登录的时候，按照我这个登录方案（判断isNew来进行自动登录的设置），
		 * 
		 * 必须在前端清除sessionID的cookie， 或者在后端手动session.invalidate再getSession，
		 * 为减轻服务器压力，我选择在前端清除sessionId
		 */
		HttpSession session = request.getSession();
		String loginInfoJson = null;// 给前端的JSON

		User user = (UserWithState) LvyJsonUtil.jsonToObj(request, UserWithState.class);// 前端JSON转Bean
		System.out.println("前端输入：用户名： " + user.getUserName() + "； 用户密码：" + user.getUserPassword() + "； 进行自动登录设置："
				+ user.isAutoLogin());// TODO 用log替代
		
		loginInfoJson = lgService.jsonForLogin(user, session, response);// 返回登录的JSON信息
		if(loginInfoJson == null){
			System.out.println("数据错误或更新失败");//TODO
			return;
		}
		LvyJsonUtil.jsonToResponse(loginInfoJson, response);// 输出JSON响应
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
