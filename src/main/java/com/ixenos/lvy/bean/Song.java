package com.ixenos.lvy.bean;

/**
 * 歌曲类 对应Song表
 * 
 * @author ixenos
 *
 */
public class Song {
	/*
	 * 本表ID
	 */
	private int songId;
	/*
	 * 歌手ID
	 */
	private int artistId;
	/*
	 * 专辑ID
	 */
	private int albumId;
	/*
	 * 歌名
	 */
	private String songName;
	/*
	 * 歌手名
	 */
	private String artistName;
	/*
	 * 专辑名
	 */
	private String albumName;
	/*
	 * 歌曲源
	 */
	private String songSrc;
	/*
	 * 版权信息
	 */
	private int copyright;
	/*
	 * 独家版权信息
	 */
	private int soleCopyright;
	/*
	 * 多媒体类型
	 */
	private int mediaType;
	/*
	 * 歌曲赞数
	 */
	private int thumpUpCount;
	/*
	 * 点击量
	 */
	private int clickCount;
	/*
	 * 歌单引用量（收藏量）
	 */
	private int songListViaCount;
	/*
	 * 评论数
	 */
	private int commentCount;
	/*
	 * 歌单id集合（一对多，用逗号隔开）
	 */
	private String songListIdSet;

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getSongListViaCount() {
		return songListViaCount;
	}

	public void setSongListViaCount(int songListViaCount) {
		this.songListViaCount = songListViaCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getSongListIdSet() {
		return songListIdSet;
	}

	public void setSongListIdSet(String songListIdSet) {
		this.songListIdSet = songListIdSet;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getSongSrc() {
		return songSrc;
	}

	public void setSongSrc(String songSrc) {
		this.songSrc = songSrc;
	}

	public int getSoleCopyright() {
		return soleCopyright;
	}

	public void setSoleCopyright(int soleCopyright) {
		this.soleCopyright = soleCopyright;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getThumpUpCount() {
		return thumpUpCount;
	}

	public void setThumpUpCount(int thumpUpCount) {
		this.thumpUpCount = thumpUpCount;
	}

	public int getCopyright() {
		return copyright;
	}

	public void setCopyright(int copyright) {
		this.copyright = copyright;
	}

}
