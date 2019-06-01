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
		//��
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
		//��	
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
		//ɾ
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `comments` WHERE account = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, account);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɾ�����ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateComments(Comments comment){
		//��
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
			pst.close(); // �ر�s
			return count > 0 ? true : false; // �Ƿ��޸ĵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
