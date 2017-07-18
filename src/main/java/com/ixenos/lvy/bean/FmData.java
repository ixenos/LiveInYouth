package com.ixenos.lvy.bean;

/**
 * 私人电台模块数据
 * @author ixenos
 *
 */
public class FmData {
	/*
	 * 数据获取标志
	 */
	private String success;
	/*
	 * 歌单列表id
	 */
	private String listId;
	/*
	 * 歌单名
	 */
	private String listName;
	/*
	 * 歌单介绍
	 */
	private String listIntro;
	/*
	 * 用户id
	 */
	private String userId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 用户头像
	 */
	private String userAvatar;
	/*
	 * 简要歌曲信息
	 */
	private JplayerData[] jPlayerDatas;
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getListIntro() {
		return listIntro;
	}
	public void setListIntro(String listIntro) {
		this.listIntro = listIntro;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public JplayerData[] getjPlayerDatas() {
		return jPlayerDatas;
	}
	public void setjPlayerDatas(JplayerData[] jPlayerDatas) {
		this.jPlayerDatas = jPlayerDatas;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
