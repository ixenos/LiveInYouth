package com.ixenos.lvy.bean;

import java.util.Date;

/**
 * 专辑类 对应album表
 * 
 * @author ixenos
 *
 */
public class Album {
	/*
	 * 本表id
	 */
	private int albumId;
	/*
	 * 歌手表id
	 */
	private int artistId;
	/*
	 * 专辑名
	 */
	private String albumName;
	/*
	 * 歌手名
	 */
	private String artistName;
	/*
	 * 专辑封面源
	 */
	private String albumImgSrc;
	/*
	 * 专辑发行日期
	 */
	private Date releaseDate;
	/*
	 * 专辑的赞数
	 */
	private int thumpUpCount;

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumImgSrc() {
		return albumImgSrc;
	}

	public void setAlbumImgSrc(String albumImgSrc) {
		this.albumImgSrc = albumImgSrc;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getThumpUpCount() {
		return thumpUpCount;
	}

	public void setThumpUpCount(int thumpUpCount) {
		this.thumpUpCount = thumpUpCount;
	}

}
