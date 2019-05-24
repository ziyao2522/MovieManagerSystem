package com.web.entity;

import java.io.Serializable;
import java.sql.Date;

public class Movie implements Serializable{
	private String cno;	//��Ʒ��˾���
	private String mno;	//��Ӱ���
	private String mname;	//��Ӱ����
	private Date myear;	//�������
	private int mduration;	//ʱ��
	private int star;	//�ղ���
	private int point;	//������
	private int cgrade;	//������
	private int cnt;	//��������
	private int commentcnt;	//Ӱ������
	private String introduction;	//��Ӱ���
	
	//getter��setter����
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
