package com.web.entity;

import java.io.Serializable;

public class Company implements Serializable {
	private int cno;	//出品公司编号
	private String cname;	//出品公司名
	private String ccity;	//出品公司所在城市
	
	//getter setter方法
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCcity() {
		return ccity;
	}
	public void setCcity(String ccity) {
		this.ccity = ccity;
	}
	@Override
	public String toString() {
		return "Company [cno=" + cno + ", cname=" + cname + ", ccity=" + ccity + "]";
	}
	
}
