package com.ixenos.lvy.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.Comment;
import com.ixenos.lvy.bean.HitCmtData;
import com.ixenos.lvy.bean.HitSong;
import com.ixenos.lvy.bean.HitSongData;
import com.ixenos.lvy.bean.SimCmt;
import com.ixenos.lvy.bean.SoleMvData;
import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.dao.AlbumDao;
import com.ixenos.lvy.dao.CommentDao;
import com.ixenos.lvy.dao.SongDao;
import com.ixenos.lvy.dao.impl.AlbumDaoImpl;
import com.ixenos.lvy.dao.impl.CommentDaoImpl;
import com.ixenos.lvy.dao.impl.SongDaoImpl;
import com.ixenos.lvy.service.DiscoverMusicService;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 发现音乐页面服务
 * 
 * @author ixenos
 *
 */
public class DiscoverMusicServiceImpl implements DiscoverMusicService {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(DiscoverMusicServiceImpl.class); 
	
	/*
	 * CommentDAO
	 */
	private CommentDao commentDao;
	/*
	 * SongDao
	 */
	private SongDao songDao;
	/*
	 * AlbumDao
	 */
	private AlbumDao albumDao;
	{
		albumDao = new AlbumDaoImpl();
		songDao = new SongDaoImpl();
		commentDao = new CommentDaoImpl();
	}

	/**
	 * 独家MV模块数据 <br>
	 * 1.在配置文件自定义标题和自定义songId和设置update为false，后台监测update为false，则根据songId对数据更新
	 * <br>
	 * 2.更新标题和歌曲的工作应该交给运营来定制，而不是在这里自动化;（根据后台数据，和对应的赞助商去选择） <br>
	 * 3.坚持一个原则：源文件路径是放在数据库中的，配置文件中的数据依赖于数据库，只get不set，只给定一个flag去半自动化地从数据库更新数据
	 * <br>
	 * &nbsp&nbsp&nbsp&nbsp 3.1 从数据库更新数据到配置文件中，程序再从配置文件中读取数据，DD是中间人 <br>
	 * 4.注意：soleMvTitle暂时不存在数据库中，由运营人员通过工具编写到properties中！故此字段默认是最新的，不予以更新。
	 * 
	 * <br>
	 * 5.我靠，这里一个id走天下是挺潇洒的，但是这是实时DAO！每刷新一个就进行DAO操作。 <br>
	 * 6.重构：1.将properties改成JSON结构，这样就能把3条comment也缓存到json文件中，减少DDOS风险
	 * 
	 * 
	 * @param soleMvProp
	 *            存放在soleMvData.properties中的数据
	 * @return SoleMvData solemv数据对象
	 * @throws IOException 
	 */
	@Deprecated
	public SoleMvData soleMvSer1(SoleMvData soleMvData) throws IOException {
		int amount = 3;// 指定需要的热门评论数 

		/*
		 * 关键数据初始化
		 */
		int songId = 0;
		String updated = null;

		/*
		 * 关键数据
		 */
		updated = soleMvData.getUpdated();// 更新标志
		songId = soleMvData.getSongId();// 默认是最新的

		/*
		 * 更新标志
		 */
		if ("true".equals(updated)) {
			return soleMvData;// true-已是最新，直接返回soleMvData；false-未更新，那么进行更新
		} else if ("false".equals(updated)) {
			// false时，进行更新
			soleMvData.setSongSrc(songDao.getSrcById(songId));// 更新歌曲链接

			// comment对象获取 热门评论——赞数最高的三条 dao——按赞数降序，取前三条 根据歌id
			Comment[] comments = commentDao.getPopuCommentBySongId(songId, amount);
			SimCmt[] cmtThumps = new SimCmt[comments.length];// JSON已包含，更新时才操作
			for (int i = 0; i < comments.length; i++) {
				cmtThumps[i] = new SimCmt();// 不要忘了
				cmtThumps[i].setUserName(comments[i].getUserName());
				cmtThumps[i].setContent(comments[i].getContent());
				cmtThumps[i].setCommentId(comments[i].getCommentId()); // 点赞链接
			}
			soleMvData.setSimpleCmts(cmtThumps);

			// 更新成功，修改“已更新”标志
			soleMvData.setUpdated("true"); // 是否已更新，此时updated是"true"

			// 再写回JSON文件中（将soleMvData这个对象保存到json文件中)
			LvyJsonUtil.objToJsonFile(soleMvData, new File("/WEB-INF/hitSongData.json"));

		} else {
			logger.warn("updated填入了非法参数，请在true/false中选择");
		}
		return soleMvData;
	}
	
