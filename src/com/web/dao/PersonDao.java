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
		//DESC为true：降序排列；false：升序排列
		String orderType = ASC? "ASC":"DESC";
		//0 默认排序      1 按照姓名     2 按照出生日期
		//true	       姓名升序	       年龄降序
		//false   姓名降序           年龄升序
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
			String sql = "select * from person "+condition; // SQL查询语句
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
			rs.close(); // 关闭
			pst.close(); // 关闭
		} catch (SQLException e) {
			e.printStackTrace(); // 抛出异常
		}		
		return list;
	}
	public boolean addPerson(Person person){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `person`(`pno`,`pname`,`pbirth`) VALUES (?,?,?)"; // 添加的SQL语句
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, person.getPno());
			pst.setString(2, person.getPname());
			pst.setDate(3, person.getPbirth());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // 是否成功添加的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return false;
	}
	public boolean deletePerson(int pno){
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `person` WHERE pno = ?"; // 删除的SQL语句，根据电影编号删除
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pno);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // 是否删除的判断
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
			pst.close(); // 关闭
			return count > 0 ? true : false; // 是否修改的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
