<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%String path = request.getContextPath(); %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<%=path%>/bzbp/css/bootstrap.css">
    <link rel="stylesheet" href="<%=path%>/bzbp/css/bootstrap-theme.css">
    <style type="text/css">
    	h1{width:100%;text-align: center;font-family: romans;}
    	#log{width:350px; height:auto; background-color:white; position:absolute; margin-left:36%; margin-top:100px;}
    	#fd{width:250px; height:auto;margin-left: 50px;margin-top: 50px;}
    	#op{text-align: center;}
    </style>
	<title>BZBP | login</title>
</head>
<body style="background-color: 	#F0F8FF;">
    <div class="container-fluid" style="height: 75px;background-color: white">
        <nav id="navbar" class="navbar-collapse collapse">
        	<h2 style="font-size:30px;font-family: times;">
        		<a style="text-decoration: none;color: black;" >欢迎来到边走边拍管理员登录界面</a> 
        	</h2>
        </nav>
    </div>
	<div class="container-fluid" style="background-color: black;">
		<div id="log">
			<h1>管理员登录</h1> 
    		<form id="fd" action="admin_login" method="post">
    		<s:fielderror cssStyle="color:Red;"></s:fielderror>
        		<div class="form-group">
           	 		<input type="text" class="form-control first"  name="admin.username" id="uid"
                   		   placeholder="管理员用户名" autofocus autocomplete="off"/>
        		</div>
        		<div class="form-group">
            		<input type="password" class="form-control last" name="admin.password" 
            		       placeholder="密码" />
       		 	</div>
        		<div class="form-group" style="margin-bottom: 40px;">
          			<button type="submit" class="btn btn-primary btn-fat btn-block">登录</button>
        		</div>
    		</form>	
		</div>
	</div>
</body>
<script src="<%=path%>/bzbp/js/jquery.min.js"></script>
<script src="<%=path%>/bzbp/js/bootstrap.min.js"></script>
<script src="<%=path%>/bzbp/js/bootstrapValidator.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#fd').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
	    	invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
	fields: {
		"admin.username": {
			message: 'Username is not valid',
			validators: {
				notEmpty: {
					message: '用户名不能是空白的'
				},
				stringLength: {
					min: 1,
					max: 100,
					message: '用户名至少为1位'
				},
				regexp: {
					regexp: /^[a-zA-Z0-9_]+$/,
					message: '用户名只能包含字母,数字,圆点和下划线'
				}
			}
		},
		"admin.password": {
			validators: {
				notEmpty: {
					message: '密码不能是空白的'
				},
				stringLength: {
					min: 5,
					max: 200,
					message: '密码至少为6位'
				},
				regexp: {
					regexp: /^[a-zA-Z0-9_.@#$%^&*~]+$/,
					message: '密码只能包含字母，数字和 _.@#$%^&*~'
				}
			}
		}
	}
	});
});
</script>
</html>