	/**
	 * 独家MV模块数据 <br>
	 * 1.在配置文件自定义标题和自定义songId和设置update为false，后台监测update为false，则根据songId对数据更新
	 * <br>
	 * 2.更新标题和歌曲的工作应该交给运营来定制，而不是在这里自动化;（根据后台数据，和对应的赞助商去选择） <br>
	 * 3.坚持一个原则：源文件路径是放在数据库中的，配置文件中的数据依赖于数据库，只get不set，只给定一个flag去半自动化地从数据库更新数据
	 * <br>
	 * &nbsp&nbsp&nbsp&nbsp 3.1 从数据库更新数据到配置文件中，程序再从配置文件中读取数据，DD是中间人 <br>
	 * 4.注意：soleMvTitle暂时不存在数据库中，由运营人员通过工具编写到properties中！故此字段默认是最新的，不予以更新。
	 * 
	 * <br>
	 * 5.我靠，这里一个id走天下是挺潇洒的，但是这是实时DAO！每刷新一个就进行DAO操作。 <br>
	 * 6.重构：1.将properties改成JSON结构，这样就能把3条comment也缓存到json文件中，减少DDOS风险
	 * 
	 * 
	 * @param soleMvProp
	 *            存放在soleMvData.properties中的数据
	 * @return SoleMvData solemv数据对象
	 */
	@Override
	public SoleMvData soleMvSer(SoleMvData soleMvData, String filePath){
		// 更新标志，true-已是最新，直接返回hitsongdata；false-未更新，那么进行更新
		String updatedFlag = soleMvData.getUpdated();
		if ("true".equals(updatedFlag)) {
			return soleMvData;
		} else if ("false".equals(updatedFlag)) {
			/*
			 * 把thumpUpUrl放到前端取拼装，这里只取commentId
			 */
			/**
			 * {
    				"updated":"true",//手动或另外的程序
    				"songId":"1",//手动或另外的程序
    				"soleMvTitle":"范晓萱最新单曲《放空》",//手动或另外的程序
    				"songSrc":"http://localhost:8081/LiveInYouth/video/mp4/范晓萱-放空.mp4",//song
    				"bannerSrc":"http://localhost:8081/LiveInYouth/images/soleMv/banner/image_1.jpg",//手动或另外的程序
    				"simpleCmts":[
        				{
            				"userName":"Yuban",//comment
            				"content":"只要她还肯露面，只要她还肯唱歌，只要她开心，她怎样都行。",//comment
            				//"thumpUpUrl":"http://localhost:8081/LiveInYouth/Comment?thumpUp=1&commentId=1"//取commentId就行
            				"commentId":"1"
        				},
        				{
            				"userName":"Dada",
            				"content":"放空 感同身受 有时候觉得融不进大家 觉得好难受 会一瞬间抽离 这是什么病？ 快乐到一半刹住 觉得空洞洞的",
            				//"thumpUpUrl":"http://localhost:8081/LiveInYouth/Comment?thumpUp=1&commentId=2"
        					"commentId":"2"
        				},
        				{
            				"userName":"Allen",
            				"content":"属于范晓萱迷幻的味道，像是喝了杯红酒有些微醺，慢慢的放空自己。",
            				//"thumpUpUrl":"http://localhost:8081/LiveInYouth/Comment?thumpUp=1&commentId=3"
        					"commentId":"3"
        				}
    				]
				}
			 */
			SimCmt[] simpleCmts = soleMvData.getSimpleCmts();
			//获取独家Mv最火的三条评论
			Comment[] hitCmts = commentDao.getPopuCommentBySongId(soleMvData.getSongId(), 3);
			for(int i=0; i<3; i++){
				simpleCmts[i].setCommentId(hitCmts[i].getCommentId());
				simpleCmts[i].setContent(hitCmts[i].getContent());
				simpleCmts[i].setUserName(hitCmts[i].getUserName());
			}
			
			soleMvData.setSimpleCmts(simpleCmts);//更新完，重新设回去
			soleMvData.setUpdated("true");//更改更新标志为已更新

		} else {
			logger.warn("updated填入了非法参数，请在true/false中选择");
		}
		// 再写回JSON文件中（将soleMvData这个对象保存到json文件中)
		LvyJsonUtil.objToJsonFile(soleMvData, new File(filePath));
		logger.info("soleMv 更新成功");
		return soleMvData;
	}
	

