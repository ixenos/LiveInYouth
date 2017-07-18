package com.ixenos.lvy.bean;

/**
 * 热门单曲模块数据
 * 
 * @author ixenos
 *
 */
public class HitSongData {
	/*
	 * 更新标志：true，已更新；false，未更新
	 */
	private String updated;
	/*
	 * 热门单曲子标题
	 */
	private String hitSongTitle;
	/*
	 * 热门单曲展示数据数组
	 */
	private HitSong[] hitSongs;

	public String getHitSongTitle() {
		return hitSongTitle;
	}

	public void setHitSongTitle(String hitSongTitle) {
		this.hitSongTitle = hitSongTitle;
	}

	public HitSong[] getHitSongs() {
		return hitSongs;
	}

	public void setHitSongs(HitSong[] hitSongs) {
		this.hitSongs = hitSongs;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}
}
