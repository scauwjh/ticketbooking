<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getServletContext().getContextPath();%>
<%@page import="com.ticketbooking.domain.ticket.Ticket" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/js/customer/map.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/js/customer/function.js"></script>
	<title>电影票预订系统</title>
	<script type="text/javascript">
		$(function(){
			var contextPath = "<%=contextPath%>";
			$("#submit").click(function() {
				var ticketId = $("#ticketId").val();
				var ticketName = $("#ticketName").val();
				var filmType = $("#filmType").val();
				var language = $("#language").val();
				var country = $("#country").val();
				var onTime = $("#onTime").val();
				var ticketPrice = $("#ticketPrice").val();
				var originalPrice = $("#originalPrice").val();
				var ticketIntro = $("#ticketIntro").val();
				$.post(contextPath + "/ticket",
					{
						method : "update",
						ticketId : ticketId,
						ticketName : ticketName,
						filmType : filmType,
						language : language,
						country : country,
						onTime : onTime,
						ticketPrice : ticketPrice,
						originalPrice : originalPrice,
						ticketIntro : ticketIntro,
						prevue : "",
						ticketImg : "",
					},
					function(data) {
						alert(data);
					}
				);// end post
			});// end click
		});
	</script>
</head>
<body>
	<div>发布电影票</div>
	<%Ticket ticket = (Ticket) request.getAttribute("ticket"); %>
	<div>
		<input type="hidden" id="ticketId" value="<%=ticket.getTicketId()%>" />
		名称：<input type="text" id="ticketName" value="<%=ticket.getTicketName()%>" />
	</div>
	
	<div>
		类型：<input type="text" id="filmType" value="<%=ticket.getFilmType()%>" />
	</div>
	
	<div>
		语言：<input type="text" id="language" value="<%=ticket.getLanguage()%>" />
	</div>
	
	<div>
		国家：<input type="text" id="country" value="<%=ticket.getCountry()%>" />
	</div>
	
	<div>
		<%String date = ticket.getOnTime().toString();%>
		<%date = date.substring(0, date.length()-2); %>
		上映时间：<input type="text" id="onTime" value="<%=date%>" />
	</div>
	
	<div>
		原价：<input type="text" id="originalPrice" value="<%=ticket.getOriginalPrice()%>" /><br>
		现价：<input type="text" id="ticketPrice" value="<%=ticket.getTicketPrice()%>" />
	</div>
	
	<div>
		详细说明：<input type="text" id="ticketIntro" value="<%=ticket.getTicketIntro()%>" />
	</div>
	
	<div><button id="submit">发布</button></div>
</body>
</html>