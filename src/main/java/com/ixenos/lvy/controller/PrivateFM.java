package com.ixenos.lvy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.service.PrivateFMService;
import com.ixenos.lvy.service.impl.PrivateFMServiceImpl;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * Servlet implementation class PrivateFM
 */
@WebServlet("/PrivateFM")
public class PrivateFM extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/*
	 * PrivateFmService
	 */
	private PrivateFMService fmService;
	
	/*
	 * 服务初始化
	 */
	{
		fmService = new PrivateFMServiceImpl();
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PrivateFM() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String type = request.getParameter("type");
		String songListId = request.getParameter("songListId");
		
		Object serData = null;
		if(type==null){
			System.out.println("前端输入错误");//TODO
			return;
		}else{
			if("otherList".equals(type) && songListId!=null){
				//根据songListId加载fm数据
				//需要判断songListId的存在，并给出相应方案
				serData = fmService.fmSer(Integer.valueOf(songListId));
			}else if("userList".equals(type)){
				//userList 则指定当前用户的信息，当前用户的listId从会话中获取
				User user = (User)session.getAttribute("user");
				serData = fmService.fmSer(user.getSongListId());
			}
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
