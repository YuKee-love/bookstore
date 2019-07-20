package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

public interface UserService {
	User doLogin(User loginUser);//处理登录的业务
	boolean doRegist(User registUser);//处理注册的业务
}
