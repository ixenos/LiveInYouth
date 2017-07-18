package com.ixenos.lvy.bean;

/**
 * 我的聆青模块数据
 * @author ixenos
 *
 */
public class ServicesData {
	/*
	 * 成功标志
	 */
	private String success;
	/*
	 * 用户对象
	 */
	private User user;
	/*
	 * 歌单对象
	 */
	private SongList songList;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SongList getSongList() {
		return songList;
	}
	public void setSongList(SongList songList) {
		this.songList = songList;
	}
	
}
