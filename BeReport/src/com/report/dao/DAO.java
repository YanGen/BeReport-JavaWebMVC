package com.report.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.report.db.JdbcUtils;




/**
 * 
 * @author Administrator
 *
 *封装基本的CRUD的方法以供子类继承使用
 *
 * @param <T> T是当前DAO处理的实体类类型 
 */
public class DAO<T> {
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	
	/**
	 * 在构造器中确定clazz的type
	 */
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs != null && typeArgs.length > 0){
				if(typeArgs[0] instanceof Class){
					clazz = (Class<T>) typeArgs[0];
				}
				
			}
		}
	}
	/**
	 * 返回某一条字段的值，如Id
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getValue(String sql ,Object ...args){
		Connection connection = null;
		try{
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection,sql,new ScalarHandler(),args);
		}catch(Exception e){
			
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	public void update(String sql,Object ... args){
		Connection connection = null;
		try{
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection,sql,args);
		}catch(Exception e){
			
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
	}
	/**
	 * 返回T的实体类的对象.
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql ,Object ... args){
		Connection connection = null;
		try{
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
		}catch(Exception e){
			
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	/**
	 * 获取数据集
	 */
	public List<T> getForList(String sql ,Object ... args){
		Connection connection = null;
		try{
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection,sql,new BeanListHandler<>(clazz), args);
		}catch(Exception e){
			
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
}
