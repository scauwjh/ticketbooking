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
			$("#submit").click(function(){
				var contextPath = "<%=contextPath%>";
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
						method : "save",
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
	<div>
		<input type="hidden" id="ticketId" value="0" />
		名称：<input type="text" id="ticketName" />
	</div>
	
	<div>
		类型：<input type="text" id="filmType" />
	</div>
	
	<div>
		语言：<input type="text" id="language" />
	</div>
	
	<div>
		国家：<input type="text" id="country" />
	</div>
	
	<div>
		上映时间：<input type="text" id="onTime" />
	</div>
	
	<div>
		原价：<input type="text" id="originalPrice" /><br>
		现价：<input type="text" id="ticketPrice" />
	</div>
	
	<div>
		详细说明：<input type="text" id="ticketIntro" />
	</div>
	
	<div><button id="submit">发布</button></div>
</body>
</html>