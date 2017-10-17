<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
	<title>边走边拍</title>  
	<link rel="stylesheet" type="text/css" href="./css/swiper.min.css" />
	<style type="text/css">  
		html{height:100%}  
		body{height:100%;margin:0px;padding:0px}  
		#container{height:80%}  
		.swiper-container{height:20%;}
	</style>  
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GoXsubGjtA9tjjUKuvgizRNcwIwoC5GU">
	</script>
</head>
<body>
<div id="container"></div> 
	
	 <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" id="photos">
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    
</body>
<script src="./js/jquery.min.js"></script>
<script src="./js/swiper.min.js"></script>  
<%String id = request.getParameter("sid"); %>
<script type="text/javascript">
	var marker;
	var map = new BMap.Map("container",{minZoom:2,maxZoom:20});  
	var poi = new BMap.Point(118.87,42.28);
	map.centerAndZoom(poi, 17);
	map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
		type: BMAP_NAVIGATION_CONTROL_SMALL,offset: new BMap.Size(20 ,80)})); 
	map.addControl(new BMap.ScaleControl()); 
	map.addControl(new BMap.PanoramaControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT})); 
	map.enableScrollWheelZoom(true);
	map.enableKeyboard();
	var id = "<%=id%>";
	var array = [];
	$.ajax({
		url: "share_getRoute?id="+id,
		dataType: "json",
		async: false,
		success: function(data){
			if(data=="failed")return;
			var len = data.length;
			for(var i=0; i<len; ++i){
				array[i]=new BMap.Point(data[i].longitude,data[i].latitude);
			}
			var polyline = new BMap.Polyline(array, {strokeColor:"blue", strokeWeight:5, strokeOpacity:0.5});	
			map.addOverlay(polyline);
			map.panTo(array[0]);
			
		}
	});
	
	$.ajax({
		url: "share_getMaxIndex?id="+id,
		dataType: "text",
		async: false,
		success: function(data){
			console.log(data);
			var maxindex = Number(data);
			$('#photos').empty();
			var html ="";
			for (var i=0; i < maxindex ; i++){
				html += "<div class=\"swiper-slide\"><img width=\"100%\" height=\"100%\" src=\"share_getPic?id="+id+"&index="+i+"\" onclick='getPos("+i+")'/></div>"
			}
			$('#photos').append(html);

		}
	});
	
	function getPos(idx){
		var sid = "<%=id%>";
		console.log(sid,idx);
		$.ajax({
			url: "share_getPosition?id="+id+"&index="+idx,
			dataType: "json",
			async: false,
			success: function(data){
				console.log(data);
				map.removeOverlay(marker);
				var point = new BMap.Point(data.lon, data.lat);    
				marker = new BMap.Marker(point);        // 创建标注    
				map.centerAndZoom(point, 21);
				map.addOverlay(marker);                     // 将标注添加到地图中
			}
		});
	}
	
</script>
<script type="text/javascript">
	var swiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        effect: 'coverflow',
	        slidesPerView: 3,
	        paginationClickable: true,
			grabCursor: true,
        	centeredSlides: true,
        	spaceBetween: 30
	});
	
</script>
</html>