package com.web.dao;

import java.util.List;

import com.web.entity.Company;

public class DaoPatternDemo {

	public static void main(String[] args) {
		CompanyDao companyDao = new CompanyDao();
		List<Company> list = companyDao.getAllCompanies();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
}