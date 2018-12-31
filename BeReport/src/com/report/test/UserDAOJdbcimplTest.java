package com.report.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.report.dao.UserDAO;
import com.report.dao.impl.UserDAOJdbcimpl;
import com.report.domain.User;

public class UserDAOJdbcimplTest {

	private UserDAO userDAO = new UserDAOJdbcimpl();
	@Test
	public void testGetAll() {

		List<User> users = userDAO.getAll();
		for(User user :users){
			System.out.println(user.getUsername());
		}
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("阳耿");
		user.setPassword("YG.1234");
		user.setEmail("zorage@qq.com");
		//userDAO.save(user);
		System.out.println("插入成功 ");
		
	}

	@Test
	public void testGetString() {
		
		User user = userDAO.get("阳耿");
		System.out.println("阳耿的email是"+user.getEmail());
				
	}

	@Test
	public void testGetCountWithUsername() {

		long num = userDAO.getCountWithUsername("阳耿");
		System.out.println("共有"+num+"个阳耿");
	}

}
