<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ticketbooking.domain.ticket.Ticket" %>
<%@page import="java.util.List" %>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>电影票预订系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/fullcalendar.css" />	
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
		<link rel="stylesheet" href="<%=contextPath%>/css/inner.css"/>
		<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/inner.js"></script>
		<script type="text/javascript">
			$(function(){
				var contextPath = "<%=contextPath%>";
				$(function(){
					$("#logout").click(function(){
						var url = contextPath + "/logout";
						$.post(url, function(data){
							top.location.href = contextPath + data;
						});
					});
					var start = 0;
					var limit = 12;
					getTicket(start, limit);
					
					$(document).on("click", ".update", function(){
						var ticketId = $(this).prevAll(".ticketId").val();
						top.location.href = contextPath + "/inner/cinema/publishticket.jsp?ticketId=" + ticketId;
					});
					
					$(document).on("click", ".delete", function(){
						var ticketId = $(this).prevAll(".ticketId").val();
						// do some thing
					});
					
					function getTicket(start, limit) {
						$.post(contextPath + "/inner/ticket",
							{
								method : "queryList",
								start : start,
								limit : limit
							},
							function(data) {
								var json = eval("(" + data + ")");
								for (var i = 0; i < json.length; i++) {
									var content = (i % 2 == 0 ? '<tr class="tr_class">' : '<tr>')
									+ '<td style="text-align:left">' + json[i].ticketName + '</td>'
									+ '<td>' + json[i].originalPrice + '</td>'
									+ '<td>' + json[i].ticketPrice + '</td>'
									+ '<td>' + json[i].filmType + '</td>'
									+ '<td>' + json[i].language + '</td>'
									+ '<td>' + json[i].country + '</td>'
									+ '<td>' + json[i].onTime + '</td>'
									+ '<td>'
									+ '<input type="hidden" value="'+ json[i].ticketId +'" class="ticketId" />'
									+ '<a href="javascript:;" class="update">修改</a>&nbsp;&nbsp;'
									+ '<a href="javascript:;" class="delete">删除</a>'
									+ '</td>'
									+ '</tr>';
									$("#content_table").append(content);
								}
								
							}
						);
					}
				});
			});
		</script>
	</head>

	<body>
		
		<div id="header">
			<h1>电影票预订系统</h1>		
		</div>
		
		<div id="search">
			<input type="text" placeholder="Search here..." />
			<button type="submit" class="tip-right" title="Search">
				<i class="icon-search icon-white"></i>
			</button>
		</div>

		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse">
                	<a title="" href="javascript:;">
	                	<i class="icon icon-user"></i>
	                	<span class="text">Profile</span>
                	</a>
                </li>
                <li class="btn btn-inverse" id="logout">
               		<a title="" href="javascript:;">
	               		<i class="icon icon-share-alt"></i>
	               		<span class="text">Logout</span>
	               	</a>
                </li>
            </ul>
        </div>
            
		<div id="sidebar">
			<a href="javascript:;" class="visible-phone">
				<i class="icon icon-home"></i>首页
			</a>

			<ul>
				<li>
					<a href="<%=contextPath%>/inner/cinema/index.jsp">
						<i class="icon icon-home"></i>
						<span>首页</span>
					</a>
				</li>
				
				<li>
					<a href="<%=contextPath%>/inner/cinema/publishticket.jsp">
						<i class="icon icon-pencil"></i>
						<span>发布电影票</span>
					</a>
				</li>

				<li  class="active">
					<a href="<%=contextPath%>/inner/cinema/ticketlist.jsp">
						<i class="icon icon-th-list"></i>
						<span>电影票列表</span>
					</a>
				</li>
				
				<li>
					<a href="javascript:;">
						<i class="icon icon-file"></i>
						<span>查看预订情况</span>
					</a>
				</li>

				<li>
					<a href="javascript:;">
						<i class="icon icon-inbox"></i>
						<span>其他</span>
					</a>
				</li>
			</ul>
		
		</div>
		
		<div id="style-switcher">
			<i class="icon-arrow-left icon-white"></i>
			<div>
				<span>Style:</span>
				<a href="#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
				<a href="#blue" style="background-color: #2D2F57;"></a>
				<a href="#red" style="background-color: #673232;"></a>
			</div>
		</div>
		

		<div id="content">
			<div id="content-header">
				<h1>电影票预订</h1>
			</div>
			<div id="breadcrumb">
				<a href="javascript:;" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a>
				<a href="javascript:;" title="Ticket list" class="tip-bottom">电影票列表</a>
			</div>
			<div class="container-fluid">
				<!-- main container -->
				<div class="main-content">
					<table id="content_table">
						<thead><tr>
							<th width="100px" style="text-align:left">电影名</th>
							<th width="80px">原价</th>
							<th width="80px">现价</th>
							<th width="200px">类型</th>
							<th width="100px">语言</th>
							<th width="100px">国家</th>
							<th width="180px">上映时间</th>
							<th width="80px">操作</th>
						</tr></thead>
					</table>
				</div>
				<div class="row-fluid">
					<div id="footer" class="span12">
						&copy;2014. <a href="http://scauwjh.github.com/blog">wjh</a>
					</div>
				</div>
			</div>
		</div>
		
		<script src="js/excanvas.min.js"></script>
		<script src="js/jquery.ui.custom.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.peity.min.js"></script>
		<script src="js/fullcalendar.min.js"></script>
		<script src="js/unicorn.js"></script>
	</body>
</html>
