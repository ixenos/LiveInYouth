package com.ixenos.lvy.bean;

/**
 * 热门评论模块数据
 * 
 * @author ixenos
 *
 */
public class HitCmtData {
	/*
	 * 更新标志
	 */
	private String updated;
	/*
	 * 评论体
	 */
	private SimCmt[] simpleCmts;

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public SimCmt[] getSimpleCmts() {
		return simpleCmts;
	}

	public void setSimpleCmts(SimCmt[] simpleCmts) {
		this.simpleCmts = simpleCmts;
	}
}
