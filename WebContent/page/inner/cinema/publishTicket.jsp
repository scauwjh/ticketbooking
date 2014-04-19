<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
			var start = 0;
			var limit = 10;
			$.post(contextPath + "/ticket",
				{
					method : "getTicketList",
					start : start,
					limit : limit
				},
				function(data) {
					alert(data);
				}
			);
		});
	</script>
</head>
<body>
	<div>发布电影</div>
	<div>
		<input type="hidden" id="ticketId" />
		名称：<input type="text" id="ticketName" />
	</div>
	
	<div>
		类型：<input type="text" id="ticketName" />
	</div>
	
	<div>
		语言：<input type="text" id="ticketName" />
	</div>
	
	<div>
		上映时间：<input type="text" id="ticketName" />
	</div>
	
	<div>
		原价：<input type="text" id="ticketName" />
		现价：<input type="text" id="ticketName" />
	</div>
	
	<div>
		原价：<input type="text" id="ticketName" />
	</div>
</body>
</html>