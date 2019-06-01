package com.web.entity;

public class Words {
	private int pno;
	private int mno;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	@Override
	public String toString() {
		return "Words [pno=" + pno + ", mno=" + mno + "]";
	}
	
}
