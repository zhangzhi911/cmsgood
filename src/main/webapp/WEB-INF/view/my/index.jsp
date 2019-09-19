<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>我的个人空间</title>
<script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	<br />
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/view/my/left.jsp"></jsp:include>
				<br />
			</div>
			<div class="col-md-9 ">
				<div id="center">
				
				  <!--  把富文本编辑器的样式引入 -->
          			<div style="display: none">
          				<jsp:include page="/resources/kindeditor/jsp/demo.jsp"></jsp:include>
          			</div>
              </div> 
              
              
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
<script type="text/javascript">
	$(".channel").click(function(){
		var url=$(this).attr('data');
		$("#center").load(url);
	});
</script>
</body>
</html>