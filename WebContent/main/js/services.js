/*
 * LiveInYouth services module js
 * author: ixenos
 * email:ixenos@foxmail.com
 * create date:2017-5-15
 * last modify date:2017-5-15
 * fixed 2017-5-19:砍掉订阅管理
 * 
 */
$(function () {
	
	//初始数据加载
	var initData = function(){
		//listName//listImg//listIntro//userName//userImg//userSubs
		//直接给list和user就好
		$.ajax({
			cache : false , //具有幂等性的GET才能使用cache，POST不符合幂等性，所以cache没有意义
			type : "GET",
			url : "/LiveInYouth/Services",
			data : "type=initData", //指定服务类型
			async : true,
			success : function(data){
			if(data.success == "true"){
				$(".listName").attr("value", data.songList.listName);
				$(".listCover").attr("src", data.songList.songListImgSrc);
				$(".listIntro").attr("value", data.songList.listIntro);
				
				$(".userName").attr("value", data.user.userName);
				$(".userAvatar").attr("src", data.user.avatarSrc);
				
//				if("0" == data.user.subsFlag){
//					var cancelSubBtn = document.getElementById("cancelSubBtn");
//					var subsBtn = document.getElementById("subsBtn");
//					cancelSubBtn.classList.add("hidden");
//					subsBtn.classList.remove("hidden");
//				}
				
			} else if(data.success == "false"){
//				if("0" == data.user.subsFlag){
//					var cancelSubBtn = document.getElementById("cancelSubBtn");
//					var subsBtn = document.getElementById("subsBtn");
//					cancelSubBtn.classList.add("hidden");
//					subsBtn.classList.remove("hidden");
//				}else if("1" == data.user.subsFlag){
//					var cancelSubBtn = document.getElementById("cancelSubBtn");
//					var subsBtn = document.getElementById("subsBtn");
//					cancelSubBtn.classList.remove("hidden");
//					subsBtn.classList.add("hidden");
//				}
				//新用户，还未有列表信息、头像，此时需要展示
				var modifyList = document.getElementById("modifyList");
				var createList = document.getElementById("createList");
				var modifyData = document.getElementById("modifyData");
				var createData = document.getElementById("createData");
				modifyList.classList.add("hidden");
				createList.classList.remove("hidden");
				modifyData.classList.add("hidden");
				createData.classList.remove("hidden");
			}
			}
		});
	};
	
	
	
	//登录判断，决定显示范围
	var loginShow = function() {
		/*
			检测是否已登陆
		*/
		var loginFlag = Cookies.get("userName");
		if(loginFlag == undefined){
			/*
				未登录时，隐藏功能模块
			*/
			var serSec = document.getElementById("fh5co-services-section");
			serSec.classList.add("hidden");
			console.log("未登录，功能模块隐藏");
		} else{
			initData();//初始化数据
		}	
	};
	
	
	
	//退出登录按钮
	var cancelLogin = function() {
		var cancelBtn = document.getElementById("cancelLoginBtn");
		cancelBtn.addEventListener("click", function(event){
			if(window.confirm("是否退出登录？")){ //可选择的alert
				//先删除前端的cookie信息
				Cookies.remove("userName", {path:"/LiveInYouth"});//完全删除username这个cookie，指定path（在哪获得的，path就在哪）
				//然后再调用后端的退出登录接口
				$.get("/LiveInYouth/Services", { type:"cancelLogin"});
				
				if(window.confirm("退出登录成功，是否跳转登录页面？")){ //可选择的alert
					window.location = "/LiveInYouth";
				}else{
					//重新加载当前页面，以刷新未登录的页面展示
					location.reload();
				}
			}else{
				event.preventDefault();//禁止click事件默认行为
				window.location = "/LiveInYouth/main/services.html#still"
			}
		});
	};
	
	
	/*
	 * 修改歌单模块
	 */
	var modListDoneEvent = function () {
         $("#modListDone").click(function () {
        	 /*
              * 文字表单模块
              */
             var subListName = $("#modingListName").val();
     		 var subListIntro = $("#modingListIntro").val();
             if(subListName == ""){
            	alert("请输入歌单名");
            	$("#imgWait").hide();
            	return;
             }
             if(subListIntro == ""){
            	alert("请输入歌单简介");
            	$("#imgWait").hide();
            	return;
             }
     		 var serUrl = "/LiveInYouth/Services?subListName="+subListName+"&subListIntro="+subListIntro;
     		 $.ajax({
                url: serUrl,
                type: "GET",
                data: "type=modList",
                contentType: false,
                success: function (data) {
                	if(data.success == "true"){
                		console.log("歌单表单保存成功");
                	}
                	if(data.success == "false"){
                		console.log("歌单表单保存失败");
                	}
                }
     		 });
     		 
     		 /*
     		  * 图片上传模块
     		  */
             $("#imgWait").show();
             var formData = new FormData();
             var file = document.getElementById("listCoverFile").files[0];
             if(file != undefined){
	             formData.append("myfile", document.getElementById("listCoverFile").files[0]);
	             var uploadUrl = "/LiveInYouth/Upload?type=listCover";
	             $.ajax({
	                 url: uploadUrl,
	                 type: "POST",
	                 data: formData,
	                 /**
	                 *必须false才会自动加上正确的Content-Type
	                 */
	                 contentType: false,
	                 /**
	                 * 必须false才会避开jQuery对 formdata 的默认处理
	                 * XMLHttpRequest会对 formdata 进行正确的处理
	                 */
	                 processData: false,
	                 success: function (data) {
	                     if (data.success == "true") {
	                    	 console.log("图片上传成功！");
	                     }
	                     if (data.success == "false") {
	                    	 console.log("图片上传失败！");
	                     }
	                     $("#imgWait").hide();
	                 }
	             });
             }
             
             
             /*
              * 歌曲修改模块
              * 自己做独立事件处理
              * 这里可以不包含在一起
              */
             
             
             /*
              * 完成后，关闭popup
              */
             $("#imgWait").hide();
             $.magnificPopup.close();
             alert("歌单信息修改成功!");
         
         });
     };

     
     /*
      * 上传歌曲到歌单
      */
     var modSongDoneEvent = function(){
    	 $("#modSongDone").click(function () {
        	 /*
              * 文字表单模块
              */
             var subSongName = $("#modingSongName").val();
     		 var subArtistName = $("#modingArtistName").val();
     		 var subAlbumName = $("#modingAlbumName").val();
     		 
             if(subSongName == ""){
            	alert("请输入歌名");
            	$("#imgWait").hide();
            	return;
             }
             if(subArtistName == ""){
            	alert("请输入歌手");
            	$("#imgWait").hide();
            	return;
             }
             if(subAlbumName == ""){
             	alert("请输入专辑");
             	$("#imgWait").hide();
             	return;
              }
             
     		 var serUrl = "/LiveInYouth/Services?subSongName="+subSongName+"&subArtistName="+subArtistName+"&subAlbumName="+subAlbumName;
     		 $.ajax({
                url: serUrl,
                type: "GET",
                data: "type=modListSong",
                contentType: false,
                success: function (data) {
                	if(data.success == "true"){
                		console.log("歌单歌曲表单保存成功");
                	}
                	if(data.success == "false"){
                		console.log("歌单歌曲表单保存失败");
                	}
                }
     		 });
    		 
      		 /*
      		  * 音频上传模块
      		  */
              $("#imgWait3").show();
              var formData = new FormData();
              var file = document.getElementById("songFile").files[0];
              if(file != undefined){
 	             formData.append("myfile", document.getElementById("songFile").files[0]);
 	             var uploadUrl = "/LiveInYouth/Upload?type=song";
 	             $.ajax({
 	                 url: uploadUrl,
 	                 type: "POST",
 	                 data: formData,
 	                 /**
 	                 *必须false才会自动加上正确的Content-Type
 	                 */
 	                 contentType: false,
 	                 /**
 	                 * 必须false才会避开jQuery对 formdata 的默认处理
 	                 * XMLHttpRequest会对 formdata 进行正确的处理
 	                 */
 	                 processData: false,
 	                 success: function (data) {
 	                     if (data.success == "true") {
 	                    	console.log("音频上传成功！");
 	                     }
 	                     if (data.success == "false") {
 	                    	console.log("音频上传失败~");
 	                     }
 	                     $("#imgWait3").hide();
 	                 }
 	             });
              }
              /*
               * 完成后，关闭popup
               */
              $("#imgWait3").hide();
              $.magnificPopup.close();
              alert("歌单歌曲修改成功!");
          });
     };
     
     
     
     
 	/*
 	 * 修改资料模块
 	 */
 	var modUserDoneEvent = function () {
          $("#modUserDone").click(function () {
         	 /*
              * 文字表单模块
              */
             var subUserName = $("#modingUserName").val();
              if(subUserName == ""){
             	alert("请输入歌单名");
             	$("#imgWait2").hide();
             	return;
              }
      		 var serUrl = "/LiveInYouth/Services?subUserName="+subUserName;
      		 $.ajax({
                 url: serUrl,
                 type: "GET",
                 data: "type=modUser",
                 contentType: false,
                 success: function (data) {
                 	if(data.success == "true"){
                 		console.log("用户表单保存成功");
                 	}
                 	if(data.success == "false"){
                 		console.log("用户表单保存失败");
                 	}
                 }
      		 });
      		 
      		 /*
      		  * 图片上传模块
      		  */
              $("#imgWait2").show();
              var formData = new FormData();
              var file = document.getElementById("avatarFile").files[0];
              if(file != undefined){
 	             formData.append("myfile", document.getElementById("avatarFile").files[0]);
 	             var uploadUrl = "/LiveInYouth/Upload?type=avatar";
 	             $.ajax({
 	                 url: uploadUrl,
 	                 type: "POST",
 	                 data: formData,
 	                 /**
 	                 *必须false才会自动加上正确的Content-Type
 	                 */
 	                 contentType: false,
 	                 /**
 	                 * 必须false才会避开jQuery对 formdata 的默认处理
 	                 * XMLHttpRequest会对 formdata 进行正确的处理
 	                 */
 	                 processData: false,
 	                 success: function (data) {
 	                     if (data.success == "true") {
 	                    	console.log("图片上传成功！");
 	                     }
 	                     if (data.success == "false") {
 	                    	console.log("图片上传失败~");
 	                     }
 	                     $("#imgWait2").hide();
 	                 }
 	             });
              }
              
              
//              /*
//               * 订阅模块
//               * 自己做独立事件处理
//               * 这里可以不包含在一起，只是作为一个隐藏的退订入口
//               */
//              
              
              /*
               * 完成后，关闭popup
               */
              $("#imgWait2").hide();
              $.magnificPopup.close();
              alert("用户信息修改成功!");
              
          });
      };
    
//    /*
//     * 订阅和退订的事件绑定
//     */
//    var modSubsBtn = function(){
//    	
//    };
     
    
    
	//call the function 
	loginShow();
	cancelLogin();
	modListDoneEvent();
	modSongDoneEvent();
	modUserDoneEvent();
	
	
});