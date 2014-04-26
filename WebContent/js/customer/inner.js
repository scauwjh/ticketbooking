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
	//控制提交的是数字（改变触发）
	$(".number").change(function(){
		var num = $(this).val();
		var reg = new RegExp("[0-9]+");
		if(num != "" && !reg.exec(num)){
			alert("只允输入数字");
			$(this).val("");
		}
	});
	//控制只能输入数字
	$(".number").keypress(function(){
		this.style.imeMode='inactive';
		var key = event.keyCode;
		if(key<48||key>57){
			//alert(event.keyCode);
			return false;
		}
	});
});