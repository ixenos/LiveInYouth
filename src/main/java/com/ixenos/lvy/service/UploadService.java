package com.ixenos.lvy.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.ixenos.lvy.bean.User;

/**
 * 上传服务
 * @author ixenos
 *
 */
public interface UploadService {
	
	/**
	 * 上传文件
	 * @param request
	 * @param user
	 * @param realPath 物理路径
	 * @param type 上传的文件类型：listCover，avatar
	 * @return relativePath 项目相对路径
	 * @throws Exception
	 */
	boolean uploadFile(HttpServletRequest request, User user, String realPath, String relativePath, String type)
			throws IOException;
	
	/**
	 * 上传歌单封面
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean uploadListCover(HttpServletRequest request, User user) throws IOException;
	
	/**
	 * 上传用户头像
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean uploadAvatar(HttpServletRequest request, User user) throws IOException;
	
	/**
	 * 上传歌曲
	 * @param request
	 * @param user
	 * @return
	 * @throws IOException
	 */
	boolean uploadSong(HttpServletRequest request, User user) throws IOException;
}
