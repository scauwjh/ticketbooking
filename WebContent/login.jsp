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
				var verifyCode = $("#verifycode").val();
				if (password == "" || user == "" || verifyCode == "") {
					alert("帐号密码不能为空");
					return;
				}
				password = hex_md5(password);
				// post data to login
				// .....
			}
			$("#submit").click(function(){
				login();
			});
			$(document).keypress(function(e){
				if (e.keyCode == 13) {
					login();
				}
			});
		});
	</script>
</head>
<body>
	<div>
		<input type="text" name="user" id="user" value=""/>
		<input type="password" name="password" id="password" value=""/>
		<input type="text" name="verifycode" id="verifycode" value=""/>
		<img src="" id="verifycode_img" />(还没写逻辑，登不了也没有验证码)
		<button id="submit">登录</button>
	</div>
</body>
</html>