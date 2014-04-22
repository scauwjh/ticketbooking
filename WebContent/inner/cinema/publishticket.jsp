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
				var ticketImg = $("#ticketImg").val();
				$.post(contextPath + "/inner/ticket",
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
						ticketImg : ticketImg,
					},
					function(data) {
						alert(data);
					}
				);// end post
			});// end click
		});
	</script>
	<style type="text/css">
		#ticketImg{
			width:300px;
		}
		label{
			width:80px;
			display:block;
			float:left;
		}
		#ticketIntro{
			width:300px;
			height:100px;
		}
	</style>
</head>
<body>
	<div>
		<div>
			<input type="hidden" id="ticketId" value="0" />
			<label>名称：</label><input type="text" id="ticketName" />
		</div>
		
		<div>
			<label>类型：</label><input type="text" id="filmType" />
		</div>
		
		<div>
			<label>语言：</label><input type="text" id="language" />
		</div>
		
		<div>
			<label>国家：</label><input type="text" id="country" />
		</div>
		
		<div>
			<label>原价：</label><input type="text" id="originalPrice" /><br>
			<label>现价：</label><input type="text" id="ticketPrice" />
		</div>
		
		<div>
			<label>上映时间：</label><input type="text" id="onTime" />
		</div>
		
		<div>
			<label>详细说明：</label><textarea id="ticketIntro" ></textarea>
		</div>
		
		<div>
			<label>剧照：</label><input type="text" id="ticketImg"/>
			<div style="height:200px;width:200px;margin-left:80px;" id="tmp_picDiv">
				<img id="refImg" width="200px" height="200px" onerror="javascript:this.src='<%=contextPath%>/image/public/nopic.jpg'"/>
			</div>
			<!-- 上传图片的form -->
			<script type="text/javascript" src="<%=contextPath%>/js/customer/ajaxfileupload.js"></script>
			<script type="text/javascript" src="<%=contextPath%>/js/customer/pictureUpload.js"></script>
			<script type="text/javascript">
				/* code by @wjh */
				$(document).ready(function(){
					/**
					 * 参数：
					 * 1.img标签的id 
					 * 2.显示图片url的input（或其他标签）的id
					 * 3.img标签父元素（div）的id
					 * 4.url是上传图片的action
					 */
					var contextPath = "<%=contextPath%>";
					var url = contextPath + '/admin/upload';
					pictureUpload("refImg", "ticketImg", "tmp_picDiv", url);
				});
				/* end code by */
			</script>
		</div>
		
		<div style="margin-left:80px;margin-top:20px"><button id="submit">发布</button></div>
	</div>
</body>
</html>