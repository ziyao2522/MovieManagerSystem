package com.web.entity;

import java.io.Serializable;
import java.sql.Date;

public class Movie implements Serializable{
	private String cno;	//出品公司编号
	private String mno;	//电影编号
	private String mname;	//电影名称
	private Date myear;	//发行年份
	private int mduration;	//时长
	private int star;	//收藏数
	private int point;	//点赞数
	private int cgrade;	//总评分
	private int cnt;	//评分人数
	private int commentcnt;	//影评数量
	private String introduction;	//电影简介
	
	//getter、setter方法
	public String getCno() {
		return cno;
	}
	public String getMno() {
		return mno;
	}
	public String getMname() {
		return mname;
	}
	public Date getMyear() {
		return myear;
	}
	public int getMduration() {
		return mduration;
	}
	public int getStar() {
		return star;
	}
	public int getPoint() {
		return point;
	}
	public int getCgrade() {
		return cgrade;
	}
	public int getCnt() {
		return cnt;
	}
	public int getCommentcnt() {
		return commentcnt;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setMyear(Date myear) {
		this.myear = myear;
	}
	public void setMduration(int mduration) {
		this.mduration = mduration;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setCgrade(int cgrade) {
		this.cgrade = cgrade;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "Movie [cno=" + cno + ", mno=" + mno + ", mname=" + mname + ", myear=" + myear + ", mduration="
				+ mduration + ", star=" + star + ", point=" + point + ", cgrade=" + cgrade + ", cnt=" + cnt
				+ ", commentcnt=" + commentcnt + ", introduction=" + introduction + "]";
	}
}
