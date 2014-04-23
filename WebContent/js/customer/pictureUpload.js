/* code by @wjh */
/**
 * 
 * @param picId
 * @param shadeId
 * @param url
 * @return
 */
function pictureUpload(picId,showUrlId,picDivId,url){
	$("#"+picId).attr("src",$("#"+showUrlId).val());
	addTag(picId,showUrlId,picDivId,url);
	$(document).ready(function(){
		var ajax = function(){
			ajaxUpload(picId,showUrlId,url);
			$("#tmp_file").change(ajax);
		};
		$("#"+picId).mouseover(function(){
			$("#shade").css("display","block");
		});
		$("#shade").mouseout(function(){
			$("#shade").css("display","none");
		});
		$("#shade").click(function(){
			$("#tmp_file").click();
		});
		$("#tmp_file").change(ajax);
		$("#"+showUrlId).change(function(){
			$("#"+picId).attr("src",$("#"+showUrlId).val());
		});
	});
}

/**
 * addForm
 * @param showUrlId 显示url的input或者textarea
 * @param picId img标签的id
 * @param url
 * @return
 * 添加一个图片上传的表单
 * tmp_file是上传空间的Id
 */
function addTag(picId,showUrlId,picDivId,url){
	var str = null;
	if(window.ActiveXObject){
		str = '<div style="height:27px;">';
		str += '<form id="tmp_form" name="frm" method="post" enctype="multipart/form-data">';
		str += '上传图片：<input type="file" id="tmp_file" name="attachment" style="width:200px;" />';
		str += '</form>';
		str += '</div>';
		$("#"+picDivId).append(str);
	}
	else{
		str = '<div id="formDiv" style="display:none">';
		str += '<form id="tmp_form" name="frm" method="post" enctype="multipart/form-data">';
		str += '<input type="file" id="tmp_file" name="attachment" />';
		str += '</form>';
		str += '</div>';
		$("body").append(str);
		str = '<div id="shade" style="width:200px;height:200px;background:rgba(0,0,0,0.4);';
		str +=	'line-height:200px;text-align:center;position:relative;top:-200px;display:none;">';
		str +=	'<span style="font-size:25px;color:white;font-weight:bold;font-family:微软雅黑">点击上传图片</span>';
		str += '</div>';
		$("#"+picDivId).append(str);
	}
}
/**
 * ajaxUpload
 * @param showUrlId 显示url的input或者textarea
 * @param picId img标签的id
 * @param url
 * @return
 */
function ajaxUpload(picId,showUrlId,url){
	$.ajaxFileUpload({
		url:url,//用于文件上传的服务器端请求地址
		secureuri:false,//一般设置为false
		fileElementId:'tmp_file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType: 'text',//返回值类型 一般设置为json
		success: function (data, status){
			if (data == -1) {
				alert("上传失败！");
				return;
			}
			$("#"+showUrlId).val(data);
			$("#"+picId).attr("src", data);
		},
		error: function (data, status, e){
			//服务器响应失败处理函数
			alert(e);
		}
		
	});
}