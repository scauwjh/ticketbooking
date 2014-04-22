<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ticketbooking.domain.ticket.Ticket" %>
<%@page import="java.util.List" %>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>电影票预定系统</title>
		<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/style.css" />
		<script src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var contextPath = "<%=contextPath%>";
				$(".details").click(function(){
					var ticketId = $(this).prevAll(".ticketId").val();
					$.post(contextPath + "/outward/ticket",
						{
							method : "query",
							ticketId : ticketId,
						},
						function(data) {
							// alert(data);
							var obj = eval("(" + data +")");
							var shade = '<div class="full-screen-shade">';
							shade += '</div>';
							$("body").append(shade);
							var box = '<div class="details-box">';
							box += '<div class="details-center-box">';
							box += '<p>片名：'+ obj.ticketName +'</p>';
							box += '<p>语言：'+ obj.language +'</p>';
							box += '<p>类型：'+ obj.filmType +'</p>';
							box += '<p>国家：'+ obj.country +'</p>';
							box += '<p>原价：'+ obj.originalPrice +'</p>';
							box += '<p>现价：'+ obj.ticketPrice +'</p>';
							box += '<p>上映时间：'+ obj.onTime.substring(0, obj.onTime.length - 2) +'</p>';
							box += '<p>简单介绍：'+ obj.ticketIntro +'</p>';
							box += '<div class="close"></div>';
							box += '<div class="details-img"><img src=" '+ obj.ticketImg +'" onerror="javascript:this.src=\''+ contextPath +'/image/public/nopic.jpg\'" /></div>';
							box += '</div>';
							box += '</div>';
							$("body").append(box);
						}
					);
					
					$(".details").click(function(){
						alert("买你妹");
					});
					
					$(document).on("click", ".close", function(){
						$(".details-box").remove();
						$(".full-screen-shade").remove();
					});
				});
			});
		</script>
		<style type="text/css">
			#main{
				min-height:950px;
			}
			#main_box table, th, tr, td{
				border:1px solid;
				text-align:center;
			}
			#main_box img{
				 width:200px;
				 height:200px;
			}
			#main_box p{
				font-size:13px;
				line-height:15px;
				color:#333;
				margin-bottom:5px;
			}
			#main_box{
				width:900px;
				margin-left:-135px;
			}
			#main_box div{
				margin-right:20px;
				margin-bottom:30px;
			}
			#main_box .ticket{
				float:left;
				width:200px;
				height:260px;
			}
			#main_box .ticket-desc{
				width:250px;
			}
			.details-box{
				position:fixed;
				top:35%;
				width:100%;
				height:300px;
				z-index:2014;
			}
			.details-center-box{
				margin-right:auto;
				margin-left:auto;
				z-index:2015;
				width:500px;
				height:300px;
				background:#fff;
				border-radius:5px;
				border:1px solid #ccc;
			}
			.details-box p{
				margin-top:20px;
				margin-left:20px;
				font-size:18px;
				line-height:10px;
				color:#333;
			}
			.details-box .close{
				position:relative;
				top:-280px;
				left:460px;
				z-index:2016;
				width:30px;
				height:28px;
				background:url(/ticketbooking/image/public/back.png);
			}
			.details-img{
				position:relative;
				top:-230px;
				left:310px;
				z-index:2016;
				width:150px;
				height:150px;
			}
			.details-img img{
				width:150px;
				height:150px;
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
						<a href="javascript:;">wjh</a>
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
							<a href="javascript:;">首页</a>
						</li>
						<li>
							<a href="javascript:;">最新电影</a>
						</li>
						<li>
							<a href="javascript:;">火热排行榜</a>
						</li>
						<li>
							<a href="javascript:;">在线影院</a>
						</li>
						<li>
							<a href="javascript:;">河蟹天堂</a>
						</li>
						<li>
							<a href="javascript:;">抢票活动</a>
						</li>
						<li>
							<a href="javascript:;">留言天地</a>
						</li>
						<li class="last">
							<a href="javascript:;">联系我们</a>
						</li>
					</ul><br class="clear" />
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
				<%List<Ticket> list = (List<Ticket>) request.getAttribute("ticketList"); %>
				<%if (list != null) { %>
					<%for (int i = 0; i < list.size(); i++) { %>
						<div class="ticket">
							<img src="<%=list.get(i).getTicketImg()%>" onerror="javascript:this.src='<%=contextPath%>/image/public/nopic.jpg'"/>
							<div class="ticket-desc">
								<div style="float:left">
									<p>片名：<%=list.get(i).getTicketName() %></p>
									<p>原价：<%=list.get(i).getOriginalPrice() %></p>
									<p>现价：<%=list.get(i).getTicketPrice() %></p>
								</div>
								<div style="float:left;margin-left:18px;margin-top:28px;">
									<input type="hidden" class="ticketId" value="<%=list.get(i).getTicketId()%>">
									<button class="details">详细</button>
									<button class="purchase">购买</button>
								</div>
							</div>
						</div>
					<%} %>
				<%} %>
					</div>
				</div>
			</div>
		</div>
		<div id="copyright">
			&copy; 2014. By @wjh&nbsp;&nbsp;website:<a href="http://scauwjh.github.com/blog/">github pages</a>
		</div>
	</body>
</html>