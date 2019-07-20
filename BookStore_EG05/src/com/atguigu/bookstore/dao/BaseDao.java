package com.atguigu.bookstore.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCTools;

public class BaseDao {
	private QueryRunner runner = new QueryRunner();
	
	/**
	 * 1、查询一条记录封装为一个对象
	 * 	参数1： sql语句查询到的数据要封装的对象的类型
	 * 	泛型： 可以指定数据类型
	 */
	public <T>T getBean(Class<T> clazz,String sql , Object...params) {
		Connection conn = JDBCTools.getConn();
		T t = null;
		try {
			t = runner.query(conn, sql, new BeanHandler<T>(clazz), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCTools.closeConn(conn);
		}
		
		return t;
		
	}
	
	
	
	/**
	 * 2、查询多条记录封装为对象的集合
	 */
	public <T>List<T> getBeanList(Class<T> clazz,String sql , Object...params) {
		Connection conn = JDBCTools.getConn();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<T>(clazz), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCTools.closeConn(conn);
		}
		return list;
		
	}
	
	/**
	 * 3、增删改
	 * 		凡是不能确定的数据可以以参数的形式找调用者要
	 * 		可变参数列表用来传递sql需要的占位符参数列表：  参数个数顺序需要和占位符一样
	 */
	public int update(String sql,Object...params) {
		Connection conn = JDBCTools.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCTools.closeConn(conn);
		}
		return i;
		
	}
	
	
	/**
	 * 4、查询表的记录总条数(分页需要使用 )
	 *  select count(1) from bs_user;
	 */
	public int getCount(String sql , Object...params) {
		Connection conn = JDBCTools.getConn();
		Integer count = 0;
		try {
			//查询数量时，mysql默认将数字以Long类型返回
			long l = (long) runner.query(conn, sql, new ScalarHandler(), params);
			count = (int)l;
		} catch (SQLException e) {
//			e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCTools.closeConn(conn);
		}
		return count;
		
	}
	/**
	 * 5、批量增删改(如果有大量的相同的操作，可以使用批处理提高效率)
	 * 		批量操作:
	 * 			1、需要知道批处理的次数
	 * 			2、每次执行时sql对应的占位符参数列表
	 * UPDATE t_employee SET salary = salary+? , commission_pct = ? WHERE eid = ？
	 * 	- 需求：修改员工id为1,2,3,4.... 对应的工资和奖金，每个员工的修改的数据都不一样
	 * 		第一次执行： 修改id=1的员工： 100 ， 1.1
	 * 					修改id=2的员工： 200 ， 1.2
	 * 
	 * 	- 二维数组的第一维长度用来告诉QueryRunner批处理执行的次数
	 * 	- 二维数组的第二维用来对应的保存每次执行时占位符参数列表
	 * 
	 */
	public void batchUpdate(String sql , Object[][]params) {
		Connection conn = JDBCTools.getConn();
		
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCTools.closeConn(conn);
		}
		
	}
	
}
