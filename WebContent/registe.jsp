<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/unicorn.login.css" />
        <link rel="stylesheet" href="<%=contextPath%>/css/inner.css" />
        <script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/js/system/MD5.js"></script>
        <title>电影票预订系统</title>
	<script type="text/javascript">
		var contextPath = "<%=contextPath%>";
		$(function(){
			var userError = false;
			var passError = false;
			$("#userId").change(function(){
				var userId = $("#userId").val();
				if (userId.length < 3) {
					$("#password").nextAll(".error").remove();
					var tmp = '<font class="error">帐号长度不能小于3</font>';
					$("#userId").next("b").after(tmp);
					userError = true;
					return;
				}
				$.post(contextPath + "/registe",
					{
						userId : userId,
						method : "checkId"
					},
					function(data){
						if (data != 1) {
							var add = '<font class="error">已存在该帐号！</font>';
							$("#userId").next("b").after(add);
							userError = true;
							return;
						}
						userError = false;
					}
				);
			});
			$("#userId, #password_confirm, #password").keydown(function(){
				$(this).nextAll(".error").remove();
			});
			
			$("#password").change(function(){
				var password = $("#password").val();
				if (password.length < 6) {
					$("#password").nextAll(".error").remove();
					var tmp = '<font class="error">密码长度不能小于6</font>';
					$("#password").next("b").after(tmp);
					passError = true;
					return;
				}
				passError = false;
			});
			
			$("#password_confirm").change(function(){
				var password = $("#password").val();
				var confirm = $("#password_confirm").val();
				if (password != confirm) {
					var add = '<font class="error">两次密码不一样</font>';
					$("#password_confirm").next("b").after(add);
					passError = true;
					return;
				}
				passError = false;
			});
			$("#submit").click(function(){
				registe();
			});
			
			$("#cancel").click(function(){
				top.location.href = contextPath + "/login.jsp";
			});
			
			$(document).keypress(function(e){
				if (e.keyCode == 13) {
					registe();
				}
			});
			
			function registe() {
				if (passError || userError)
					return;
				var userId = $("#userId").val();
				var password = $("#password").val();
				var confirm = $("#password_confirm").val();
				var add = '<font class="error">不能为空</font>';
				if (userId == "") {
					$("#userId").nextAll(".error").remove();
					$("#userId").next("b").after(add);
					return;
				}
				if (password == "") {
					$("#password").nextAll(".error").remove();
					$("#password").next("b").after(add);
					return;
				}
				if (confirm == "") {
					$("#password_confirm").nextAll(".error").remove();
					$("#password_confirm").next("b").after(add);
					return;
				}
				var name = $("#name").val();
				var telephone = $("#telephone").val();
				var address = $("#address").val();
				var IDCard = $("#IDCard").val();
				var otherCard = $("#otherCard").val();
				name = name == "" ? 0 : name;
				telephone = telephone == "" ? 0 : telephone;
				address = address == "" ? 0 : address;
				IDCard = IDCard == "" ? 0 : IDCard;
				otherCard = otherCard == "" ? 0 : otherCard;
				// alert(name + "," + telephone + "," + address + "," + IDCard + "," + otherCard);
				$.post(contextPath + "/registe",
					{
						method : "registe",
						userId : userId,
						password : hex_md5(password),
						name : name,
						telephone : telephone,
						address : address,
						IDCard : IDCard,
						otherCard : otherCard
					},
					function(data) {
						if (data == 1) {
							alert("注册成功!");
							top.location.href = contextPath + "/login.jsp";
						}
					}
				);
			}
		});
	</script>
	<style type="text/css">
		#main{
			margin-top:40px;
			margin-left:30px;
		}
		#main div{
			width:450px;
			display:block;
			text-align:left;
			margin-top:10px;
			margin-left:10px;
		}
		span{
			width:100px;
			display:block;
			float:left;
			text-align:right;
		}
		b{
			color:red;
		}
		.error{
			color:red;
			font-size:12px;
		}
		#loading{
			display:none;
		}
		#loginbox{
			height:500px;
			width:500px;
		}
		.operation{
			margin-left:140px;
		}
	</style>
</head>
<body>
	<div id="logo">
        <img src="<%=contextPath%>/bootstrap/img/logo.png" alt="" />
    </div>
    <div id="loginbox">
	    <div class="control-group">
	        <div id="main">
				<div>
					<span>帐号：</span><input type="text" id="userId" value=""/><b> * </b>
				</div>
				<div>
					<span>密码：</span><input type="password" id="password" value=""/><b> * </b>
				</div>
				<div>
					<span>确认密码：</span><input type="password" id="password_confirm" value=""/><b> * </b>
				</div>
				<div>
					<span>姓名：</span><input type="text" id="name" value=""/>
				</div>
				<div>
					<span>电话：</span><input type="text" id="telephone" value=""/>
				</div>
				<div>
					<span>地址：</span><input type="text" id="address" value=""/>
				</div>
				<div>
					<span>身份证：</span><input type="text" id="IDCard" value=""/>
				</div>
				<div>
					<span>其他证件：</span><input type="text" id="otherCard" value=""/>
				</div>
				<div>
					<button id="submit" class="btn btn-inverse operation">注册</button>
					<button id="cancel" class="btn btn-inverse">返回</button>
				</div>
			</div>
		</div>
     </div>
     
	
	<div id="loading">
		<div>
			<img src="<%=contextPath%>/image/public/loading.gif">
		</div>
	</div>
	
	<script src="<%=contextPath%>/bootstrap/js/jquery.min.js"></script>  
    <script src="<%=contextPath%>/bootstrap/js/unicorn.login.js"></script> 
</body>
</html>