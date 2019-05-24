package com.web.entity;

import java.io.Serializable;

public class Manager implements Serializable{
	private String maccount;	//管理员账号
	private String mpwd;	//管理员密码
	
	//getter setter方法
	public String getMaccount() {
		return maccount;
	}
	public void setMaccount(String maccount) {
		this.maccount = maccount;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	@Override
	public String toString() {
		return "Manager [maccount=" + maccount + ", mpwd=" + mpwd + "]";
	}
	
}
