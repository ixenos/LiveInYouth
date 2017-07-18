package com.ixenos.lvy.bean;

/**
 * 九宫格歌单模块数据之歌单数据
 * 
 * @author ixenos
 *
 */
public class HitList {
	/*
	 * 本表ID
	 */
	private int songListId;
	/*
	 * 歌单名
	 */
	private String listName;
	/*
	 * 用户ID
	 */
	private int userId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 歌单介绍
	 */
	private String listIntro;
	/*
	 * 歌单封面图链接
	 */
	private String songListImgSrc;

	public int getSongListId() {
		return songListId;
	}

	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
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

	public String getListIntro() {
		return listIntro;
	}

	public void setListIntro(String listIntro) {
		this.listIntro = listIntro;
	}

	public String getSongListImgSrc() {
		return songListImgSrc;
	}

	public void setSongListImgSrc(String songListImgSrc) {
		this.songListImgSrc = songListImgSrc;
	}

}
