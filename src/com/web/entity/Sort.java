package com.web.entity;

public class Sort {
	private String tname;
	private int mno;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	@Override
	public String toString() {
		return "Sort [tname=" + tname + ", mno=" + mno + "]";
	}
	
}
