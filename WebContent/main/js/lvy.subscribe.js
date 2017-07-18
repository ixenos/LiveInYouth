/*
 * 订阅模块js
 * 
 * author:眼聆
 * authorEmail:ixenos@foxmail.com
 * create date:2017-4-17
 * last modify 
 * date：2017-5-13：：fixed:change the absolute path to relative path
 * data：2017-5-15：：fixed：change the pattern of the function in order to make it normal
 * 
 * 依赖：
 * 		jquery.min.js
 * 		js.cookie.js （原生的js编写的）
 */
$(function(){
		
	var bottomSubs = function(){
			
		//三个部分
		var loginSubs = document.getElementById("loginSubs");
		var registSubs = document.getElementById("registSubs");
		var hasSubs = document.getElementById("hasSubs");

		//取不到JSESSIONID只是说明没有自动登录，而取不到userName才可以代表没有登录
		var loginFlag = Cookies.get("userName");
		if(loginFlag == undefined){
			/*
				未登录时，点击登录并订阅时，跳转登录页面的登录页面,TODO:在login根据参数的提示跳转mian的订阅部分
			*/
			registSubs.addEventListener("click", function(){
				//在URL即传参又设置锚点:XXXX?id=XX&number=YY#zz
				window.location = "/LiveInYouth";//跳转登录注册页面
			});
		}else{
			/*
				已登录时，显示订阅，隐藏登录并订阅
			*/
			loginSubs.classList.remove("hidden");//显示订阅
			registSubs.classList.add("hidden");//隐藏登录并订阅
			
			/*
				已登录时，自动ajax查询是否已订阅，已订阅显示不可选的已订阅按钮
			*/
			var url = "/LiveInYouth/Subscribe";
			$.ajax({
				cache : false , //开启缓存，下个ajax会从缓存中读数据，除非url重写（cache:false就是给url加上时间戳来不缓存请求的）
				type : "GET", //具有幂等性的GET才能使用cache，POST不符合幂等性，所以cache没有意义
				url : url,
				data : "type=ifSubs", //指定服务类型：是否订阅
				async : true,
				success : function(data) {
					if(data.success=="false" ){
						if(data.type=="hasSubs"){
							//标签变成已订阅，且不可选
							loginSubs.classList.add("hidden");//隐藏订阅
							hasSubs.classList.remove("hidden");//显示已订阅（已禁止button事件）
						}
						if(data.type=="notSubs"){
							//do nothing
							//alert("未订阅");
						}
						if(data.type=="sessionNull"){
							alert("后台会话已关闭，请重新登录");
							
							//完全删除username这个cookie，指定path（在哪获得的，path就在哪）
							Cookies.remove("userName", {path:"/LiveInYouth"});
							
							loginSubs.classList.add("hidden");//隐藏订阅
							registSubs.classList.remove("hidden");//显示点击登录
						}
					}
				}
			});
			
			/*
				已登录时，点击订阅按钮，订阅后显示不可选的已订阅按钮
			*/
			var subsBtn = document.getElementById("subs");
			subsBtn.addEventListener("click", function(event){
				//点击订阅时，dao设置用户标志
				var url = "/LiveInYouth/Subscribe";
				$.ajax({
					cache : false , //开启缓存，下个ajax会从缓存中读数据，除非url重写（cache:false就是给url加上时间戳来不缓存请求的）
					type : "GET", //具有幂等性的GET才能使用cache，POST不符合幂等性，所以cache没有意义
					url : url,
					data : "type=subs", //指定服务类型：订阅
					async : true,
					success : function(data) {
						if(data.success == "true"){
							alert("订阅成功");
							//标签变成已订阅，且不可选 
							loginSubs.classList.add("hidden");//隐藏订阅
							hasSubs.classList.remove("hidden");//显示已订阅（已禁止button事件）
						}else if(data.success == "false"){
							if(data.type == "hasSubs"){
								//标签变成已订阅，且不可选
								loginSubs.classList.add("hidden");//隐藏订阅
								hasSubs.classList.remove("hidden");//显示已订阅（已禁止button事件）
							}
							if(data.type == "updateDao"){
								alert("dao更新失败");
							}
							if(data.type == "selectDao"){
								alert("dao查询失败");
							}
						}else{
							alert("订阅失败");
						}
					}
				});
				
				return false;//禁止type="button"的默认事件，不会进行跳转
			});
		}
	};
	
	
	//call functions
	bottomSubs();
	
});