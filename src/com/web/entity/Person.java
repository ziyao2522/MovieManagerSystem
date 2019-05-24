package com.web.entity;

import java.io.Serializable;
import java.sql.Date;

public class Person implements Serializable{
	private int pno;
	private String pname;
	private Date pbirth;
	public int getPno() {
		return pno;
	}
	public String getPname() {
		return pname;
	}
	public Date getPbirth() {
		return pbirth;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setPbirth(Date pbirth) {
		this.pbirth = pbirth;
	}
	@Override
	public String toString() {
		return "Person [pno=" + pno + ", pname=" + pname + ", pbirth=" + pbirth + "]";
	}
}
