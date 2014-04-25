$(function(){
	var flag = false;
	$("#style-switcher i").click(function(){
		flag = !flag;
		var obj = $("#style-switcher");
		if(flag) {
			$(obj).find("div").css("display", "block");
			$(obj).css("width", "200px");
			$(obj).css("margin-right", "0px");
		} else {
			$(obj).find("div").css("display", "none");
			$(obj).css("width", "35px");
			$(obj).css("margin-right", "0px");
		}
	});
});