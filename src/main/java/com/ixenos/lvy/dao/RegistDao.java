package com.ixenos.lvy.dao;

import com.ixenos.lvy.bean.User;

/**
 * 注册相关DAO
 * @author ixenos
 *
 */
public interface RegistDao {
	
	/**
	 * 判断是否已存在用户
	 * 
	 * @param name
	 * @param formatFlag
	 *            1：name是userName 2：name是userEmail
	 * @return boolean 是否已存在用户
	 * 
	 */
	boolean restValidForName(String userName);
	
	/**
	 * 插入用户
	 * 
	 * 不使用其插入失败来判断用户是否存在，因为这里变量太多
	 * 
	 * @param user 要插入的新用户
	 */
	boolean insertUser(User user);
	
}
