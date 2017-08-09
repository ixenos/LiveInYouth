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

import com.ixenos.lvy.bean.ServicesData;
import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.service.ServicesService;
import com.ixenos.lvy.service.impl.ServicesServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * Servlet implementation class Services
 */
@WebServlet("/Services")
public class Services extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(Services.class);
	
	/*
	 * ServicesService
	 */
	private ServicesService sersService;
	
	/*
	 * 服务初始化
	 */
	{
		sersService = new ServicesServiceImpl();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Services() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String type = request.getParameter("type");
		User user = (User)session.getAttribute("user");
		Object serData = null;
		
		if(type==null){
			logger.warn("前端输入错误");
			return;
		}else if("cancelLogin".equals(type)){
			//session.invalidate();//销毁session
			session.removeAttribute("user");
			user = (User)session.getAttribute("user");
			session.removeAttribute("userName");
			logger.info("成功退出的的登录");
		}else if("initData".equals(type)){//初始化数据
			//初始化表单数据展示
			ServicesData sersData = sersService.initServicesData(user);
			if(sersData == null){
				logger.warn("sersData为空");
				return;
			}
			serData = sersData;
			
		}else if("modList".equals(type)){//修改歌单
			SongList modSongList = new SongList();
			
			modSongList.setListName(request.getParameter("subListName"));
			modSongList.setListIntro(request.getParameter("subListIntro"));
			
			logger.info("修改的list名：" + modSongList.getListName());
			logger.info("修改的list简介： " + modSongList.getListIntro());
			
			Object[] flags = sersService.modBaseList(user, modSongList);
			
			/*
			 * 更新session中的user信息
			 */
			if((boolean)flags[2]){
				user = (User)flags[1];
				session.setAttribute("user", user);
			}
			
			/*
			 * 返回前端
			 */
			Map<String, Object> map = new HashMap<>();
			if((boolean) flags[0]){
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
			LvyJsonUtil.mapToJsonResponse(map, response);
			return;
		
		}else if("modUser".equals(type)){//修改用户
			
			String subUserName = request.getParameter("subUserName");
			user.setUserName(subUserName);
			
			logger.info("修改的用户名： " + subUserName);
			
			boolean flag = sersService.modBaseUser(user);
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
			
		}else if("modListSong".equals(type)){//增加歌单歌曲		
			//获取表单信息
			String subSongName = request.getParameter("subSongName");
			String subArtistName = request.getParameter("subArtistName");
			String subAlbumName = request.getParameter("subAlbumName");
			
			logger.info("上传的歌曲名： " + subSongName);
			logger.info("上传的歌曲的歌手名" + subArtistName);
			logger.info("上传的歌曲的专辑名" + subAlbumName);
			
			//表单信息实例化
			Song song = new Song();
			song.setSongName(subSongName);
			song.setAlbumName(subAlbumName);
			song.setArtistName(subArtistName);

			boolean flag = sersService.modBaseSong(user, song);
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
		
		LvyJsonUtil.objToJsonResponse(serData, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
