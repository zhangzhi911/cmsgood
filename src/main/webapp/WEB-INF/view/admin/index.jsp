<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <!-- <link href="/resource/css/all.min.css" rel="stylesheet" type="text/css">
 -->
<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>
</head>
<body>
	<!-- 后台顶部 -->
	<jsp:include page="top.jsp" />
		
		<div id="wrapper">
			<!-- 后台管理系统 -->
				<jsp:include page="left.jsp" />
			<div id="content-wrapper" >
			   <!-- 	<div style="background-image:url('/resources/img/lun/car1.png');width: 100%;height: 100%; align="center" ></div> -->
			</div>
		</div>
</body>
<script type="text/javascript">

	$(function(){
//导航栏添加点击事件
		$(".nav-link").click(function(){
			var url=$(this).attr("data");
			//在中间区域加载url
			$("#content-wrapper").load(url);
		});
		
		
	})
	

</script>
</html>