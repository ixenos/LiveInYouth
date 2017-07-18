package com.ixenos.lvy.bean;

/**
 * 歌单类，对应songlist表
 * 
 * 本表是个单曲歌单表 多个记录才汇成一个歌单的内容 以歌单名为准
 * 
 * @author ixenos
 *
 */
public class SongList {
	/*
	 * 本表ID
	 */
	private int songListId;
	/*
	 * 用户ID
	 */
	private int userId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 歌单名
	 */
	private String listName;
	/*
	 * 歌单类型
	 */
	private int listType;
	/*
	 * 歌单赞数
	 */
	private int thumpUpCount;
	/*
	 * 歌曲id集合，以逗号隔开
	 */
	private String songIdSet;
	/*
	 * 歌单封面图链接
	 */
	private String songListImgSrc;
	/*
	 * 歌单介绍
	 */
	private String listIntro;

	public int getSongListId() {
		return songListId;
	}

	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}

	public int getListType() {
		return listType;
	}

	public void setListType(int listType) {
		this.listType = listType;
	}

	public String getSongIdSet() {
		return songIdSet;
	}

	public void setSongIdSet(String songIdSet) {
		this.songIdSet = songIdSet;
	}

	public String getSongListImgSrc() {
		return songListImgSrc;
	}

	public void setSongListImgSrc(String songListImgSrc) {
		this.songListImgSrc = songListImgSrc;
	}

	public String getListIntro() {
		return listIntro;
	}

	public void setListIntro(String listIntro) {
		this.listIntro = listIntro;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public int getThumpUpCount() {
		return thumpUpCount;
	}

	public void setThumpUpCount(int thumpUpCount) {
		this.thumpUpCount = thumpUpCount;
	}

}
