package com.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.Movie;

public class MovieDao {
	/**
	 * 
	 * @author xiaohan_whaleshark
	 * @version 2019��5��20������10:41:34
	 * @param order ����ʽ 
	 * @param ASC ����or����
	 * @return
	 */
	public List<Movie> getAllMovies(int order,boolean DESC){
		List<Movie> list = new ArrayList<>();
		String condition="";
		//DESCΪtrue���������У�false����������
		String orderType = DESC? "DESC":"ASC";
		//0 Ĭ������      1 ��������     2 ������������   3 ���յ�����     4 �����ղ���     5 ����Ӱ������       6 ������ӳʱ��        7 ���յ�Ӱʱ��
		//true	  �����ɸߵ���	   �������ɶൽ��    �������ɶൽ��   �ղ����ɶൽ��    Ӱ�������ɶൽ��   ��ӳʱ���ɽ���Զ  ��Ӱ�г��ɳ�����
		//false  �����ɵ͵���   ���������ٵ���   ���������ٵ���   �ղ������ٵ���    Ӱ���������ٵ���   ��ӳʱ����Զ����  ��Ӱ�г��ɶ̵���
		switch(order){
		case 1:
			condition = "order by cgrade/cnt "+orderType;
			break;
		case 2:
			condition = "order by cnt "+orderType;
			break;
		case 3:
			condition = "order by point "+orderType;
			break;
		case 4:
			condition = "order by star "+orderType;
			break;
		case 5:
			condition = "order by commentcnt "+orderType;
			break;
		case 6:
			condition = "order by myear "+orderType;
			break;
		case 7:
			condition = "order by mduration "+orderType;
			break;
		}
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from movie "+condition; // SQL��ѯ���
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setCno(rs.getInt("cno"));
				movie.setMno(rs.getInt("mno"));
				movie.setMname(rs.getString("mname"));
				movie.setMyear(rs.getDate("myear"));
				movie.setMduration(rs.getInt("mduration"));
				movie.setStar(rs.getInt("star"));
				movie.setPoint(rs.getInt("point"));
				movie.setCgrade(rs.getInt("cgrade"));
				movie.setCnt(rs.getInt("cnt"));
				movie.setCommentcnt(rs.getInt("commentcnt"));
				movie.setIntroduction(rs.getString("introduction"));
				list.add(movie);
			}
			rs.close(); // �ر�
			pst.close(); // �ر�
		} catch (SQLException e) {
			e.printStackTrace(); // �׳��쳣
		}
		return list;
	}
	public boolean addMovie(Movie movie){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `movie`(`cno`,`mno`,`mname`,`myear`,`mduration`,`star`,`point`,`cgrade`,`cnt`,`commentcnt`,`introduction`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"; // ��ӵ�SQL���
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, movie.getCno());
			pst.setInt(2, movie.getMno());
			pst.setString(3, movie.getMname());
			pst.setDate(4, movie.getMyear());
			pst.setInt(5, movie.getMduration());
			pst.setInt(6, movie.getStar());
			pst.setInt(7, movie.getPoint());
			pst.setInt(8, movie.getCgrade());
			pst.setInt(9, movie.getCnt());
			pst.setInt(10, movie.getCommentcnt());
			pst.setString(11, movie.getIntroduction());
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɹ���ӵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return false;
	}
	public boolean deleteMovie(String mno){
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `movie` WHERE mno = ?"; // ɾ����SQL��䣬���ݵ�Ӱ���ɾ��
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mno);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // �Ƿ�ɾ�����ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateMovie(Movie movie){
		Connection conn = DbHelper.getConn();
		String sql = "UPDATE `movie` SET `cno`=?,`mname`=?,`myear`=?,`mduration`=?,`star`=?,`point`=?,`cgrade`=?,`cnt`=?,`commentcnt`=?,`introduction`=? where `cno` = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, movie.getCno());
			pst.setString(2, movie.getMname());
			pst.setDate(3, movie.getMyear());
			pst.setInt(4, movie.getMduration());
			pst.setInt(5, movie.getStar());
			pst.setInt(6, movie.getPoint());
			pst.setInt(7, movie.getCgrade());
			pst.setInt(8, movie.getCnt());
			pst.setInt(9, movie.getCommentcnt());
			pst.setString(10, movie.getIntroduction());
			pst.setInt(11, movie.getMno());
			int count = pst.executeUpdate();
			pst.close(); // �ر�s
			return count > 0 ? true : false; // �Ƿ��޸ĵ��ж�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Movie getMovieByMno(int mno){
		Movie movie = new Movie();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "select * from movie where mno = "+mno; // SQL��ѯ���
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				movie.setCno(rs.getInt("cno"));
				movie.setMno(rs.getInt("mno"));
				movie.setMname(rs.getString("mname"));
				movie.setMyear(rs.getDate("myear"));
				movie.setMduration(rs.getInt("mduration"));
				movie.setStar(rs.getInt("star"));
				movie.setPoint(rs.getInt("point"));
				movie.setCgrade(rs.getInt("cgrade"));
				movie.setCnt(rs.getInt("cnt"));
				movie.setCommentcnt(rs.getInt("commentcnt"));
				movie.setIntroduction(rs.getString("introduction"));
			}
			rs.close(); // �ر�
			pst.close(); // �ر�
		} catch (SQLException e) {
			e.printStackTrace(); // �׳��쳣
		}
		return movie;
	}
	
	public List<Movie> getMovieByDate(String date1, String date2) {
		List<Movie> list = new ArrayList<>();
		try {
			Connection conn = DbHelper.getConn();
			String sql = "";
			if (Date.valueOf(date1).before(Date.valueOf(date2))) {
				sql = "SELECT mno FROM movie WHERE myear >= '"+ date1+ "' AND myear <= '" + date2 + "'";
			}
			else {
				sql = "SELECT mno FROM movie WHERE myear >= '"+ date2+ "' AND myear <= '" + date1 + "'";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie = getMovieByMno(rs.getInt("mno"));
				list.add(movie);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}