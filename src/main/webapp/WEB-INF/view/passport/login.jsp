<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="icon" type="image/x-icon" href="/resources/pic/title.png" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<div style="height: 50px;"></div>
	<!-- 登录 -->
	<div class="container">
		<div class="row passport_bg">
			<!-- 图片 -->
			<div class="col-md-6">


				<div class="card" style="width: 22rem;">
					<div class="card-body">
					<span style="color: red;">${errer}</span>
					<h3 class="card-title" align="center">用户登录</h3>
								
						<form action="/passport/login" id="form1" method="post">
						<div class="form-group">
							<label for="username">用户名:</label>
							<input type="text" class="form-control" name="username" id="username" value="${username}">							
						</div>
						<div class="form-group">
							<label for="password">密码:</label>
							<input type="password" class="form-control" name="password" id="password" >						
						</div>
						
						<button  type="submit" class="btn btn-info">登录</button>
						<button  type="reset" class="btn btn-warning">重置</button>
						</form>
								
					
					</div>
				</div>
			</div>


			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户</h3>
					<p align="center">
						<img src="/resources/images/jing.png" style="width: 120px;height: 120px;" alt="..."
							class="rounded-circle img-thumbnail" />
						<img src="/resources/images/guest1.jpg" style="width: 120px;height: 120px;" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</div>
			
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>


</body>
<script type="text/javascript">
	$(function(){
		$("#form1").validate({
			rules:{
				username:{
					required:true,
					rangelength:[2,10],
				},
				password:{
					required:true,
					rangelength:[6,10],
				},
		
			},
			messages:{
				username:{
					required:"用户名不可为空!",
					rangelength:"用户名长度为2-10位",
				},
				password:{
					required:"密码不可以为空",
					rangelength:"密码长度为6-10位",
				},
			
			}
			
			
			
		});
		
	});




</script>
</html>