package com.report.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author Administrator
 *
 *JDBC 操作工具类
 *
 */
public class JdbcUtils {
	
	private static DataSource dataSource = null;
	static{
		dataSource = new ComboPooledDataSource("BeReport");
	}
	
	/**
	 * 释放链接
	 * @param connection
	 * @return
	 */
	public static void releaseConnection(Connection connection){
		try{
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取一个链接对象
	 * @return
	 */ 
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
