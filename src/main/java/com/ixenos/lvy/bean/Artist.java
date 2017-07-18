package com.ixenos.lvy.bean;

/**
 * 歌手类 对应artist表
 * 
 * @author ixenos
 *
 */
public class Artist {
	/*
	 * 本表id
	 */
	private int artistId;
	/*
	 * 歌手名
	 */
	private String artistName;
	/*
	 * 歌手国籍
	 */
	private String nation;
	/*
	 * 歌手介绍
	 */
	private String intro;
	/*
	 * 歌手简介
	 */
	private String briefIntro;

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBriefIntro() {
		return briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}

}
