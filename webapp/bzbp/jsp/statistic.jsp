<%@ page import="java.util.ArrayList"%>
<%@ page import="model.ShareItem"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>BookStore</title>

<%
	String path = request.getContextPath();
%>
<link href="<%=path%>/bzbp/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bzbp/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/bzbp/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="<%=path%>/bzbp/css/bookstore.css" rel="stylesheet">
<link href="<%=path%>/bzbp/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<body>

	<%
	 	String session_adminname=(String)session.getAttribute("admin");
	 	if (session_adminname == null ){
	 		response.sendRedirect("login");
	 	}
	 %>

<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-brand" href="#">边走边拍后台管理系统</a>
			<a class="navbar-brand" href="admin_logout">登出</a>
		</div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
			  		<li><a href="user_getAll"><i
							class="fa fa-user fa-fw"></i> 用户管理</a></li>
					<li><a href="statistic_get" class="active"><i class="fa fa-book fa-fw"></i>
							查看统计分析</a></li>
					<li><a href="bestshare_getBest"><i class="fa fa-reorder fa-fw"></i>
							最佳分享管理</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">统计分析</h1>
				</div>
			</div>	
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							查询某个用户各个月份的分享情况				
						</div>
						<div class="panel-body">
							<div class="div-left">
								<label>请输入想要查询的用户的id:</label><input name="singleuserid">
								<label>请输入想要查询的年份:</label><input name="year" type="number"
										step="1" value="2017" min = 0>
								<button id = "submitsingle">查询</button>
							</div>
							<div class="col-md-5 chart">
								<div id = "singleShareChart"></div>	
								<div id = "singlePicChart"></div>	
								<div id = "singleUpvoteChart"></div>	
								<div id = "singleCommentChart"></div>	
							</div>	
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							所有用户各个月份的分享情况统计				
						</div>							
						<div class="panel-body">
							<div class="div-left">
								<label>请输入想要查询的年份:</label><input name="all_year" type="number"
										step="1" value="2017" min = 0>
								<button id = "submitall">查询</button>
							</div>
							<div class="col-md-5 chart">
								<div id = "allShareChart"></div>	
								<div id = "allPicChart"></div>
								<div id = "allUpvoteChart"></div>
								<div id = "allCommentChart"></div>
								<div id = "activeUserChart"></div>	
							</div>	
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	
	

