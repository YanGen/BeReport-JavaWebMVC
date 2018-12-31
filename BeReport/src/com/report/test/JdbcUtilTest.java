package com.report.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.report.db.JdbcUtils;

public class JdbcUtilTest {

	@Test
	public void testGetConnection() throws SQLException{

		Connection connection = JdbcUtils.getConnection();
		System.out.println(connection);
	}

}
