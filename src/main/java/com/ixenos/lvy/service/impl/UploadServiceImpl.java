package com.ixenos.lvy.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.bean.SongList;
import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.SongDao;
import com.ixenos.lvy.dao.SongListDao;
import com.ixenos.lvy.dao.SongListMapDao;
import com.ixenos.lvy.dao.UserDao;
import com.ixenos.lvy.dao.impl.SongDaoImpl;
import com.ixenos.lvy.dao.impl.SongListDaoImpl;
import com.ixenos.lvy.dao.impl.SongListMapDaoImpl;
import com.ixenos.lvy.dao.impl.UserDaoImpl;
import com.ixenos.lvy.service.UploadService;

public class UploadServiceImpl implements UploadService {
	/*
	 * userDao
	 */
	private UserDao userDao;
	/*
	 * songListDao
	 */
	private SongListDao songListDao;
	/*
	 * songDao
	 */
	private SongDao songDao;
	/*
	 * songListMapDao
	 */
	private SongListMapDao songListMapDao;	
	
	/*
	 * 服务初始化
	 */
	{
		userDao = new UserDaoImpl();
		songListDao = new SongListDaoImpl();
		songDao = new SongDaoImpl();
		songListMapDao = new SongListMapDaoImpl();
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param user
	 * @param realPath 物理路径
	 * @param type 上传的文件类型：listCover，avatar
	 * @return relativePath 项目相对路径
	 * @throws Exception
	 */
	@Override
	public boolean uploadFile(HttpServletRequest request, User user, String realPath, String relativePath, String type) throws IOException{
		/*
		 * 上传
		 */
		request.setCharacterEncoding("utf-8");
		RequestContext requestContext = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(requestContext)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置缓存目录
			factory.setRepository(new File("c:/tmp/"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setHeaderEncoding("utf-8");
			upload.setSizeMax(15000000);//15MB文件大小限制
			List<FileItem> items = new ArrayList<>();
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				System.out.println("文件上传发生错误" + e1.getMessage());
			}

			Iterator<FileItem> it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				//普通表单内容
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   "
							+ new String(fileItem.getString().getBytes("iso8859-1"), "gbk"));//TODO
				} 
				//文件表单内容
				else {
					System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   "
							+ fileItem.isInMemory() + "    " + fileItem.getContentType() + "   " + fileItem.getSize());//TODO

					if (fileItem.getName() != null && fileItem.getSize() != 0) {
						File fullFile = new File(fileItem.getName());
						
						Random random = new Random();
						int ranInt = random.nextInt(100000000);
						String fileName = ranInt + fullFile.getName();
						String realFilePath = realPath + fileName;
						File newFile = new File(realFilePath);
						try {
							/*
							 * 保存到对应文件
							 */
							fileItem.write(newFile);
							
							/*
							 * 将路径传给数据库，这时候需要对应当前songListId，新用户需要在创建歌单后进行
							 */
							String relativeFilePath = relativePath + fileName;
							
							if("listCover".equals(type)){
								SongList songList = songListDao.getSongListBySongListId(user.getSongListId());
								songList.setSongListImgSrc(relativeFilePath);
								songListDao.updateSongListByBean(songList);
								
								System.out.println("songList保存成功");//TODO
								
							}else if("avatar".equals(type)){
								user.setAvatarSrc(relativeFilePath);
								userDao.updateUserByBean(user);
								
								System.out.println("user保存成功");//TODO
								
							}else if("audio".equals(type)){
								//从sonlistMap获取无源歌曲（现在暴力一点，只认为只有一首新增的是无源）
								List<Integer> list = songListMapDao.getNoneSrcSongId(user.getSongListId());
								if(list == null){
									System.out.println("list是空的");//TODO
									return false;
								}
								int songId = list.get(0);
								Song song = songDao.getSongById(songId);//上传时首
								
								System.out.println("what the fucck?--->" + relativeFilePath);
								
								song.setSongSrc(relativeFilePath);
								songDao.updateSongByBean(song);
								
								//song有src了，在map表更改src_flag
								boolean flag = songListMapDao.changeNoneSrcFlag(songId, true);
								if(flag){
									System.out.println("song保存成功，但map表没有更改src_flag");//TODO
									return false;
								}
								System.out.println("song保存成功");//TODO
							}else{
								return false;
							}
							//while循环体内
							return true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("文件没有选择 或 文件内容为空");//TODO
					}
				}
			}
		}
		return false;
	}

	/**
	 * 上传歌单封面
	 * @param request
	 * @param user
	 * @param realPath 物理路径
	 * @param relativePath 项目相对路径
	 * @throws IOException
	 */
	@Override
	public boolean uploadListCover(HttpServletRequest request, User user) throws IOException {
		String realPath = "E:\\MARSworkspace\\LiveInYouth\\WebContent\\images\\discoverSongList\\songList\\"; 
		String relativePath = "/LiveInYouth/images/discoverSongList/songList/";
		return uploadFile(request, user, realPath, relativePath, "listCover");
	}
	
	/**
	 * 上传用户头像
	 * @param request
	 * @param user
	 * @param realPath 物理路径
	 * @param relativePath 项目相对路径
	 * @throws IOException
	 */
	@Override
	public boolean uploadAvatar(HttpServletRequest request, User user) throws IOException {
		String realPath = "E:\\MARSworkspace\\LiveInYouth\\WebContent\\images\\avatar\\"; 
		String relativePath = "/LiveInYouth/images/avatar/";
		return uploadFile(request, user, realPath, relativePath, "avatar");
	}

	/**
	 * 上传歌曲
	 * @param request
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean uploadSong(HttpServletRequest request, User user) throws IOException {
		String realPath = "E:\\MARSworkspace\\LiveInYouth\\WebContent\\audio\\mp3\\"; 
		String relativePath = "/LiveInYouth/audio/mp3/";
		return uploadFile(request, user, realPath, relativePath, "audio");
	}
	
	
}
