$(function(){
	function init(ticket, ticketRecord) {
		var content = null;
		for (var i = 0; i < ticket.length; i++) {	
			var text = ticketRecord[i].checked == 1 ? "已接受订购" : "未处理订购";
			content = '<div class="ticket">';
			content += '<img src="'+  ticket[i].ticketImg
			+ '" onerror="javascript:this.src=\''
			+ contextPath + '/image/public/nopic.jpg\'"/>'
			+ '<div class="ticket-desc">'
			+ '<p>片名：' + ticket[i].ticketName + '</p>'
			+ '<p>原价：' + ticket[i].originalPrice + '</p>'
			+ '<p>现价：' + ticket[i].ticketPrice + '</p>'
			+ '<div class="operate">'
			+ '<input type="hidden" class="ticketId" value="' + ticket[i].ticketId + '">'
			+ '<button class="details">' + text + '</button>'
			+ '</div>'
			+ '</div>'
			+ '</div>';
			$("#main_box").append(content);
		}
	}
	
	function queryTicketRecord(start, limit) {
		$.post(contextPath + "/inner/buyticket",
			{
				method : "query",
				start : start,
				limit : limit
			},
			function(data){
				var list = eval("(" + data + ")");
				init(list.ticket, list.ticketRecord);
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
	// 检查是否登录
	checkLogin();
	loginMessage();
	if (isLogin) queryTicketRecord(0, 12);
	
	$("#logout").click(function(){
		var url = contextPath + "/logout";
		$.post(url, function(data){
			window.location.reload();
		});
	});
});