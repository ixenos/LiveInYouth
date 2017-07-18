package com.ixenos.lvy.bean;

/**
 * 热门单曲具体数据
 * 
 * @author ixenos
 *
 */
public class HitSong {
	/*
	 * songId
	 */
	private int songId;
	/*
	 * 歌名
	 */
	private String songName;
	/*
	 * 热门评论内容
	 */
	private String hitCmt;
	/*
	 * 专辑图片
	 */
	private String albumImgSrc;
	/*
	 * 歌曲源
	 */
	private String songSrc;
	/*
	 * 歌曲点赞数
	 */
	private int thumpCount;
	/*
	 * 歌曲点击量
	 */
	private int clickCount;
	/*
	 * 歌曲收藏量
	 */
	private int viaCount;
	/*
	 * 歌曲评论数
	 */
	private int cmtCount;

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getHitCmt() {
		return hitCmt;
	}

	public void setHitCmt(String hitCmt) {
		this.hitCmt = hitCmt;
	}

	public String getAlbumImgSrc() {
		return albumImgSrc;
	}

	public void setAlbumImgSrc(String albumImgSrc) {
		this.albumImgSrc = albumImgSrc;
	}

	public String getSongSrc() {
		return songSrc;
	}

	public void setSongSrc(String songSrc) {
		this.songSrc = songSrc;
	}

	public int getThumpCount() {
		return thumpCount;
	}

	public void setThumpCount(int thumpCount) {
		this.thumpCount = thumpCount;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getViaCount() {
		return viaCount;
	}

	public void setViaCount(int viaCount) {
		this.viaCount = viaCount;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}
}
