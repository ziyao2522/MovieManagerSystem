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
		//��
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
		//��	
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
		//ɾ
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `point` WHERE account = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
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
	public boolean updatePoint(Point point){
		//��
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `point` SET `account`=?,`mno`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, point.getAccount());
			pst.setInt(2, point.getMno());
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
