<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎回来</title>

<!-- Bootstrap -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link rel="icon" type="image/x-icon" href="/resources/pic/title.png" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<!-- 登录注册页面 -->
	<div style="height: 50px;"></div>

	<div class="container">
		<!-- 最大的div -->
		<div class="row passport_bg">
			<!-- 背景图 -->
			<div class="col-md-6" >
				<!-- 左侧的div -->

				<div class="card" style="width: 22rem;">
					<!-- 卡片 -->
					<div class="card-body">
						<span style="color: red;">${errer}</span>
						<h3 class="card-title" align="center">用户注册</h3>
						
						<form action="/passport/reg" method="post" id="form1">
						
							<div class="form-group">
								<label for="username">用户名:</label> <input type="text"
									class="form-control" name="username" id="username">
							</div>

							<div class="form-group">
								<label for="password">密码:</label> <input type="text"
									class="form-control" name="password" id="password">
							</div>

							<div class="form-group">
								<label for="repassword">重复密码:</label> <input type="text"
									class="form-control" name="repassword" id="repassword">
							</div>
							<div class="form-group form-inline">
								<input type="radio" value="0" class="form-control" name="gender" id="gender" checked="checked">男
							   <input type="radio" value="1" class="form-control" name="gender" id="gender" >女
							</div>

							<button type="submit" class="btn btn-info">注册</button>
							<button type="reset" class="btn btn-warning">重置</button>
							
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
						<img src="/resources/images/duan.png" style="width: 120px;height: 120px;" alt="..."
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
					required:true,//用户名不能为空
					rangelength:[2,10],//用户名长度必须在5-10
				},
				password:{
					required:true,
					rangelength:[6,10],
				},
				repassword:{
					equalTo:"#password",
				}
			},
			//消息提示
			messages:{
				username:{
					required:"用户名不可为空",
					rangelength:"用户名长度为2-10",
				},
				password:{
					required:"密码不可以为空",
					rangelength:"密码长度为6-10",
				},
				repassword:{
					equalTo:"两次密码不一致",
				},
			}
		});
	});
</script>
</html>