package com.ixenos.lvy.bean;

/**
 * 客户信息
 * 
 * 和数据库表User的字段对应
 * 
 * @author ixenos
 *
 */
public class User {
	/*
	 * 用户id
	 */
	private int userId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 用户密码
	 */
	private String userPassword;
	/*
	 * 用户邮箱
	 */
	private String userEmail;
	/*
	 * 用户权限
	 */
	private String userPower;
	/*
	 * 订阅标志
	 */
	private int subsFlag;
	/*
	 * 头像源
	 */
	private String avatarSrc;
	/*
	 * 歌单id（唯一）
	 */
	private int songListId;

	public int getSubsFlag() {
		return subsFlag;
	}

	public void setSubsFlag(int subsFlag) {
		this.subsFlag = subsFlag;
	}

	public String getAvatarSrc() {
		return avatarSrc;
	}

	public void setAvatarSrc(String avatarSrc) {
		this.avatarSrc = avatarSrc;
	}

	public int getSongListId() {
		return songListId;
	}

	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPower() {
		return userPower;
	}

	public void setUserPower(String userPower) {
		this.userPower = userPower;
	}

	public boolean isAutoLogin() {
		// 空方法
		return false;
	}
}
