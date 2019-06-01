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
		//查
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
		//增	
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
		//删
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `star` WHERE account = ?"; // 删除的SQL语句，根据电影编号删除
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
	public boolean updateStar(Star star){
		//改
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `star` SET `account`=?,`mno`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, star.getAccount());
			pst.setInt(2, star.getMno());
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
