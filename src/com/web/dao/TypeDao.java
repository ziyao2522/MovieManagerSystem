package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Type;

public class TypeDao {
	public List<Type> getAllTypes(){
		List<Type> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from types "; // SQL��ѯ���
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Type type = new Type();
				type.setTname(rs.getString("tname"));
				type.setTnote(rs.getString("tnote"));
				type.setTlimit(rs.getInt("tlimit"));
				list.add(type);
			}
			rs.close(); // �ر�
			pst.close(); // �ر�
		} catch (SQLException e) {
			e.printStackTrace(); // �׳��쳣
		}
		return list;
	}
	
	public boolean addType(Type type){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `types`(`tname`,`tnote`,`tlimit`) VALUES (?,?,?)"; // ��ӵ�SQL���
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, type.getTname());
			pst.setString(2, type.getTnote());
			pst.setInt(3, type.getTlimit());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɹ���ӵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return false;
	}
	
	public boolean deleteType(String tname) {
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `types` WHERE tname = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, tname);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɾ�����ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateType(Type type) {
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `types` SET `tname`=?,`tnote`=?,`tlimit`=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, type.getTname());
			pst.setString(2, type.getTnote());
			pst.setInt(3, type.getTlimit());
			int count = pst.executeUpdate();
			pst.close(); // �ر�
			return count > 0 ? true : false; // �Ƿ��޸ĵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
