package com.ixenos.lvy.util;

import com.ixenos.lvy.bean.User;

/**
 * 面向前端表单数据的工具
 * 
 * @author ixenos
 *
 */
public class FormUtil {
	/*
	 * userEmail正则表达式，两个“\”是因为“\”也需要转义
	 */
	private static final String emailRegex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
	/*
	 * username正则表达式，字母开头，允许5-16字节，允许字母数字下划线；前端注意上下限；^表示以什么开头，$表示以什么结尾
	 */
	private static final String nameRegex = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,15}$";
	/*
	 * userPassword正则表达式，以字母开头，长度在5-16之间；前端注意上下限
	 */
	private static final String passwordRegex = "^([a-zA-Z0-9]|[._]){5,16}$"; 
	
	/**
	 * 登录校验格式
	 * 
	 * 字符类型、长度
	 * 
	 * @param name
	 * @return formatFlag: 
	 * 				0:格式错误，正常不能再后台检测出来，请检查前端限制代码 
	 * 				 1:name是userName
	 * 				 2：name是userEmail
	 */
	public static int validLoginFormat(String userName, String userPassword) {
		if (userPassword.matches(passwordRegex)) {
			//下面两个if不嵌套，如果重排序优化也许可以提高效率
			if (userName.matches(nameRegex)) {
				return 1;
			}
			if (userName.matches(emailRegex)) {
				return 2;
			}
			//return 0; 和下面的重合，可省略
		}
		return 0;
	}
	
	/**
	 * 区分name和email
	 * 
	 * 字符类型、长度
	 * 
	 * @param userNameOrEmail 待区分的用户名或email
	 * @return formatFlag: <br>
	 * 				0:格式错误，正常不能再后台检测出来，请检查前端限制代码 
	 * 				 1:name是userName
	 * 				 2：name是userEmail
	 */
	public static int discernNameOrEmail(String userNameOrEmail){
		if (userNameOrEmail.matches(nameRegex)) {
			return 1;
		}
		if (userNameOrEmail.matches(emailRegex)) {
			return 2;
		}
		return 0;
	}

	/**
	 * 注册校验格式
	 * 
	 * 字符类型、长度
	 * 
	 * @param user
	 * @return false 格式错误，检查前端限制代码
	 */
	public static boolean validateRegistFormat(User user) {
		String userName = user.getUserName();
		String userEmail = user.getUserEmail();
		String password = user.getUserPassword();
		if (userName.matches(nameRegex) && userEmail.matches(emailRegex) && password.matches(passwordRegex)) {
			return true;
		}
		return false;
	}
}
