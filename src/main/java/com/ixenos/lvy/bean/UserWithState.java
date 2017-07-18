package com.ixenos.lvy.bean;

/**
 * 带状态的客户信息
 * 
 * 
 * 
 * @author ixenos
 *
 */
public class UserWithState extends User {
	/*
	 * 用于实现自动登录功能； 接收后端重写给前端的JSESSIONID 不存在数据库表中的字段~
	 * 
	 * 也可以使用一个子类，就叫带状态的子类，来接受前端的数据，这是装饰者模式
	 */
	private String sessionId;
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
	 * 自动登录
	 */
	private boolean autoLogin;

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
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
