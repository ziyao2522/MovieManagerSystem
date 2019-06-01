package com.web.dao;

import java.sql.Date;
import java.util.List;

import com.web.entity.Person;
import com.web.entity.Star;

public class DaoPatternDemo {
	public static void main(String[] args) {
		StarDao stardao = new StarDao();
		Star star = new Star();
		star.setAccount(40000004);
		star.setMno(20000001);
		stardao.addStar(star);
	}	
}