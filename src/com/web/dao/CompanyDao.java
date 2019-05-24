package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Company;

public class CompanyDao {
	public List<Company> getAllCompanies(){
		List<Company> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT * FROM company";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Company company = new Company();
				company.setCno(rs.getInt("cno"));
				company.setCname(rs.getString("cname"));
				company.setCcity(rs.getString("ccity"));
				list.add(company);
			}
			rs.close();
			pst.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addCompany(Company company) {
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `company`(`cno`,`cname`,`ccity`) VALUES (?,?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, company.getCno());
			pst.setString(2, company.getCname());
			pst.setString(3, company.getCcity());
			int count = pst.executeUpdate();	
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCommpany(int cno) {
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `company` WHERE cno = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cno);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateCompany(Company company) {
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `company` SET `cno`=?, `cname`=?, `ccity`=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, company.getCno());
			pst.setString(2, company.getCname());
			pst.setString(3, company.getCcity());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true:false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
