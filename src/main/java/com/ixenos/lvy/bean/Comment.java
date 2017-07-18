package com.ixenos.lvy.bean;

/**
 * 评论类，对应Comment表
 * 
 * @author ixenos
 *
 */
public class Comment {
	/*
	 * 本表ID
	 */
	private int commentId;
	/*
	 * 歌曲ID
	 */
	private int songId;
	/*
	 * 歌曲名
	 */
	private String songName;
	/*
	 * 评论的用户ID
	 */
	private int userId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 评论类型
	 */
	private int commentType;
	/*
	 * 评论内容
	 */
	private String content;
	/*
	 * 评论赞数
	 */
	private int thumpUpCount;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCommentType() {
		return commentType;
	}

	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getThumpUpCount() {
		return thumpUpCount;
	}

	public void setThumpUpCount(int thumpUpCount) {
		this.thumpUpCount = thumpUpCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

}
