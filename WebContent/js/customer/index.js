$(function(){
	function init(list) {
		var content = null;
		for (var i = 0; i < list.length; i++) {	
			content = '<div class="ticket">';
			content += '<img src="'+  list[i].ticketImg
			+ '" onerror="javascript:this.src=\''
			+ contextPath + '/image/public/nopic.jpg\'"/>'
			+ '<div class="ticket-desc">'
			+ '<p>片名：' + list[i].ticketName + '</p>'
			+ '<p>原价：' + list[i].originalPrice + '</p>'
			+ '<p>现价：' + list[i].ticketPrice + '</p>'
			+ '<div class="operate">'
			+ '<input type="hidden" class="ticketId" value="' + list[i].ticketId + '">'
			+ '<button class="details">详细</button>'
			+ '<button class="purchase">购买</button>'
			+ '</div>'
			+ '</div>'
			+ '</div>';
			$("#main_box").append(content);
		}
	}
	
	function queryTicket(start, limit) {
		$.post(contextPath + "/outward/ticket",
			{
				method : "queryList",
				start : start,
				limit : limit
			},
			function(data){
				// alert(data);
				var list = eval("(" + data + ")");
				init(list);
			}
		);
	}
	
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
	
	// 查询ticket列表
	queryTicket(0, 12);
	// 检查是否登录
	checkLogin();
	loginMessage();
	
	$(document).on("click", ".purchase", function(){
		checkLogin();
		if (!isLogin) {
			alert("请先登录");
			return;
		}
		var ticketId = $(this).prevAll(".ticketId").val();
		$.post(contextPath + "/inner/buyticket",
			{
				ticketId : ticketId
			},
			function(data){
				alert(data);
			}
		);
	});
	
	$(document).on("click", ".details", function(){
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
		
		$(document).on("click", ".close", function(){
			$(".details-box").remove();
			$(".full-screen-shade").remove();
		});
	});
	
	$("#logout").click(function(){
		var url = contextPath + "/logout";
		$.post(url, function(data){
			window.location.reload();
		});
	});
});