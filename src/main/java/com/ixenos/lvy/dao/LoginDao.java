package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.User;

/**
 * 登录相关DAO操作
 * 
 * @author ixenos
 *
 */
public interface LoginDao {
	/**
	 * 通过用户名的类别（name或者email）给出对应sql语句
	 * 
	 * @param formatFlag
	 *            1：name是userName 2：name是userEmail
	 * @return
	 */
	String[] sqlForLogin(int formatFlag);
	
	/**
	 * 判断是否已存在用户
	 * 
	 * @param name
	 * @param formatFlag
	 *            1：name是userName 2：name是userEmail
	 * @return boolean 是否已存在用户
	 */
	boolean validForName(String name, int formatFlag);
	
	/**
	 * 登录验证 根据输入账号类型(name or email) 用户名和密码
	 * 
	 * @param name
	 * @param password
	 * @param formatFlag
	 *            1:name和value正常 2：name是email
	 * @return 密码是否正确
	 */
	boolean validForPassword(String name, String password, int formatFlag);
	
	/**
	 * 通过userName取user
	 * 
	 * 可用于判断用户是否存在:
	 * 	这里name只能是name
	 * 
	 * @param userName
	 * @return
	 */
	User getUserByName(String userName);
	
	/**
	 * 通过userEmail取user
	 * 
	 * 可用于判断用户是否存在:
	 * 	这里name只能是email
	 * 
	 * @param userEmail
	 * @return
	 */
	User getUserByEmail(String userEmail);
	
}
