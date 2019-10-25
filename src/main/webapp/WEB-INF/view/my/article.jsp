<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<!-- 个人单篇文章-->
	<div class="container">
	
<button class="btn btn-success" onclick="pre()">上一篇</button>
<button class="btn btn-success" onclick="next()">下一篇</button>
	</div>
	<div class="container">
	<!-- 让他在一行 -->
	<div class="row">
	<div class="col-md-9">
	
		<dl>
			<dt><h1 align="center">${art.title}</h1> </dt>
			<dd>作者:${art.user.nickname}  &nbsp; &nbsp;
				发布日期:&nbsp;<fmt:formatDate value="${art.created}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;&nbsp;
				浏览量:${art.pageview}&nbsp;&nbsp;
				<span style="color: red;float: right;font-size: 24px;" onclick="wenhits(${art.id})" >点击量:${art.hits}
		   	 				<img alt="" src="/resources/img/ico/png/thumb-up-2x.png" >
				</span>
			</dd>
    		 <dd><hr/></dd>
    		 <dd>${art.content }</dd>
   		 </dl>
   	</div>
   	<!-- 右侧的排行榜 -->
   	<div class="col-md-3" >
   		<div class="card" style="width: 18rem;">
			  <div class="card-body">
				   <h3 class="card-title">评论排行榜</h3>
				    <c:forEach items="${artcoms}" var="li">
    	 			
    	 			<a  href="/article/selectsBy?id=${li.id}" target="_blank"  style="text-decoration: none;"> ${li.title} </a> 
    	 			${li.comments}  &nbsp;&nbsp; <br/>
    	 			
    	 			</c:forEach>
				
			  </div>
			  
			  <div class="card-body" >
				   <h3 class="card-title">点击率排行榜</h3>
				    <c:forEach items="${arthits}" var="li" varStatus="1" >
    	 			 
    	 				 <a  href="/article/selectsBy?id=${li.id}" target="_blank"  style="text-decoration: none;"> ${li.title} </a> 
    	 				&nbsp;&nbsp; <br/>
    	 			
    	 			</c:forEach>
				
			  </div>
		</div>
   	</div>
   	
   	</div>	<!-- 让他在一行 -->
   	
   		 <!-- 以下是评论 -->
   	<c:if test="${sessionScope.user!=null}">
 
		<h3>${coments.size()}条评论~!</h3>
		<div  > 
			<img alt="" src="/resources/images/default_avatar.png" style="width: 50px;height: 30px;float: left;">
			${user.nickname}
			<form action="" id="form1">
				<textarea rows="2" cols="155" placeholder="please enter~" name="content"></textarea><br>
				<input type="hidden" name="article.id" value="${art.id}">
				<input type="button"  value="评论" class="btn btn-danger" style="float: right;" onclick="pin()">
				<button type="reset" class="btn btn-warning" style="float: right;">重置</button>
			</form>
		</div>
	</c:if>
	<c:if test="${sessionScope.user==null}">
		 <h4 style="color: red">请登录后评论</h4>
	</c:if>
		   <br>
		   <hr>
    	 <dl>
    	 	<c:forEach items="${coments}" var="li">
    	 		<dt>${li.userid.username} &nbsp;&nbsp; 
    	 			<fmt:formatDate  value="${li.created}" pattern="yyyy-MM-dd HH-mm-ss"/>
    	 			 <p style="display: inline-block;float: right;.text-decoration-none;cursor: pointer;" onclick="dian(${li.id})">${li.hits}
    	 				<img alt="" src="/resources/img/ico/png/thumb-up-2x.png" >
    	 			</p> 
    	 		</dt>
    	 		
    	 		<dd >${li.content}</dd>
				<hr>    	 		
    	 	</c:forEach>
    	 </dl>
		<jsp:include page="/WEB-INF/view/my/mypages.jsp"></jsp:include>
	
	
	</div>



</body>

<script type="text/javascript">
	//上一篇
	function pre(){
		$.get(
			"/article/checkPre",{
				id:${art.id},
				channelId:${art.channelId}
			},function(data){
				if(data){
					location.href="/article/preselectBy?id="+${art.id}+"&channelId="+${art.channelId};
				}else{
					alert("已经第一篇了~");
				}
			}
		);
	}
	//下一篇
	function next(){
		$.get(
				"/article/chackNext",{
					id:${art.id},
					channelId:${art.channelId}
				},function(data){
					if(data){
						location.href="/article/nextselectBy?id="+${art.id}+"&channelId="+${art.channelId};
					}else{
						alert("已经最后一篇了~");
					}
				}
			);
	}

//评论
	function pin(){
		var a=$("[name=content]").val();
		if(a==''){
			alert("评论不可为空~");
			return false;
		} 
		 $.post(
			"/article/coment",
				$("#form1").serialize()				
			,function(data){
				if(data){
					alert("发布成功");
					location.reload();//重新加载页面
				}else{
					alert("请重新登录~");
					location.href="/passport/login";
				}
			}
		);
	}
	//评论点赞
	function dian(id){
		$.post(
				"/article/comentupda",{
					id:id	
				},function(data){
					if(data){
						location.reload();//重新加载页面
					}else{
						alert("请重新登录~");
						location.href="/passport/login";
					}
				}
			); 
	}	
	//文章点赞
	function wenhits(id){
		$.post(
				"/article/updateArt",{
					id:id
				},function(data){
					if(data){
						location.reload();//重新加载页面
						
					}else{
						alert("请重新登录~");
						location.href="/passport/login";
					}
				}
			); 
	}
	//分页
	function fen(num){
		var id='${art.id}';
		location.href="/article/selectsBy?id="+id+"&pageNum="+num;
	}
	
</script>
</html>