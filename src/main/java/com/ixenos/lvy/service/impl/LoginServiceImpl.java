package com.ixenos.lvy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ixenos.lvy.bean.User;
import com.ixenos.lvy.dao.LoginDao;
import com.ixenos.lvy.dao.impl.LoginDaoImpl;
import com.ixenos.lvy.service.LoginService;
import com.ixenos.lvy.util.FormUtil;
import com.ixenos.lvy.util.LvyJsonUtil;

/**
 * 登录验证
 * 
 * @author ixenos
 *
 */
public class LoginServiceImpl implements LoginService {
	/*
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(LoginServiceImpl.class); 
	
	/*
	 * LoginDao
	 */
	private static LoginDao loginDao;
	
	static{
		loginDao = new LoginDaoImpl();//算不算单例
	}
	
	/**
	 * 验证账号，返回相关信息
	 * 
	 * @param user
	 * @return String类型： wrongFormat:数据格式错误，在前端输入限制的情况下发生的错误，可能有漏洞
	 *         notExist:用户不存在 wrongPassword:账号密码错误 successLogin：验证成功
	 */
	@Override
	public Object[] jsonForLogin(User user) {
		Map<String, String> jsonMap = new HashMap<>();// 存放结果

		String name = user.getUserName();
		String password = user.getUserPassword();

		// 相信前端的非空限制和后端的jackson转换，name和password不可能为空
		int formatFlag = FormUtil.validLoginFormat(name, password); // 校验格式，0:输入格式错误；1：name是userName；2：name是userEmail
		if (formatFlag != 0) {
			boolean nameFlag = loginDao.validForName(name, formatFlag); // 是否存在该账户
			boolean passwordFlag = loginDao.validForPassword(name, password, formatFlag); // 密码是否正确
			if (nameFlag) {
				if (passwordFlag) {
					jsonMap.put("success", "true");// map的key是唯一的
					jsonMap.put("type", "successLogin");
					logger.info("登录成功，用户：" + user.getUserName() + " 登录成功");
					return new Object[] { LvyJsonUtil.simpleMapToJson(jsonMap), true, formatFlag };// 验证成功,true
				} else {
					jsonMap.put("success", "false");
					jsonMap.put("type", "wrongPassword");// 密码错误,false
					logger.error("登录失败，用户：" + user.getUserName() + " 密码错误");
				}
			} else {
				jsonMap.put("success", "false");
				jsonMap.put("type", "notExist");// 不存在的账户名,false
				logger.error("登录失败，用户：" + user.getUserName() + " 不存在");
			}
		} else {
			jsonMap.put("success", "flase");
			jsonMap.put("type", "wrongFormat");// 校验失败，false,前端没做好输入限制
			logger.error("登录失败，用户名或邮箱、密码格式错误");
		}
		return new Object[] { LvyJsonUtil.simpleMapToJson(jsonMap), false, formatFlag }; // 验证失败
	}

