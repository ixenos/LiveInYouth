package com.ixenos.lvy.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ixenos.lvy.bean.User;

/**
 * 登录服务
 * 
 * @author ixenos
 *
 */
public interface LoginService {
	/**
	 * 验证账号，返回相关信息
	 * 
	 * @param user
	 * 
	 * @return String, boolean；
	 * 
	 *         String： 直接返回相关JSON 1.wrongFormat:数据格式错误，在前端输入限制的情况下发生的错误，可能有漏洞；
	 *         2.notExist:用户不存在； 3.wrongPassword:账号密码错误； 4.successLogin：验证成功；
	 * 
	 *         boolean: 登录是否成功；
	 */
	Object[] jsonForLogin(User user);

	String jsonForLogin(User user, HttpSession session, HttpServletResponse response);
}
