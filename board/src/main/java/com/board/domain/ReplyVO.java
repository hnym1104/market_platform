package com.board.domain;

import java.util.Date;

public class ReplyVO {

	private int repNum;
	private int gdsNum;
	private String repWriter;
	private String repPwd;
	private String repContent;
	private Date repDate;
	
	public int getRepNum() {
		return repNum;
	}
	public void setRepNum(int repNum) {
		this.repNum = repNum;
	}
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public String getRepWriter() {
		return repWriter;
	}
	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}
	public String getRepPwd() {
		return repPwd;
	}
	public void setRepPwd(String repPwd) {
		this.repPwd = repPwd;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
}
