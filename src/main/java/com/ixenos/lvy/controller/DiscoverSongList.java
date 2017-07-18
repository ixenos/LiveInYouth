package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ixenos.lvy.bean.SongListData;
import com.ixenos.lvy.service.DiscoverSongListService;
import com.ixenos.lvy.service.impl.DiscoverSongListServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 发现歌单模块控制器
 * 
 * @author ixenos
 */
@WebServlet("/DiscoverSongList")
public class DiscoverSongList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/*
	 * discoverSonglist服务
	 */
	private DiscoverSongListService dslService;
	/*
	 * 服务初始化
	 */
	{
		dslService = new DiscoverSongListServiceImpl();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscoverSongList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = getServletContext(); // ServletContext实例化
		/*
		 * 类型判断，处理后返回响应类型需要的对象数据
		 */
		String type = request.getParameter("type");// 前端参数获取
		Object serData = null;// 服务返回的数据
		String realPath = ctx.getRealPath("/").replace("\\", "\\\\");//获取项目绝对路径（专属于eclipse）
		String filePath = null;
		
		if ("getList".equals(type)) {
			
			filePath = realPath + "WEB-INF\\json\\discoverSongList\\songListData.json";
			SongListData songListData = (SongListData) LvyJsonUtil.jsonToObj(filePath, SongListData.class);//九宫格模块数据（源文件、未经更新判断）
			serData = (SongListData) dslService.songListSer(songListData, filePath); // 经过更新判断后的九宫格模块数据
			if(serData == null){
				System.out.println("数据错误或更新失败");//TODO
				return;
			}
			
		} else {
			System.out.println("非法参数");// TODO
			return;
		}
		LvyJsonUtil.objToJsonResponse(serData, response);// 设置返回的MIME，并把bean转成JSON，输出到响应报文中
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
