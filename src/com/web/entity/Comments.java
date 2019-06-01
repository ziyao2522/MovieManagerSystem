package com.web.entity;

import java.io.Serializable;

public class Comments implements Serializable{
	private int account;	//账户id
	private int mno;	//电影编号
	private int cgrade;	//电影评分
	private String ccomment;	//电影评论
	
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCgrade() {
		return cgrade;
	}
	public void setCgrade(int cgrade) {
		this.cgrade = cgrade;
	}
	public String getCcomment() {
		return ccomment;
	}
	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}
	@Override
	public String toString() {
		return "Comments [account=" + account + ", mno=" + mno + ", cgrade=" + cgrade + ", ccomment=" + ccomment + "]";
	}
	
}