	/**
	 * 热门单曲模块数据 <br>
	 * 1.在配置文件自定义标题和自定义songId和设置update为false，后台监测update为false，则根据songId对数据更新
	 * <br>
	 * 2.更新标题和歌曲的工作应该交给运营来定制，而不是在这里自动化;（根据后台数据，和对应的赞助商去选择） <br>
	 * 3.坚持一个原则：源文件路径是放在数据库中的，配置文件中的数据依赖于数据库，只get不set，只给定一个flag去半自动化地从数据库更新数据
	 * <br>
	 * &nbsp&nbsp&nbsp&nbsp 3.1 从数据库更新数据到配置文件中，程序再从配置文件中读取数据，DD是中间人
	 * 
	 * @param request
	 * @param response
	 * @return 经过更新状态的判断的 热门单曲模块数据
	 */
	@Override
	public HitSongData hitSongSer(HitSongData hitSongData, String filePath) {
		// 更新标志，true-已是最新，直接返回hitsongdata；false-未更新，那么进行更新
		String updatedFlag = hitSongData.getUpdated();
		if ("true".equals(updatedFlag)) {
			return hitSongData;
		} else if ("false".equals(updatedFlag)) {
			/*
			 * 0、从数据库中取出最新数据：除了手动调整的updated和hitSongTitle，其余的都要（songId由另外的程序设置）
			 * 1、写入到JSON中； 2、将JSON数据转为对象，
			 *  2.1、写到上下文属性中，
			 * 	2.2、返回（在controller中转成json流）
			 */
			/**
			 * 这个是hitSong对象的相关属性 
			 * { 
			 * 	“songId”:"1",//由运营给出
			 * 	"songName":"Yuban",//由songid查出，song
			 * 	"hitCmt":"只要她还肯露面，只要她还肯唱歌，只要她开心，她怎样都行。",//由songid查出，comment
			 * 	"albumImgSrc":"/LiveInYouth/images/hitSong/work_1.png",//由songid查出，album 
			 * 	"songSrc":"http://localhost:8081/LiveInYouth/audio/mp3/范晓萱-放空.mp3",//由songid查出，song 
			 * 	"thumpCount":"8888",//由songid查出，song
			 * 	"clickCount":"99999",//由songid查出，song
			 * 	"viaCount":"7777",//由songid查出，song
			 * 	"cmtCount":"6666"//由songid查出，song 
			 * }
			 */
			// 从数据库取出数据，转化成对象，取属性
			HitSong[] hitSongs = hitSongData.getHitSongs();//每个hitSong初始只有id是最新的，由别的程序设置
			for (HitSong hitSong : hitSongs) {
				/*
				 * comment表 //根据对应歌曲的id
				 */
				Comment[] comments = commentDao.getPopuCommentBySongId(hitSong.getSongId(), 1);
				String hitCmt = comments[0].getContent();
				hitSong.setHitCmt(hitCmt);
				/*
				 * album表 //根据对应歌曲的id
				 * 
				 * 应该从song表获取commentId
				 * 
				 */
				int albumId = songDao.getOtherIdById(hitSong.getSongId(), "ALBUM_ID");
				if(albumId == 0){
					logger.error("更新失败，albumId不存在");
					return null;
				}
				String albumImgSrc = albumDao.getAlbumImgSrcById(albumId);
				if(albumImgSrc == null){
					logger.error("更新失败，albumImgSrc不存在");
					return null;
				}
				hitSong.setAlbumImgSrc(albumImgSrc);
				
				//song表，根据对应歌曲的id
				Song song = songDao.getSongById(hitSong.getSongId());
				if(song == null){
					logger.error("更新失败，song获取失败");
					return null;
				}
				hitSong.setClickCount(song.getClickCount());
				hitSong.setCmtCount(song.getCommentCount());
				hitSong.setSongName(song.getSongName());
				hitSong.setSongSrc(song.getSongSrc());
				hitSong.setThumpCount(song.getThumpUpCount());
				hitSong.setViaCount(song.getSongListViaCount());
			}
			
			hitSongData.setHitSongs(hitSongs);//更新完，重新设回去
			hitSongData.setUpdated("true");//更改更新标志为已更新
			
		} else {
			logger.warn("updated填入了非法参数，请在true/false中选择");
		}
		//再写回JSON文件中（将soleMvData这个对象保存到json文件中) //TODO 失败   
		LvyJsonUtil.objToJsonFile(hitSongData, new File(filePath));
		
		return hitSongData;
	}
	
	/**
	 * 热门评论模块数据提供
	 * 
	 * @param hitCmtData 服务器上未经更新判断的JSON
	 * @param filePath 服务器上的JSON地址
	 * @return 返回经过更新判断后的数据（由JSON转化的bean）
	 */
	@Override
	public HitCmtData hitCmtSer(HitCmtData hitCmtData, String filePath) {
		String updatedFlag = hitCmtData.getUpdated();
		if ("true".equals(updatedFlag)) {
			return hitCmtData;
		} else if("false".equals(updatedFlag)){
			Comment[] comments = commentDao.getPopuComment(3);//从数据库中获取当前最热门的三条评论
			SimCmt[] simpleCmts = hitCmtData.getSimpleCmts();//提取要发送的热评数据
			for(int i=0; i<3; i++){
				simpleCmts[i].setContent(comments[i].getContent());
				simpleCmts[i].setSongName(comments[i].getSongName());
				simpleCmts[i].setUserName(comments[i].getUserName());
			}
			
			hitCmtData.setSimpleCmts(simpleCmts);//更新完数值，set回去
			hitCmtData.setUpdated("true");
			
		} else {
			System.out.println("updated填入了非法参数，请在true和false中选择");
		}
		//再写回JSON文件中（将soleMvData这个对象保存到json文件中) //TODO 失败   
		LvyJsonUtil.objToJsonFile(hitCmtData, new File(filePath));
		
		return hitCmtData;
	}
}
