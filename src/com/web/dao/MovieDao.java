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
	 * @version 2019年5月20日上午10:41:34
	 * @param order 排序方式 
	 * @param ASC 升序or降序
	 * @return
	 */
	public List<Movie> getAllMovies(int order,boolean DESC){
		List<Movie> list = new ArrayList<>();
		String condition="";
		//DESC为true：降序排列；false：升序排列
		String orderType = DESC? "DESC":"ASC";
		//0 默认排序      1 按照评分     2 按照评价人数   3 按照点赞数     4 按照收藏数     5 按照影评数量       6 按照上映时间        7 按照电影时长
		//true	  评分由高到低	   评价数由多到少    点赞数由多到少   收藏数由多到少    影评数量由多到少   上映时间由近到远  电影市场由长到短
		//false  评分由低到高   评价数由少到多   点赞数由少到多   收藏数由少到多    影评数量由少到多   上映时间由远到近  电影市场由短到长
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
			String sql = "select * from movie "+condition; // SQL查询语句
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
			rs.close(); // 关闭
			pst.close(); // 关闭
		} catch (SQLException e) {
			e.printStackTrace(); // 抛出异常
		}
		return list;
	}
	public boolean addMovie(Movie movie){
		Connection conn = DbHelper.getConn();
		String sql = "INSERT INTO `movie`(`cno`,`mno`,`mname`,`myear`,`mduration`,`star`,`point`,`cgrade`,`cnt`,`commentcnt`,`introduction`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"; // 添加的SQL语句
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
			return count > 0 ? true : false; // 是否成功添加的判断
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return false;
	}
	public boolean deleteMovie(String mno){
		Connection conn = DbHelper.getConn();
		String sql = "DELETE FROM `movie` WHERE mno = ?"; // 删除的SQL语句，根据电影编号删除
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mno);
			int count = pst.executeUpdate();
			pst.close();
			return count > 0 ? true : false; // 是否删除的判断
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
			pst.close(); // 关闭s
			return count > 0 ? true : false; // 是否修改的判断
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
			String sql = "select * from movie where mno = "+mno; // SQL查询语句
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
			rs.close(); // 关闭
			pst.close(); // 关闭
		} catch (SQLException e) {
			e.printStackTrace(); // 抛出异常
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