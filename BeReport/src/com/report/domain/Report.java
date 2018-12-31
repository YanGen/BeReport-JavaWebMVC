package com.report.domain;

public class Report {
	private Integer id;
	private String title;
	private String username;
	private String tday;
	private String tsecond;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTday() {
		return tday;
	}
	public void setTday(String tday) {
		this.tday = tday;
	}
	public String getTsecond() {
		return tsecond;
	}
	public void setTsecond(String tsecond) {
		this.tsecond = tsecond;
	}
	private String type;
	private String jdata;
	/**
	 * @param username
	 * @param type
	 * @param jdata
	 */
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJdata() {
		return jdata;
	}
	public void setJdata(String jdata) {
		this.jdata = jdata;
	}
}
