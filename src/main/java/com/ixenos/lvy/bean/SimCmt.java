package com.ixenos.lvy.bean;

/**
 * { "userName" : "啦啦啦", "content" : "只要她还肯露面，只要她还肯唱歌，只要她开心，她怎样都行。",
 * "thumpUpUrl" :
 * "http://localhost:8081/LiveInYouth/Comment?commentId=3&thumpUp=1" }
 * 
 * 评论对象
 * 
 * @author ixenos
 *
 */
public class SimCmt {
	/*
	 * 评论的用户名
	 */
	private String userName;
	/*
	 * 评论的内容
	 */
	private String content;
	/*
	 * 评论的赞的url
	 */
	private int commentId;
	/*
	 * 评论的歌曲名
	 */
	private String songName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

}
