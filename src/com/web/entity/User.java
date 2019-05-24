package com.web.entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.web.dao.DbHelper;

public class User {
	private int account;
	private String uname;
	private String upwd;
	private String email;
	private String phone;
	private Date u_birth;
	public int getAccount() {
		return account;
	}
	public String getUname() {
		return uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public Date getU_birth() {
		return u_birth;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setU_birth(Date u_birth) {
		this.u_birth = u_birth;
	}
	@Override
	public String toString() {
		return "User [account=" + account + ", uname=" + uname + ", upwd=" + upwd + ", email=" + email + ", phone="
				+ phone + ", u_birth=" + u_birth + "]";
	}
	
	public void setPoint(int account, int movieno) {
		//点赞机制
		int pointstate = 0;
		try {
			Connection conn1 = DbHelper.getConn();
			String sql1 = "SELECT * FROM point WHERE account=" 
					+ account + " AND mno=" + movieno;
			PreparedStatement pst1 = conn1.prepareStatement(sql1);
			ResultSet rs1 = pst1.executeQuery();
			if (!rs1.next()) {
				pointstate = 0;
			}
			else {
				pointstate = 1;
			}
			rs1.close();
			pst1.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	//完成判断是否有关于电影id和账号的赞记录
		
		if (pointstate == 1) {
			//movie数据库中点赞数point加1
			Connection conn = DbHelper.getConn();
			String sql = "UPDATE movie set point=point-1 WHERE movieno=" +movieno;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//点赞point数据库中添加账号和电影编号
			Connection conn2 = DbHelper.getConn();
			String sql2 = "INSERT INTO `point`(`account`, `mno`) VALUES (?,?)";
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, account);
				pst2.setInt(2, movieno);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			//movie数据库中点赞数point减1
			Connection conn = DbHelper.getConn();
			String sql = "UPDATE movie set point=point+1 WHERE movieno=" +movieno;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//点赞point数据库中删除账号和电影编号
			Connection conn2 = DbHelper.getConn();
			String sql2 = "DELETE FROM `point` WHERE account=? AND mno=?";
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, account);
				pst2.setInt(2, movieno);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setStar(int account, int movieno) {
		//收藏机制
		int starstate = 0;
		try {
			Connection conn1 = DbHelper.getConn();
			String sql1 = "SELECT * FROM star WHERE account=" 
					+ account + " AND mno=" + movieno;
			PreparedStatement pst1 = conn1.prepareStatement(sql1);
			ResultSet rs1 = pst1.executeQuery();
			if (!rs1.next()) {
				starstate = 0;
			}
			else {
				starstate = 1;
			}
			rs1.close();
			pst1.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	//完成判断是否有关于电影id和账号的收藏记录
		
		if (starstate == 1) {
			//movie表中收藏数star减1
			Connection conn = DbHelper.getConn();
			String sql = "UPDATE movie SET star=star-1 WHERE movieno=" +movieno;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//收藏star表中添加账号和电影编号
			Connection conn2 = DbHelper.getConn();
			String sql2 = "INSERT INTO `star`(`account`, `mno`) VALUES (?,?)";
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, account);
				pst2.setInt(2, movieno);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			//movie表中收藏数star加1
			Connection conn = DbHelper.getConn();
			String sql = "UPDATE movie SET star=star+1 WHERE movieno=" +movieno;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//收藏star表中删除账号和电影编号
			Connection conn2 = DbHelper.getConn();
			String sql2 = "DELETE FROM `star` WHERE account=? AND mno=?";
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, account);
				pst2.setInt(2, movieno);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setComment(int account, int movieno, int grade, String commenttext) {
		//添加评论机制
		int commentstate = 0;
		try {
			Connection conn0 = DbHelper.getConn();
			String sql0 = "SELECT * FROM comments WHERE account=" 
					+ account + " AND mno=" + movieno;
			PreparedStatement pst0 = conn0.prepareStatement(sql0);
			ResultSet rs0 = pst0.executeQuery();
			if (!rs0.next()) {
				commentstate = 0;
			}
			else {
				commentstate = 1;
			}
			rs0.close();
			pst0.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	//完成判断是否有关于电影id和账号的评论记录
		
		if (commentstate == 0) {
			//movie数据库中评论数point加1
			Connection conn = DbHelper.getConn();
			String sql = "UPDATE movie SET commentcnt=commentcnt+1 WHERE movieno=" +movieno;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//评分数加1
			sql = "UPDATE movie set cnt=cnt+1 WHERE movieno=" +movieno;
			try {
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//评论comments数据库中添加账号、电影编号、评分及影评
			Connection conn2 = DbHelper.getConn();
			String sql2 = "INSERT INTO `comments`(`account`,`mno`,`cgrade`,`ccomment`) VALUES (?,?,?,?)";
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, account);
				pst2.setInt(2, movieno);
				pst2.setInt(3, grade);
				pst2.setString(4, commenttext);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			//修改评论（评分）
			Connection conn2 = DbHelper.getConn();
			String sql2 = "UPDATE `comments` SET ccomment=" 
					+ commenttext + ", cgrade=" + grade + "WHERE account=" 
					+ account + " AND mno=" + movieno;
			PreparedStatement pst2;
			try {
				pst2 = conn2.prepareStatement(sql2);
				pst2.executeUpdate();
				pst2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteComment(int account, int movieno) {
		//删除评论机制
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `comments` WHERE account=? AND mno=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, account);
			pst.setInt(2, movieno);
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
