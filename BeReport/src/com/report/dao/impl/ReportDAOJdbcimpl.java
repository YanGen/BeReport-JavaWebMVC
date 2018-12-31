package com.report.dao.impl;

import java.util.List;

import com.report.dao.DAO;
import com.report.dao.ReportDao;
import com.report.domain.Report;

public class ReportDAOJdbcimpl extends DAO<Report> implements ReportDao{

	@Override
	public void save(Report report) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO report(username,title,tday,tsecond,type,jdata)Values (?,?,?,?,?,?)";
		update(sql, report.getUsername(),report.getTitle(),report.getTday(),report.getTsecond(),report.getType(),report.getJdata());
		
	}

	@Override
	public Report get(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT username,title,tday,tsecond,type,jdata FROM report WHERE id=?";
		return get(sql,id);
	}
	@Override
	public long getCountWithInfo(String id) {
		String sql = "SELECT count(id)FROM report WHERE id=?";
		return getValue(sql, id);
	}

	@Override
	public List<Report> getListByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT id,username,title,tday,tsecond,type FROM report WHERE username=?";
		return getForList(sql,username);
	}

}
