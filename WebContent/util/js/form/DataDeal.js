/**
 * 	util.js.form
 * 
 * 	DataDeal
 */

// 全局$.ajax的设置
$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded; charset=utf-8"
});

var DataDeal = {
	//将序列化的表单数据转换成JSON
	formUrlToJson : function(data) {
		//防止中文乱码，很有必要！因为serialize是按照URL编码的格式来序列化的，中文会处理成两个十六位的字符
		data = decodeURIComponent(data, true);
		//  ‘/&’匹配‘&’字符， ‘/g’表示匹配全局 ； 第二个参数是替换的字符串，注意转义字符
		data = data.replace(/&/g, "\",\"");
		data = data.replace(/=/g, "\":\"");
		data = "{\"" + data + "\"}";
		return data;
	},
};