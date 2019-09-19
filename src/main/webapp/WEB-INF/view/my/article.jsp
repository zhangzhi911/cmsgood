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
	<div class="container">
		<dl>
			<dt><h1 align="center">${art.title}</h1> </dt>
			<dd>${art.user.nickname} <fmt:formatDate value="${art.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
    		 <dd><hr/></dd>
    		 <dd>${art.content }</dd>
    
    </dl>
	
	
	</div>

</body>
<script type="text/javascript">
</script>
</html>