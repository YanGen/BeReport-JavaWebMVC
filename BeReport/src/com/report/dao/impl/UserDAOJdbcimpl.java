package com.report.dao.impl;

import java.util.List;

import com.report.dao.DAO;
import com.report.dao.UserDAO;
import com.report.domain.User;

public class UserDAOJdbcimpl extends DAO<User> implements UserDAO{

	@Override
	public List<User> getAll() {
		String sql = "SELECT id,username,email,manager FROM user";
		return getForList(sql);
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO user(username,password,email,manager)Values (?,?,?,?)";
		update(sql, user.getUsername(),user.getPassword(),user.getEmail(),"no");
		
	}

	@Override
	public User get(String username) {
		String sql = "SELECT username,password,email,manager FROM user WHERE username = ?";
		return get(sql,username);
	}

	@Override
	public long getCountWithUsername(String username) {
		String sql = "SELECT count(username)FROM user WHERE username=?";
		return getValue(sql, username);
	}

}
