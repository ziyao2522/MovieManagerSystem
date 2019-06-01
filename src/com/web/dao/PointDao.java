package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Point;

public class PointDao {
	public List<Point> getAllPoints(){
		//查
		List<Point> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT * FROM point";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Point point = new Point();
				point.setAccount(rs.getInt("account"));
				point.setMno(rs.getInt("mno"));
				list.add(point);
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean addPoint(Point point) {
		//增	
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `point` (`account`,`mno`) VALUES (?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, point.getAccount());
			pst.setInt(2, point.getMno());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deletepoint(int account){
		//删
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `point` WHERE account = ?"; // 删除的SQL语句，根据电影编号删除
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
	public boolean updatePoint(Point point){
		//改
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `point` SET `account`=?,`mno`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, point.getAccount());
			pst.setInt(2, point.getMno());
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
