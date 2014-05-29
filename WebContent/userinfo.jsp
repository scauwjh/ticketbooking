<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ticketbooking.domain.ticket.Ticket" %>
<%@page import="java.util.List" %>
<%String contextPath = request.getServletContext().getContextPath();%>
<%Long userId = (Long) request.getSession().getAttribute("userId");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>电影票预定系统</title>
		<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/inner.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/index.css" />
		<script src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		var contextPath = "<%=contextPath%>";
		var userId = <%=userId%>;
		</script>
		<script>
			$(function(){
				var isLogin = false;
				var user = null;
				function checkLogin() {
					$.ajaxSettings.async = false;
					$.post(contextPath + "/login", 
						{
							method : "check",
						},
						function(data) {
							if(data != 0) {
								isLogin = true;
								user = data;
							}
						}
					);
					$.ajaxSettings.async = true;
				}
				
				function loginMessage() {
					if (isLogin) {
						$("#to_login").css("display", "none");
						$("#login_box").css("display", "block");
						var tmp = user + "&nbsp;";
						$("#userName").html(tmp);
					}
				}
				
				// 检查是否登录
				checkLogin();
				loginMessage();
				
				if (!isLogin) 
					$("#main_box").append('<b style="font-size:25px;margin-left:-100px;color:red;">请先登录<b>');
				else {
					$.post(contextPath + "/inner/userinfo",
						{
							method : "userInfo",
							userId : <%=userId%>
						},
						function(data) {
							var info = eval("(" + data + ")");
							//alert(data);
							$("#userInfo").css("display", "block");
							$("#name .content").text(info.name);
							$("#telephone .content").text(info.telephone);
							$("#IDCard .content").text(info.IDCard);
							$("#otherCard .content").text(info.otherCard);
							$("#address .content").text(info.address);
						}
					);
				}
				
				$("#logout").click(function(){
					var url = contextPath + "/logout";
					$.post(url, function(data){
						window.location.reload();
					});
				});
			});
		</script>
		<style type="text/css">
			#userInfo{
				display:none;
			}
			.info-title{
				font-size:30px;
				font-weight:900;
			}
		</style>
	</head>
	<body>
		<div id="bg1"></div>
		<div id="bg2"></div>
		<div id="outer">
			<div id="header">
				<div id="logo">
					<h1>
						<a href="javascript:;">个人信息</a>
					</h1>
				</div>
				<div id="search">
					<form action="" method="post">
						<div>
							<input class="text" name="search" size="32" maxlength="64" value="查找电影" />
						</div>
					</form>
				</div>
				<div id="nav">
					<ul>
						<li class="first">
							<a href="index.jsp">最新电影</a>
						</li>
						<li>
							<a href="ticketrecord.jsp">订购记录</a>
						</li>
						<li>
							<a href="javascript:;">个人信息</a>
						</li>
						<li>
							<a href="javascript:;">在线影院</a>
						</li>
						<li>
							<a href="javascript:;">河蟹天堂</a>
						</li>
						<li>
							<a href="javascript:;">留言天地</a>
						</li>
						<li class="last">
							<a href="javascript:;">联系我们</a>
						</li>
					</ul><br class="clear" />
					<div id="login_box">
						<span id="userName"></span> 
						<span>欢迎登录</span> |
						<a href="javascript:;" id="logout"> 退出</a> 
					</div>
					<div id="to_login"> 
						<a href="<%=contextPath%>/login.jsp">登录 </a>|
						<a href="<%=contextPath%>/registe.jsp">注册</a>
					</div>
				</div>
			</div>
			<div id="banner">
				<div class="captions">
					<h2>复仇者联盟2014正在热播中，赶快拿起你的电话，拨打99199880088订票吧！</h2>
				</div>
				<img src="<%=contextPath%>/image/banner.jpg" alt="" />
			</div>
			<div id="main">
				<div id="content">
					<div id="main_box">
						<div id="userInfo">
							<div>
								<span class="info-title">个人信息</span>
							</div>
							<div id="name">
								<span class="title">姓名：</span><span class="content"></span>
							</div>
							<div id="telephone">
								<span class="title">电话：</span><span class="content"></span>
							</div>
							<div id="IDCard">
								<span class="title">身份证：</span><span class="content"></span>
							</div>
							<div id="otherCard">
								<span class="title">其他证件：</span><span class="content"></span>
							</div>
							<div id="address">
								<span class="title">联系地址：</span><span class="content"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="copyright">
			&copy; 2014. By @wjh&nbsp;&nbsp;website:<a href="http://scauwjh.github.com/blog/">github pages</a>
		</div>
	</body>
</html>