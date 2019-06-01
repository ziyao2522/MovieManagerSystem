package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.User;

public class UserDao {
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from users "; // SQL查询语句
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setAccount(rs.getInt("account"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setU_birth(rs.getDate("u_birth"));
				list.add(user);
			}
			rs.close(); // 关闭
			pst.close(); // 关闭
		} catch (SQLException e) {
			e.printStackTrace(); // 抛出异常
		}
		return list;
	}
	public boolean addUser(User user){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `users`(`account`,`uname`,`upwd`,`email`,`phone`,`u_birth`) VALUES (?,?,?,?,?,?)"; // 添加的SQL语句
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getAccount());
			pst.setString(2, user.getUname());
			pst.setString(3, user.getUpwd());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getPhone());
			pst.setDate(6, user.getU_birth());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // 是否成功添加的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		//添加user的account
		sql = "SELECT max(account) FROM `users` WHERE `email`=" + user.getEmail() + " AND `uname`="+user.getUname();
		try {
			PreparedStatement pst1 = conn.prepareStatement(sql);
			ResultSet rs = pst1.executeQuery();
			while (rs.next()) {
				user.setAccount(rs.getInt("account"));
			}
			rs.close();
			pst1.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean deleteUser(int account){
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `users` WHERE account = ?";
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
	public boolean updateUser(User user){
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `users` SET `uname`=?,`upwd`=?,`email`=?,`phone`=?,`u_birth`=? where `account` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUname());
			pst.setString(2, user.getUpwd());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPhone());
			pst.setDate(5, user.getU_birth());
			pst.setInt(6, user.getAccount());
			int count = pst.executeUpdate();
			pst.close(); // 关闭
			return count > 0 ? true : false; // 是否修改的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public User getUserByAccount(int account) {
		User user = new User();
		
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from users where account = "+account; // SQL查询语句
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				user.setAccount(rs.getInt("account"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setU_birth(rs.getDate("u_birth"));
			}
			rs.close(); // 关闭
			pst.close(); // 关闭
		} catch (SQLException e) {
			e.printStackTrace(); // 抛出异常
		}
		return user;
	}
	
	public boolean judgeUser(int account, String password) {
		try {
			Connection conn = DbHelper.getConn();
			String sql = "SELECT upwd FROM users WHERE account=" + account;
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if((rs.getString("upwd")).equals(password)) {
					return true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