</body>


	
	
	<script src="<%=path%>/bzbp/js/jquery.min.js"></script>


	<script src="<%=path%>/bzbp/js/jquery.min.js"></script>
	<script src="<%=path%>/bzbp/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bzbp/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bzbp/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bzbp/js/bootbox.min.js"></script>

	<script src="<%=path%>/bzbp/js/Chart.js"></script>
	<script src="<%=path%>/bzbp/js/Chart.bundle.js"></script>
	<script src="<%=path%>/bzbp/js/Chart.bundle.min.js"></script>
	<script src="<%=path%>/bzbp/js/Chart.min.js"></script>



	<script type="text/javascript">
		
		$("#submitsingle").click(function(){
			var id = $("input[name='singleuserid']").val();
			var year = $("input[name='year']").val();
			console.log(id,year);
			
			var checkuid = "";
			jQuery.ajax({
				url : 'user_checkUid',
				processData : true,
				dataType : "text",
				data : {
					uid: id
				},
				success : function(data) {
					checkuid = data;
					if (checkuid!="valid"){
						bootbox.alert({
							message : "该用户不存在"
						});
						
					}
					else {
						
						if (year < 0){
							bootbox.alert({
								message : "请输入正确的年份"
							});
						}
						else{
							jQuery.ajax({
								url : 'statistic_singleshare',
								processData : true,
								dataType : "json",
								data : {
									uid : id,
									year: year
								},
								success : function(data) {
									console.log(data);
									var result = data;
									$('#singleShareChart').empty();
									var html = "<canvas id=\"singleShare\"></canvas>";
									$('#singleShareChart').append(html);
									var ctx = document.getElementById("singleShare").getContext("2d");						
									var data = {
											labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
											datasets : [
												{
													label:"分享次数",
													fillColor : "rgba(80,108,200,0.8)",
													strokeColor : "rgba(80,108,200,1)",
													data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
													borderWidth: 1
												}
											]
										}
									var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
					        			title: {
					                    	display: true,
					                    	text: '用户'+id+'在'+year+'年每月分享的次数统计',
					                    	fontSize: 10
					                	}}
									});					
										
									
									jQuery.ajax({
										url : 'statistic_singlepic',
										processData : true,
										dataType : "json",
										data : {
											uid : id,
											year: year
										},
										success : function(data) {
											console.log(data);
											var result = data;
											$('#singlePicChart').empty();
											var html = "<canvas id=\"singlePic\"></canvas>";
											$('#singlePicChart').append(html);
											var ctx = document.getElementById("singlePic").getContext("2d");						
											var data = {
													labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
													datasets : [
														{
															label:"分享的照片数量",
															fillColor : "rgba(80,108,200,0.8)",
															strokeColor : "rgba(80,108,200,1)",
															data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
															borderWidth: 1
														}
													]
												}
											var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
							        			title: {
							                    	display: true,
							                    	text: '用户'+id+'在'+year+'年每月分享的照片数量统计',
							                    	fontSize: 10
							                	}}
											});
											
											
											jQuery.ajax({
												url : 'statistic_singleupvote',
												processData : true,
												dataType : "json",
												data : {
													uid : id,
													year: year
												},
												success : function(data) {
													console.log(data);
													var result = data;
													$('#singleUpvoteChart').empty();
													var html = "<canvas id=\"singleUpvote\"></canvas>";
													$('#singleUpvoteChart').append(html);
													var ctx = document.getElementById("singleUpvote").getContext("2d");						
													var data = {
															labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
															datasets : [
																{
																	label:"收到的点赞数",
																	fillColor : "rgba(80,108,200,0.8)",
																	strokeColor : "rgba(80,108,200,1)",
																	data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
																	borderWidth: 1
																}
															]
														}
													var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
									        			title: {
									                    	display: true,
									                    	text: '用户'+id+'在'+year+'年每月收到的点赞数统计',
									                    	fontSize: 10
									                	}}
													});
													
													
													jQuery.ajax({
														url : 'statistic_singlecomment',
														processData : true,
														dataType : "json",
														data : {
															uid : id,
															year: year
														},
														success : function(data) {
															console.log(data);
															var result = data;
															$('#singleCommentChart').empty();
															var html = "<canvas id=\"singleComment\"></canvas>";
															$('#singleCommentChart').append(html);
															var ctx = document.getElementById("singleComment").getContext("2d");						
															var data = {
																	labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
																	datasets : [
																		{
																			label:"收到的评论数",
																			fillColor : "rgba(80,108,200,0.8)",
																			strokeColor : "rgba(80,108,200,1)",
																			data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
																			borderWidth: 1
																		}
																	]
																}
															var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
											        			title: {
											                    	display: true,
											                    	text: '用户'+id+'在'+year+'年每月收到的评论数统计',
											                    	fontSize: 10
											                	}}
															});
																									
														}
													});
																							
												}
											});
																					
										}
									});
									
									
								}
							});
						}
						
					}
				}
			});
			
															
			
		});
		
		
		$("#submitall").click(function(){
			var year = $("input[name='all_year']").val();
			console.log(year);
						
			if (year <= 0){
				bootbox.alert({
					message : "请输入正确的年份"
				});
			}
			else{
				
			
				jQuery.ajax({
					url : 'statistic_allshare',
					processData : true,
					dataType : "json",
					data : {
						year: year
					},
					success : function(data) {
						console.log(data);
						var result = data;
						
						$('#allShareChart').empty();
						var html = "<canvas id=\"allShare\"></canvas>";
						$('#allShareChart').append(html);
						var ctx = document.getElementById("allShare").getContext("2d");						
						var data = {
								labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
								datasets : [
									{
										label:"分享路线次数",
										fillColor : "rgba(80,108,200,0.8)",
										strokeColor : "rgba(80,108,200,1)",
										data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
										borderWidth: 1
									}
								]
							}
						var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
		        			title: {
		                    	display: true,
		                    	text: '所有人'+'在'+year+'年每月的分享情况',
		                    	fontSize: 10
		                	}}
						});
						
						
						jQuery.ajax({
							url : 'statistic_allpic',
							processData : true,
							dataType : "json",
							data : {
								year: year
							},
							success : function(data) {
								console.log(data);
								var result = data;
								
								$('#allPicChart').empty();
								var html = "<canvas id=\"allPic\"></canvas>";
								$('#allPicChart').append(html);
								var ctx = document.getElementById("allPic").getContext("2d");						
								var data = {
										labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
										datasets : [
											{
												label:"分享的照片数量",
												fillColor : "rgba(80,108,200,0.8)",
												strokeColor : "rgba(80,108,200,1)",
												data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
												borderWidth: 1
											}
										]
									}
								var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
				        			title: {
				                    	display: true,
				                    	text: '所有人'+'在'+year+'年每月的分享的照片数量统计',
				                    	fontSize: 10
				                	}}
								});		
								
								
								jQuery.ajax({
									url : 'statistic_allupvote',
									processData : true,
									dataType : "json",
									data : {
										year: year
									},
									success : function(data) {
										console.log(data);
										var result = data;
										
										$('#allUpvoteChart').empty();
										var html = "<canvas id=\"allUpvote\"></canvas>";
										$('#allUpvoteChart').append(html);
										var ctx = document.getElementById("allUpvote").getContext("2d");						
										var data = {
												labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
												datasets : [
													{
														label:"收到的点赞数",
														fillColor : "rgba(80,108,200,0.8)",
														strokeColor : "rgba(80,108,200,1)",
														data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
														borderWidth: 1
													}
												]
											}
										var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
						        			title: {
						                    	display: true,
						                    	text: '所有人'+'在'+year+'年每月收到的点赞数统计',
						                    	fontSize: 10
						                	}}
										});		
										
										
										jQuery.ajax({
											url : 'statistic_allcomment',
											processData : true,
											dataType : "json",
											data : {
												year: year
											},
											success : function(data) {
												console.log(data);
												var result = data;
												
												$('#allCommentChart').empty();
												var html = "<canvas id=\"allComment\"></canvas>";
												$('#allCommentChart').append(html);
												var ctx = document.getElementById("allComment").getContext("2d");						
												var data = {
														labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
														datasets : [
															{
																label:"分享的照片数量",
																fillColor : "rgba(80,108,200,0.8)",
																strokeColor : "rgba(80,108,200,1)",
																data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
																borderWidth: 1
															}
														]
													}
												var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
								        			title: {
								                    	display: true,
								                    	text: '所有人'+'在'+year+'年每月收到的评论数统计',
								                    	fontSize: 10
								                	}}
												});	
												
												
												jQuery.ajax({
													url : 'statistic_activeuser',
													processData : true,
													dataType : "json",
													data : {
														year: year
													},
													success : function(data) {
														console.log(data);
														var result = data;
														
														$('#activeUserChart').empty();
														var html = "<canvas id=\"activeUser\"></canvas>";
														$('#activeUserChart').append(html);
														var ctx = document.getElementById("activeUser").getContext("2d");						
														var data = {
																labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
																datasets : [
																	{
																		label:"每月活跃用户数量",
																		fillColor : "rgba(80,108,200,0.8)",
																		strokeColor : "rgba(80,108,200,1)",
																		data : [result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11]],
																		borderWidth: 1
																	}
																]
															}
														var myNewChart = new Chart(ctx, {type:'bar',data: data,options:{
										        			title: {
										                    	display: true,
										                    	text: year+'年每月的活跃用户数量统计'+'（每月活跃用户指的是那个月至少有过一次分享的用户）'	,
										                    	fontSize: 10
										                	}}
														});														
													}
												});
												
											}
										});
										
									}
								});
								
							}
						});
						
					}
				});
			}								
			
		});
		
	</script>

</html>