	/**
	 * 
	 * 自动登录判断 密码为空时判断能否自动登录
	 * 密码为空时，查看session时候存在，存在的时候查看session的userMap属性，看在map中能否找到对应的username，
	 * 找到就返回登录成功的json （一个session服务一个用户多个账户，账户使用set存起来，作为属性放在session中）
	 * 
	 * 密码不为null时执行登录流程 密码为null时执行自动登录流程
	 *
	 * @param loginInfoJson
	 * @param user
	 * @param session
	 * @param response
	 * @return
	 */
	@Override
	public String jsonForLogin(User user, HttpSession session, HttpServletResponse response) {
		String loginInfoJson = null;// 返回的JSON定义

		if (user.getUserPassword() != null) {
			Object[] loginInfo = jsonForLogin(user);//调用该方法返回关键信息，本方法再进行额外操作（会话管理）

			loginInfoJson = (String) loginInfo[0]; // 返回的薛定谔JSON
			boolean loginFlag = (boolean) loginInfo[1];// 登录成功标志
			int nameOrEamilFlag = ((Integer) loginInfo[2]).intValue();// 输入账户名的是name（1）还是email（2）

			// 登录成功
			if (loginFlag) {
				User existUser = null;// 完整的用户信息
				if (nameOrEamilFlag == 1) {
					existUser = loginDao.getUserByName(user.getUserName());
				} else {
					existUser = loginDao.getUserByEmail(user.getUserName());// 小心这里，前端把email放在userName属性了，用getUserName
				}
				/*
				 * 自动登录设置模块
				 */
				// 状态在B/S双向保存，状态只包括 JSESSIONID 和 userName
				if (existUser != null) {
					if (user.isAutoLogin()) {// 如果会话不是新的，那么就不要进行自动登录设置，但是注意，“退出登录”的功能要进行会话的invalidate销毁
						Cookie nameCookie = null;
						
						if (nameOrEamilFlag == 1) {
							//保存整个用户进去，后面会用到
							session.setAttribute("user", existUser);
							session.setAttribute("userName", existUser.getUserName());// session持久化
							nameCookie = new Cookie("userName", existUser.getUserName());// cookie持久化
						} else {
							//保存整个用户进去，后面会用到
							session.setAttribute("user", existUser);
							session.setAttribute("userName", existUser.getUserEmail());
							nameCookie = new Cookie("userName", existUser.getUserEmail());
						}
						Cookie sessIdCookie = new Cookie("JSESSIONID", session.getId());// 重写主要是为了覆盖原来的值，然后才能使用setMaxAge来设定cookie端的存活时间
						
						logger.info("sessionID是" + sessIdCookie.getValue());
						
						session.setMaxInactiveInterval(30 * 24 * 60 * 60);// 设置服务端session的会话时间
						nameCookie.setMaxAge(30 * 24 * 60 * 60);
						sessIdCookie.setMaxAge(30 * 24 * 60 * 60);// 设置浏览器端的cookie的存活时间30天（以匹配服务端的会话）
						// cookie输出到响应头
						nameCookie.setPath("/LiveInYouth");
						sessIdCookie.setPath("/LiveInYouth");// 表示作用的范围
						response.addCookie(nameCookie);
						response.addCookie(sessIdCookie);
						logger.info("自动登录设置完成");
					} else {
						/*
						 * 非自动登录状态时，保存用户信息直，生命周期和自然session一致（浏览器关闭，前端sessionID失效
						 * ，后端不设置超时一定时间就自动销毁）
						 */
						// 我们只需要返回非sessionID的cookie
						logger.info("sessionID是" + session.getId());
						
						Cookie nameCookie = null;
						if (nameOrEamilFlag == 1) {
							session.setAttribute("userName", existUser.getUserName());// session持久化
							nameCookie = new Cookie("userName", existUser.getUserName());// cookie持久化
						} else {
							session.setAttribute("userName", existUser.getUserEmail());
							nameCookie = new Cookie("userName", existUser.getUserEmail());
						}

						nameCookie.setMaxAge(2 * 60 * 60);// cookie有效时间两个小时，够了
						nameCookie.setPath("/LiveInYouth");
						response.addCookie(nameCookie);
						logger.info("非自动登录设置完成");
					}
				} else {
					logger.error("用name或email取不出existUser");
				}
			}
		} else {
			/*
			 * 自动登录流程（密码为空）
			 */
			Map<String, String> jsonMap = new HashMap<>();
			jsonMap.put("success", "false");
			jsonMap.put("type", "nullValue");
			/*
			 * 判断是否自动登录流程
			 */
			// 如果request.getSession返回新的session，说明请求报文的Cookie中的sessionID在后端是不存在的，或者已撤销。
			if (!session.isNew()) {
				// 判断能否在JSON中找到userName
				String userName = (String) session.getAttribute("userName");
				if (user.getUserName().equals(userName)) {
					jsonMap.put("success", "true");
					jsonMap.put("type", "successLogin");
					loginInfoJson = LvyJsonUtil.simpleMapToJson(jsonMap);
					logger.info("自动登录成功");
				}
				// session中没有对应的userName，说明没有选择自动登录
			} else {
				// 新session
				logger.warn("如果session是新的，但是密码却为空，那么一般是前端的空值限制失败了，或者后端服务器重启了，导致session销毁");
				loginInfoJson = LvyJsonUtil.simpleMapToJson(jsonMap);
			}
		}
		return loginInfoJson;
	}

}
