package com.ixenos.lvy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.service.CollectService;
import com.ixenos.lvy.service.impl.CollectServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 收藏模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/Collect")
public class Collect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(Collect.class); 
	/*
	 * 收藏服务
	 */
	private CollectService colService;
    /*
     * 服务初始化
     */
	{
		colService = new CollectServiceImpl();
	}
		
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//获取会话
		
		String songListId = request.getParameter("songListId");
		logger.info("get songListId: " + songListId);
		String type = request.getParameter("type");
		logger.info("get type: " + type);
		
		/*
		 * 登录判断
		 */
		User user = (User) session.getAttribute("user"); //获取会话中的user
		if(user == null){
			Map<String, Object> map = new HashMap<>();
			map.put("success", "false");
			map.put("type", "notLogin"); //后端防止未登录 （前端也有防止未登录）
			LvyJsonUtil.mapToJsonResponse(map, response);
			return;
		}
		/*
		 * 服务类型判断
		 */
		String jsonInfo = null;//响应数据
		if ("listColl".equals(type)) {
			jsonInfo = colService.jsonForListColl(Integer.valueOf(songListId), user);
			logger.info("coll json is: " + jsonInfo);
			if(jsonInfo == null){
				logger.warn("用户为null? at Collect");
				return;
			}
		}else{
			logger.error("参数异常 at Collect controller");
			return;
		}
		LvyJsonUtil.jsonToResponse(jsonInfo, response); //成败响应
	}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
