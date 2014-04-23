<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getServletContext().getContextPath();%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/unicorn.login.css" />
        <script type="text/javascript" src="<%=contextPath%>/js/system/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/js/system/MD5.js"></script>
        <title>电影票预订系统</title>
        <script type="text/javascript">
            $(function(){
                /*
                 * ajax login
                 */
                var contextPath = "<%=contextPath%>";
                function login(){
                    var password = $("#password").val();
                    var user = $("#user").val();
                    if (password == "" || user == "") {
                        alert("帐号密码不能为空");
                        return;
                    }
                    password = hex_md5(password);
                    // post data to login
                    // .....
                    $.ajax({
                        url : contextPath + "/login",
                        data : {
                            user : user,
                            password : password,
                        },
                        cache : false,
                        success : function(data){
                            if (data == -1)
                                alert("帐号或者密码错误");
                            else top.location.href = contextPath + data;
                            hideLoading();
                        },
                        error : function() {
                            alert("连接失败");
                            hideLoading();
                        }
                    });
                }
                $("#login").click(function(){
                    login();
                });
                $("#registe").click(function(){
                    top.location.href = contextPath + "/registe.jsp";
                });
                
                $(document).keypress(function(e){
                    if (e.keyCode == 13) {
                        login();
                    }
                });
                function showLoading(){
                    $("#loading").show();
                }
                function hideLoading(){
                    $("#loading").hide();
                }
                hideLoading();
            });
        </script>
    </head>
    <body>
        <div id="logo">
            <img src="<%=contextPath%>/bootstrap/img/logo.png" alt="" />
        </div>
        <div id="loginbox">            
			<p>Enter username and password to continue.</p>
               <div class="control-group">
                   <div class="controls">
                       <div class="input-prepend">
                           <span class="add-on"><i class="icon-user"></i></span>
                           <input type="text" placeholder="Username" id="user" />
                       </div>
                   </div>
               </div>
               <div class="control-group">
                   <div class="controls">
                       <div class="input-prepend">
                           <span class="add-on"><i class="icon-lock"></i></span>
                           <input type="password" placeholder="Password" id="password" />
                       </div>
                   </div>
               </div>
               <div class="form-actions">
                   <span class="pull-left"><a href="#" class="flip-link" id="registe">Registe</a></span>
                   <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Login" id="login" /></span>
               </div>
			<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>
			<div class="control-group">
                   <div class="controls">
                       <div class="input-prepend">
                           <span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                       </div>
                   </div>
               </div>
               <div class="form-actions">
                   <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
                   <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover" /></span>
               </div>
            <div id="loading">
                <img src="<%=contextPath%>/image/public/loading.gif" alt="">
            </div>
        </div>
        
        <script src="<%=contextPath%>/bootstrap/js/jquery.min.js"></script>  
        <script src="<%=contextPath%>/bootstrap/js/unicorn.login.js"></script> 
    </body>
</html>
