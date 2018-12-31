package com.report.dao;

import java.util.List;

import com.report.domain.User;

public interface UserDAO {

	public List<User> getAll();
	
	public void save(User user);
	
	public User get(String username);
	
	/**
	 * 返回和 username 相关的记录数
	 * @param username
	 * @return
	 */
	public long getCountWithUsername(String username);
}
