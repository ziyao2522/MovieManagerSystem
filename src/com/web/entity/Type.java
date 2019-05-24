package com.web.entity;

public class Type {
	private String tname;
	private String tnote;
	private int tlimit;
	public String getTname() {
		return tname;
	}
	public String getTnote() {
		return tnote;
	}
	public int getTlimit() {
		return tlimit;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public void setTnote(String tnote) {
		this.tnote = tnote;
	}
	public void setTlimit(int tlimit) {
		this.tlimit = tlimit;
	}
	@Override
	public String toString() {
		return "Type [tname=" + tname + ", tnote=" + tnote + ", tlimit=" + tlimit + "]";
	}
}
