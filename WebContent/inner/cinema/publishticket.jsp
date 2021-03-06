<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="<%=contextPath%>/css/jQueryUI-1.10.4.css"/>
		<link rel="stylesheet" href="<%=contextPath%>/css/jquery-ui-timepicker-addon.css"/>
		<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/system/jQueryUI-1.10.4.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/system/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/inner.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/function.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/map.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/customer/pictureUpload.js"></script>
		<script type="text/javascript">
			$(function(){
				var contextPath = "<%=contextPath%>";
				$("#onTime").datetimepicker({
					timeText: '时间',
					hourText: '小时',
					minuteText: '分钟',
					secondText: '秒',
					currentText: '现在',
					closeText: '完成',
					showSecond: false, //显示秒
					timeFormat: 'hh:mm:ss'//格式化时间
				});
				$(function(){
					$("#logout").click(function(){
						var url = contextPath + "/logout";
						$.post(url, function(data){
							top.location.href = contextPath + data;
						});
					});
				});
				$("#submit").click(function(){
					var ticketId = $("#ticketId").val();
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
							method : "saveOrUpdaet",
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
							ticketImg : ticketImg,
						},
						function(data) {
							alert(data);
						}
					);// end post
				});// end click
				var map = getParameters();
				if (map != null) {
					var ticketId = map.get("ticketId");
					$.post(contextPath + "/inner/ticket",
						{
							method : "query",
							ticketId : ticketId
						},
						function(data){
							//alert(data);
							var json = eval("(" + data + ")");
							$("#ticketId").val(json.ticketId);
							$("#ticketName").val(json.ticketName);
							$("#filmType").val(json.filmType);
							$("#language").val(json.language);
							$("#country").val(json.country);
							$("#onTime").val(json.onTime);
							$("#ticketPrice").val(json.ticketPrice);
							$("#originalPrice").val(json.originalPrice);
							$("#ticketIntro").val(json.ticketIntro);
							$("#ticketImg").val(json.ticketImg);
							$("#refImg").attr("src", json.ticketImg);
						}
					);
				}
				$("#prevue").click(function(){
					$("#prevue_input").click();
				});
				var upload = function(){
					var url = contextPath + '/admin/upload?fileType=1';
					var showUrlId = "prevue";
					var elemntId = "prevue_input";
					ajaxUpload(null, showUrlId, url, elemntId);
					$("#prevue_input").change(upload);
				};
				$("#prevue_input").change(upload);
				
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
			<a href="index.jsp" class="visible-phone">
				<i class="icon icon-home"></i>首页
			</a>

			<ul>
				<li>
					<a href="index.jsp">
						<i class="icon icon-home"></i>
						<span>首页</span>
					</a>
				</li>
				
				<li class="active">
					<a href="publishticket.jsp">
						<i class="icon icon-pencil"></i>
						<span>发布电影票</span>
					</a>
				</li>

				<li>
					<a href="ticketlist.jsp">
						<i class="icon icon-th-list"></i>
						<span>电影票列表</span>
					</a>
				</li>
				
				<li>
					<a href="handlebooking.jsp">
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
				<h1>发布电影票</h1>
			</div>
			<div id="breadcrumb">
				<a href="index.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a>
				<a href="publishticket.jsp" title="publish ticket" class="tip-bottom">发布电影</a>
			</div>
			<div class="container-fluid">
				<!-- main container -->
				<div class="main-content">
					<div class="publish-box">
						<div>
							<input type="hidden" id="ticketId" value="0" />
							<label>名称：</label><input type="text" id="ticketName" />
						</div>
						
						<div><label>类型：</label><input type="text" id="filmType" /></div>
						
						<div><label>语言：</label><input type="text" id="language" /></div>
						
						<div><label>国家：</label><input type="text" id="country" /></div>
						
						<div><label>原价：</label><input type="text" id="originalPrice" class="number" /></div>
						
						<div><label>现价：</label><input type="text" id="ticketPrice" class="number" /></div>
						
						<div><label>上映时间：</label><input type="text" id="onTime"/></div>
						
						<div style="width:100px;"></div>
						
						<div><label>详细说明：</label><textarea id="ticketIntro" ></textarea></div>
						
						<div>
							<form id="prevue_form" method="post" enctype="multipart/form-data">
								<label>预告片：</label> <input type="text" id="prevue" value="点击上传视频文件，格式限制为:avi/mkv/rm/rmvb/mp4/3gp"/>
								<input type="file" id="prevue_input" name="prevue_input" style="display:none"/>
							</form>
						</div>
						
						<div>
							<label>剧照：</label><input type="text" id="ticketImg"/>
							<div style="height:200px;width:200px;margin-left:130px;" id="tmp_picDiv">
								<img id="refImg" style="width:200px;height:200px;" onerror="javascript:this.src='<%=contextPath%>/image/public/nopic.jpg'"/>
								<span class="img-intro">*可以手动填写网络图片地址或者点击上传图片</span>
							</div>
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
									var url = contextPath + '/admin/upload?fileType=0';
									pictureUpload("refImg", "ticketImg", "tmp_picDiv", url);
								});
								/* end code by */
							</script>
						</div>
						
						<div style="margin-left:130px;margin-top:20px"><button id="submit">发布</button></div>
					</div>
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
