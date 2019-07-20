package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 约束对bs_user表的操作
 * @author xugang
 *
 */
public interface UserDao {
	
	/**
	 * 根据用户名和密码查询用户
	 * 	返回值：如果返回的user为null代表登录失败
	 * 			如果不为null代表登录成功
	 */
	User getUserByUsernameAndPassword(User loginUser);
	
	
	/**
	 * 将注册的用户信息保存到数据库中
	 * 	返回值： 如果>0代表注册成功
	 */
	int saveUser(User registUser);
}
