package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Comments;
import com.web.entity.Star;

public class CommentDao {
	public List<Comments> getAllComments(){
		//查
		List<Comments> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT * FROM comments";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Comments comment = new Comments();
				comment.setAccount(rs.getInt("account"));
				comment.setMno(rs.getInt("mno"));
				comment.setCgrade(rs.getInt("cgrade"));
				comment.setCcomment(rs.getString("ccomment"));
				list.add(comment);
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean addComments(Comments comment) {
		//增	
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `comments` (`account`,`mno`,`cgrade`,`ccomment`) VALUES (?,?,?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comment.getAccount());
			pst.setInt(2, comment.getMno());
			pst.setInt(3, comment.getCgrade());
			pst.setString(4, comment.getCcomment());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteComments(int account){
		//删
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `comments` WHERE account = ?"; // 删除的SQL语句，根据电影编号删除
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, account);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // 是否删除的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateComments(Comments comment){
		//改
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `comments` SET `account`=?,`mno`=?,`cgrade`=?,`ccomment`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comment.getAccount());
			pst.setInt(2, comment.getMno());
			pst.setInt(3, comment.getCgrade());
			pst.setString(4, comment.getCcomment());
			int count = pst.executeUpdate();
			pst.close(); // 关闭s
			return count > 0 ? true : false; // 是否修改的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
