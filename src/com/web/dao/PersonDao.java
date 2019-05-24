package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Person;

public class PersonDao {
	public List<Person> getAllPerson(){
		return getAllPerson(0,true);
	}
	public List<Person> getAllPerson(int order,boolean ASC){
		List<Person> list = new ArrayList<>();
		String condition="";
		//DESCΪtrue���������У�false����������
		String orderType = ASC? "ASC":"DESC";
		//0 Ĭ������      1 ��������     2 ���ճ�������
		//true	       ��������	       ���併��
		//false   ��������           ��������
		switch(order){
		case 0:
			condition = "order by pno "+orderType;
			break;
		case 1:
			condition = "order by pname "+orderType;
			break;
		case 2:
			condition = "order by pbirth "+orderType;
			break;
		}
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from person "+condition; // SQL��ѯ���
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Person person = new Person();
				person.setPno(rs.getInt("pno"));
				person.setPname(rs.getString("pname"));
				person.setPbirth(rs.getDate("pbirth"));
				list.add(person);
			}
			rs.close(); // �ر�
			pst.close(); // �ر�
		} catch (SQLException e) {
			e.printStackTrace(); // �׳��쳣
		}		
		return list;
	}
	public boolean addPerson(Person person){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `person`(`pno`,`pname`,`pbirth`) VALUES (?,?,?)"; // ��ӵ�SQL���
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, person.getPno());
			pst.setString(2, person.getPname());
			pst.setDate(3, person.getPbirth());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɹ���ӵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return false;
	}
	public boolean deletePerson(int pno){
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `person` WHERE pno = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pno);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɾ�����ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updatePerson(Person person){
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `person` SET `pname`=?,`pbirth`=? where `pno` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, person.getPname());
			pst.setDate(2, person.getPbirth());
			pst.setInt(3, person.getPno());
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
