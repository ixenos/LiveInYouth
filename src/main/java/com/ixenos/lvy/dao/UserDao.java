package com.ixenos.lvy.dao;


import com.ixenos.lvy.bean.User;

/**
 * user表的dao
 * @author ixenos
 *
 */
public interface UserDao {
	/**
	 * 通过songListId获取user
	 * @param songListId 歌单表id
	 * @return user
	 */
	User getUserBySongListId(int songListId);
	
	/**
	 * 通过用户名更新订阅字段
	 * @param userName 需要更新字段的用户
	 * @param subsFlag 订阅字段的boolean值，false 0 取消订阅； true 1 订阅
	 * @return
	 */
	boolean updateSubsByName(String userName, boolean subsFlag);
	
	/**
	 * 通过用户名查询订阅字段
	 * @param userName 需要查询的用户
	 * @return 返回查询字段的值  -1表示取不到当前用户，0表示当前用户未订阅，1表示已订阅
	 */ 
	int getSubsFlagByName(String userName);
	
	/**
	 * 通过邮箱更新订阅字段
	 * @param userName 需要更新字段的用户
	 * @param subsFlag 订阅字段的boolean值，订阅字段的boolean值，false 0 取消订阅； true 1 订阅
	 * @return
	 */
	boolean updateSubsByEmail(String userEmail, boolean subsFlag);
	
	/**
	 * 通过邮箱查询订阅字段
	 * @param userName 需要查询的用户
	 * @return 返回查询字段的值 -1表示取不到当前用户，0表示当前用户未订阅，1表示已订阅
	 */ 
	int getSubsFlagByEmail(String userEmail);
	
	/**
	 * 通过user bean来更新user
	 * @param user
	 * @return
	 */
	boolean updateUserByBean(User user);
}
