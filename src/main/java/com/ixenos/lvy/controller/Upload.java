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
import com.ixenos.lvy.service.UploadService;
import com.ixenos.lvy.service.impl.UploadServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(Upload.class);
	
	/*
	 * uploadService
	 */
	private UploadService uploadService;
	
	/*
	 * 服务初始化
	 */
	{
		uploadService = new UploadServiceImpl();
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		Object userName = session.getAttribute("userName");
		if (userName == null) {
			logger.info("未登录，禁止使用上传服务");
			return;
		}
		
		String type = request.getParameter("type");
		logger.info("upload时，type是： " + type);
		
		if("listCover".equals(type)){
			/*
			 * 上传歌单封面服务
			 */
			boolean flag = uploadService.uploadListCover(request, user);
			/*
			 * 返回前端
			 */
			Map<String, Object> map = new HashMap<>();
			if(flag){
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
			LvyJsonUtil.mapToJsonResponse(map, response);
			return;
			
		}else if("avatar".equals(type)){
			/*
			 * 上传用户头像发服务
			 */
			boolean flag = uploadService.uploadAvatar(request, user);
			/*
			 * 返回前端
			 */
			Map<String, Object> map = new HashMap<>();
			if(flag){
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
			LvyJsonUtil.mapToJsonResponse(map, response);
			return;
			
		}else if("song".equals(type)){
			
			/*
			 * 上传歌曲服务
			 */
			boolean flag = uploadService.uploadSong(request, user);
			/*
			 * 返回前端
			 */
			Map<String, Object> map = new HashMap<>();
			if(flag){
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
			LvyJsonUtil.mapToJsonResponse(map, response);
			return;
			
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
