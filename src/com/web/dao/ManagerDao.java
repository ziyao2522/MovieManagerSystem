package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Manager;

public class ManagerDao {
	public List<Manager> getAllManagers(){
		List<Manager> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT * FROM manager";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				manager.setMaccount(rs.getString("maccount"));
				manager.setMpwd(rs.getString("mpwd"));
				list.add(manager);
			}
			rs.close();
			pst.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addManager(Manager manager) {
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `manager`(`maccount`,`mpwd`) VALUES (?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, manager.getMaccount());
			pst.setString(2, manager.getMpwd());
			int count = pst.executeUpdate();	
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteManager(String maccount) {
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `company` WHERE maccount = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, maccount);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateManager(Manager manager) {
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `company` SET `maccount`=?, `mpwd`=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, manager.getMaccount());
			pst.setString(2, manager.getMpwd());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
