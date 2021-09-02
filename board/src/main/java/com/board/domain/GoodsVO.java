package com.board.domain;

import java.util.Date;

public class GoodsVO {
	/*
	 * create table tbl_goods( gdsNum int not null auto_increment, cateCode
	 * varchar(50) not null, gdsPrice varchar(10) not null default ‘0’, gdsDes text
	 * not null, gdsState varchar(30) not null, gdsImg varchar(200) not null default
	 * ‘no’, gdsDate timestamp not null default now(), viewCnt int default 0,
	 * gdsWriter varchar(50) not null, gdsPwd varchar(50) not null, primary
	 * key(gdsNum) );
	 * 
	 */
	private int gdsNum;
	private String cateCode;
	private String gdsPrice;
	private String gdsDes;
	private String gdsState;
	private String gdsImg;
	private Date gdsDate;
	private int viewCnt;
	private String gdsWriter;
	private String gdsPwd;
	private String gdsThumbImg;

	public int getGdsNum() {
		return gdsNum;
	}

	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(String gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public String getGdsDes() {
		return gdsDes;
	}

	public void setGdsDes(String gdsDes) {
		this.gdsDes = gdsDes;
	}

	public String getGdsState() {
		return gdsState;
	}

	public void setGdsState(String gdsState) {
		this.gdsState = gdsState;
	}

	public Date getGdsDate() {
		return gdsDate;
	}

	public void setGdsDate(Date gdsDate) {
		this.gdsDate = gdsDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getGdsPwd() {
		return gdsPwd;
	}

	public void setGdsPwd(String gdsPwd) {
		this.gdsPwd = gdsPwd;
	}

	public String getGdsImg() {
		return gdsImg;
	}

	public void setGdsImg(String gdsImg) {
		this.gdsImg = gdsImg;
	}

	public String getGdsWriter() {
		return gdsWriter;
	}

	public void setGdsWriter(String gdsWriter) {
		this.gdsWriter = gdsWriter;
	}

	public String getGdsThumbImg() {
		return gdsThumbImg;
	}

	public void setGdsThumbImg(String gdsThumbImg) {
		this.gdsThumbImg = gdsThumbImg;
	}
}
