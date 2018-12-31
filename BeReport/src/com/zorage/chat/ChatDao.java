package com.zorage.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDao {
	public void CreateMassage(String name,String date,String content,String manager){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			String driverClass = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql:///bereport";
			String user = "root";
			
			String password = "root";
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url,user,password);
			String sql="insert into chat (username,date,content,manager) values (?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, content);
			preparedStatement.setString(4, manager);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (preparedStatement != null){
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}try {
				if (connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<ChatObject> getAll(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ChatObject> chats = new ArrayList<>();
		try {
			
			String driverClass = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql:///bereport";
			String user = "root";
			
			String password = "root";
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url,user,password);
			String sql = "SELECT id , username,date,content,manager from chat";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String username = resultSet.getString(2);
				String date = resultSet.getString(3);
				String content = resultSet.getString(4);
				String manager = resultSet.getString(5);
				ChatObject chat = new ChatObject(id,username,date,content,manager);
				chats.add(chat);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (resultSet != null){
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null){
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}try {
				if (connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chats;
}
}
