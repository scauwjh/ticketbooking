<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/js/system/MD5.js"></script>
	<title>电影票预订系统</title>
	<script type="text/javascript">
		$(function(){
			/*
			 * ajax login
			 */
			var contextPath = "<%=contextPath%>";
			function login(){
				var password = $("#password").val();
				var user = $("#user").val();
				if (password == "" || user == "") {
					alert("帐号密码不能为空");
					return;
				}
				password = hex_md5(password);
				// post data to login
				// .....
				$.ajax({
					url : contextPath + "/login",
					data : {
						user : user,
						password : password,
					},
					cache : false,
					success : function(data){
						if (data == -1)
							alert("帐号或者密码错误");
						else top.location.href = contextPath + data;
						hideLoading();
					},
					error : function() {
						alert("连接失败");
						hideLoading();
					}
				});
			}
			$("#login").click(function(){
				login();
			});
			$("#registe").click(function(){
				top.location.href = contextPath + "/registe.jsp";
			});
			
			$(document).keypress(function(e){
				if (e.keyCode == 13) {
					login();
				}
			});
			function showLoading(){
				$("#loading").show();
			}
			function hideLoading(){
				$("#loading").hide();
			}
			hideLoading();
		});
	</script>
</head>
<body>
	<div>
		<input type="text" name="user" id="user" value=""/>
		<input type="password" name="password" id="password" value=""/>
		<button id="login">登录</button>
		<button id="registe">注册</button>
	</div>
	<div id="loading">
		<img src="<%=contextPath%>/image/public/loading.gif" alt="">
	</div>
</body>
</html>