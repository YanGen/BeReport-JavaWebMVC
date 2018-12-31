package com.zorage.chat;

public class ChatObject {
	private int id;
	private String username;
	private String date;
	private String content;
	private String manager;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public ChatObject() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param username
	 * @param date
	 * @param content
	 */
	public ChatObject(int id, String username, String date, String content,String manager) {
		super();
		this.id = id;
		this.username = username;
		this.date = date;
		this.content = content;
		this.manager = manager;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
		
}
