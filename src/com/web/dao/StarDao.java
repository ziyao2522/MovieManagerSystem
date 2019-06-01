package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Star;

public class StarDao {
	public List<Star> getAllStars(){
		//��
		List<Star> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT * FROM star";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Star star = new Star();
				star.setAccount(rs.getInt("account"));
				star.setMno(rs.getInt("mno"));
				list.add(star);
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean addStar(Star star) {
		//��	
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `star` (`account`,`mno`) VALUES (?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, star.getAccount());
			pst.setInt(2, star.getMno());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteStar(int account){
		//ɾ
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `star` WHERE account = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
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
	public boolean updateStar(Star star){
		//��
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `star` SET `account`=?,`mno`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, star.getAccount());
			pst.setInt(2, star.getMno());
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
