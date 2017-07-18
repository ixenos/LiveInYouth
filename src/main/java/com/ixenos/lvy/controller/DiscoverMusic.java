package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ixenos.lvy.bean.HitCmtData;
import com.ixenos.lvy.bean.HitSongData;
import com.ixenos.lvy.bean.SoleMvData;
import com.ixenos.lvy.service.DiscoverMusicService;
import com.ixenos.lvy.service.impl.DiscoverMusicServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 发现音乐模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/DiscoverMusic")
public class DiscoverMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * DiscoverMusicService
	 */
	DiscoverMusicService dmService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiscoverMusic() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = getServletContext(); // ServletContext实例化
		/*
		 * 类型判断，处理后返回响应类型需要的对象数据
		 */
		dmService = new DiscoverMusicServiceImpl(); // 服务实例化
		String type = request.getParameter("type");// 前端参数获取
		Object serData = null;// 服务返回的数据
		String realPath = ctx.getRealPath("/").replace("\\", "\\\\");//获取项目绝对路径（专属于eclipse）
		String filePath = null;
		
//		URL url = ctx.getResource("/");
//		String urlStr = url.toString().replace("/", "\\\\").replace("file:\\\\", "");
		
		if ("soleMv".equals(type)) {
			
			filePath = realPath + "WEB-INF\\json\\discovermusic\\soleMvData.json";
			SoleMvData soleMvData = (SoleMvData) LvyJsonUtil.jsonToObj(filePath, SoleMvData.class);//独家MV模块数据（源文件、未经更新判断）
			serData = (SoleMvData) dmService.soleMvSer(soleMvData, filePath); // 经过更新判断后的独家MV模块数据
			if(serData == null){
				System.out.println("数据错误或更新失败");//TODO
				return;
			}
			
		} else if ("hitSong".equals(type)) {
			
			filePath = realPath + "WEB-INF\\json\\discovermusic\\hitSongData.json";
			HitSongData hitSongData = (HitSongData) LvyJsonUtil.jsonToObj(filePath, HitSongData.class);//热门单曲模块数据（源文件、未经更新判断）
			serData = (HitSongData) dmService.hitSongSer(hitSongData, filePath); // 经过更新判断后的热门单曲模块数据
			if(serData == null){
				System.out.println("数据错误或更新失败");//TODO
				return;
			}
		
		} else if ("hitCmt".equals(type)) {
			
			filePath = realPath + "WEB-INF\\json\\discovermusic\\hitCmtData.json";
			HitCmtData hitCmtData = (HitCmtData) LvyJsonUtil.jsonToObj(filePath, HitCmtData.class);
			serData = (HitCmtData) dmService.hitCmtSer(hitCmtData, filePath);
			
		} else {
			System.out.println("非法参数");// TODO
			return;
		}
		LvyJsonUtil.objToJsonResponse(serData, response);// 设置返回的MIME，并把bean转成JSON，输出到响应报文中
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
