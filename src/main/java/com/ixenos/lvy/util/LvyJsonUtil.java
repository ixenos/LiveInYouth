package com.ixenos.lvy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 后端与前端交互的JSON的构建工具
 * 
 * @author ixenos
 *
 */
public class LvyJsonUtil {
	/*
	 * 全局使用的objectmapper
	 */
	// private static ObjectMapper objectMapper = null;
	// static{
	// objectMapper = new ObjectMapper();
	// }

	/**
	 * 平直结构的Map转成JSON
	 * 
	 * 由于这里用不到bean，所以也没必要使用Jackson（转数组、List、Map、对象成JSON的利器）
	 * 
	 * @param typeAndValue
	 *            存有响应JSON键值对的Map
	 * @return
	 */
	public static String simpleMapToJson(Map<String, String> typeAndValue) {
		StringBuffer jsonStr = new StringBuffer("{");
		Set<Entry<String, String>> set = typeAndValue.entrySet();
		for (Entry<String, String> entry : set) {
			jsonStr.append("\"");
			jsonStr.append(entry.getKey());
			jsonStr.append("\":\"");
			jsonStr.append(entry.getValue());
			jsonStr.append("\"");
			jsonStr.append(",");
		}
		// 删除最后一个逗号
		jsonStr.deleteCharAt(jsonStr.length() - 1);
		jsonStr.append("}");
		return jsonStr.toString();
	}

	/**
	 * 嵌套结构的Map转成JSON，并输出到响应
	 * 
	 * 使用Jackson框架
	 * 
	 * @param map
	 * @param response
	 * @return
	 */
	public static void mapToJsonResponse(Map<String, Object> map, HttpServletResponse response) {
		JsonGenerator jsonGenerator = null;
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();
		
		/*
		 * 前后端分离，采用JSON格式通信
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),
					JsonEncoding.UTF8);
			jsonGenerator.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!jsonGenerator.isClosed()) {
				try {
					jsonGenerator.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将对象转化成json，并发送json响应
	 * 
	 * @param object
	 *            目标对象
	 * @param response
	 *            响应
	 * @return
	 */
	public static void objToJsonResponse(Object serData, HttpServletResponse response) {
		/*
		 * 前后端分离，采用JSON格式通信
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			objToJsonOut(serData, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转化成json，并发送json到输出流中
	 * 
	 * @param serData
	 *            处理对象
	 * @param out
	 *            目标流
	 * @return
	 */
	public static void objToJsonOut(Object serData, OutputStream out) {
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();

		try {
			// 把bean直接按照该类的结构输出成JSON字符串
			objectMapper.writeValue(out, serData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转化成json，并发送json到文件中
	 * 
	 * @param serData
	 *            处理对象
	 * @param file
	 *            目标文件
	 * @return
	 */
	public static void objToJsonFile(Object serData, File file) {
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();

		try {
			System.out.println("能写到file中吗？1");
			// 把bean直接按照该类的结构输出成JSON字符串
			objectMapper.writeValue(file, serData);
			System.out.println("能写到file中吗？2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象转json字符串
	 * @param serData 被转对象
	 * @return json字符串
	 */
	public static String objToJson(Object serData) {
		ObjectMapper om = null;
		om = new ObjectMapper();
		try {
			return om.writeValueAsString(serData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 前端JSON转指定类型Bean
	 * 
	 * 利用Jackson框架
	 * 
	 * @param request
	 * @param beanClass
	 * @return
	 * @throws IOException
	 */
	public static Object jsonToObj(HttpServletRequest request, Class<?> beanClass) {
		/*
		 * 从request获取JSON
		 */
		String acceptJson = null;
		BufferedReader br = null;
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();

		try {
			br = request.getReader();
			StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			acceptJson = sb.toString();
			return objectMapper.readValue(acceptJson, beanClass);// 返回bean
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * JSON转指定类型bean <br>
	 * <br>
	 * 指定文件路径和目标bean类型 <br>
	 * <br>
	 * 使用UTF-8
	 * 
	 * @param jsonFilePath
	 *            JSON文件路径
	 * @param beanClass
	 *            目标bean类型
	 * @return
	 */
	public static Object jsonToObj(String jsonFilePath, Class<?> beanClass) {
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(new File(jsonFilePath), beanClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * JSON转指定类型bean <br>
	 * <br>
	 * 指定文件路径和目标bean类型
	 * 
	 * @param jsonFilePath
	 *            JSON文件路径
	 * @param beanClass
	 *            目标bean类型
	 * @return
	 */
	public static Object jsonToObj(InputStream in, Class<?> beanClass) {
		BufferedReader br = null;
		StringBuffer sb = null;
		String jsonStr = null;// 存储在JSON文件的信息
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();

		try {
			br = new BufferedReader(new InputStreamReader(in));
			sb = new StringBuffer();
			while (br.ready()) {
				sb.append(br.readLine());
			}
			jsonStr = sb.toString();
			return objectMapper.readValue(jsonStr, beanClass); // 返回bean
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
					System.gc();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 输出JSON到响应中
	 * @param jsonInfo
	 * @param response
	 */
	public static void jsonToResponse(String jsonInfo, HttpServletResponse response) {
		/*
		 * 前后端分离，采用JSON格式通信
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// 输出响应
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(jsonInfo);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
