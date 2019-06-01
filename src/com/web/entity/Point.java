package com.web.entity;

public class Point {
	private int account;	//’Àªßid
	private int mno;	//µÁ”∞±‡∫≈
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
	@Override
	public String toString() {
		return "Point [account=" + account + ", mno=" + mno + "]";
	}
	
}
