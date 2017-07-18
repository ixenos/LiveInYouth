package com.ixenos.lvy.bean;

/**
 * 歌曲歌单映射表
 * @author ixenos
 *
 */
public class SongListMap {
	/*
	 * songListMapId
	 */
	private int songListMapId;
	/*
	 * 歌单id
	 */
	private int songListId;
	/*
	 * 歌单名
	 */
	private String listName;
	/*
	 * 歌曲id
	 */
	private int songId;
	/*
	 * 歌曲名
	 */
	private String songName;
	/*
	 * 是否有源标志
	 */
	private int srcFlag;
	
	public int getSongListMapId() {
		return songListMapId;
	}
	public void setSongListMapId(int songListMapId) {
		this.songListMapId = songListMapId;
	}
	public int getSongListId() {
		return songListId;
	}
	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public int getSrcFlag() {
		return srcFlag;
	}
	public void setSrcFlag(int srcFlag) {
		this.srcFlag = srcFlag;
	}
	
}
