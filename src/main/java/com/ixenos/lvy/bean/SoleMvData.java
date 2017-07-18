package com.ixenos.lvy.bean;

/**
 * 独家MV的ajax请求json对应的bean
 * 
 * @author ixenos
 *
 */
public class SoleMvData {
	/*
	 * ajax成功标识 兼 数据更新标志（对于运营人员来说）
	 * 
	 * ！手动打
	 */
	private String updated;
	/*
	 * mv在歌曲表的id
	 */
	private int songId;
	/*
	 * 独家MV标题
	 * 
	 * ！上下文属性读取（配置文件提供）
	 */
	private String soleMvTitle;
	/*
	 * 音乐（MV）源
	 * 
	 * ！song表
	 */
	private String songSrc;
	/*
	 * banner图片
	 * 
	 * ！上下文属性读取（配置文件提供)
	 */
	private String bannerSrc;
	/*
	 * 评论列表
	 * 
	 * !comment表
	 */
	private SimCmt[] simpleCmts;

	public String getSoleMvTitle() {
		return soleMvTitle;
	}

	public void setSoleMvTitle(String soleMvTitle) {
		this.soleMvTitle = soleMvTitle;
	}

	public String getSongSrc() {
		return songSrc;
	}

	public void setSongSrc(String songSrc) {
		this.songSrc = songSrc;
	}

	public SimCmt[] getSimpleCmts() {
		return simpleCmts;
	}

	public void setSimpleCmts(SimCmt[] simpleCmts) {
		this.simpleCmts = simpleCmts;
	}

	public String getBannerSrc() {
		return bannerSrc;
	}

	public void setBannerSrc(String bannerSrc) {
		this.bannerSrc = bannerSrc;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

}
