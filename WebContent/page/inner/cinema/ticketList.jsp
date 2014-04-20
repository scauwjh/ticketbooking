<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ticketbooking.domain.ticket.Ticket" %>
<%@page import="java.util.List" %>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
	<title>电影票预订系统</title>
	<script type="text/javascript">
		$(function(){
			var contextPath = "<%=contextPath%>";
			$("#update").click(function(){
				var ticketId = $(this).prevAll("#ticketId").val();
				top.location.href = contextPath + "/ticket?method=query&ticketId=" + ticketId;
			});
			$("#delete").click(function(){
				var ticketId = $(this).prevAll("#ticketId").val();
				// do some thing
			});
		});
	</script>
	<style type="text/css">
		table, th, tr, td{
			border:1px solid;
			text-align:center;
		}
	</style>
</head>
<body>
	<%List<Ticket> list = (List<Ticket>)request.getAttribute("ticketList");%>
	<table>
		<tr>
			<th width="100px">电影名</th>
			<th width="80px">原价</th>
			<th width="80px">现价</th>
			<th width="200px">类型</th>
			<th width="100px">语言</th>
			<th width="100px">国家</th>
			<th width="180px">上映时间</th>
			<th width="150px">剧照</th>
			<th width="150px">预告片</th>
			<th width="300px">详细说明</th>
			<th width="150px">操作</th>
		</tr>
	<%for (int i = 0; i < list.size(); i++) { %>
		<%Ticket ticket = list.get(i);%>
		<%String date = ticket.getOnTime().toString();%>
		<%date = date.substring(0, date.length()-2); %>
		<tr>
			<td><%=ticket.getTicketName() %></td>
			<td><%=ticket.getOriginalPrice()%></td>
			<td><%=ticket.getTicketPrice() %></td>
			<td><%=ticket.getFilmType()%></td>
			<td><%=ticket.getLanguage()%></td>
			<td><%=ticket.getCountry()%></td>
			<td><%=date%></td>
			<td><%=ticket.getTicketImg()%></td>
			<td><%=ticket.getPrevue()%></td>
			<td><%=ticket.getTicketIntro()%></td>
			<td>
				<input type="hidden" value="<%=ticket.getTicketId()%>" id="ticketId" />
				<a href="javascript:;" id="update">修改</a>&nbsp;&nbsp;<a href="javascript:;" id="delete">删除</a>
			</td>
		</tr>
	<%} %>
	</table>
</body>
</html>