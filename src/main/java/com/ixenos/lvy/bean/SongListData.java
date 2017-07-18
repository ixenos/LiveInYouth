package com.ixenos.lvy.bean;

/**
 * 九宫格歌单模块数据
 * 
 * @author ixenos
 *
 */
public class SongListData {
	/*
	 * 更新标志
	 */
	private String updated;
	/*
	 * 歌单数据
	 */
	private HitList[] hitLists;

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public HitList[] getHitLists() {
		return hitLists;
	}

	public void setHitLists(HitList[] hitLists) {
		this.hitLists = hitLists;
	}
}
