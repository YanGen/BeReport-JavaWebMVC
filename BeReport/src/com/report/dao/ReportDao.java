package com.report.dao;

import java.util.List;

import com.report.domain.Report;

public interface ReportDao {
	public List<Report> getListByUsername(String username);

	public void save(Report report);
	
	public Report get(String id);
	
	public long getCountWithInfo(String id);
	
}
