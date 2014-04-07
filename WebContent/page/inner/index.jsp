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
			$("#logout").click(function(){
				var url = contextPath + "/logout";
				$.post(url, function(data){
					top.location.href = data;
				});
			});
		});
	</script>
</head>
<body>
	<div>index page</div>
	<div>
		<button id="logout">登出</button>
	</div>
</body>
</html>