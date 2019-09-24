<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>


<title>CMS系统</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<div>
		<br>
	</div>

	
	<div class="container">
		<div class="row">
			<!-- 一行 -->

			<div class="col-md-2">
				<!-- 左侧两行 -->
				<ul class="list-group">
					<!-- 列表组 ul -->
					<li class="list-group-item text-center" id="channel"><a
						style="font-size: 35px;" class="channel" href="index">热门</a></li>
					<!-- 第一个热门是写死的 -->

					<c:forEach items="${channels}" var="cli">
						<li class="list-group-item text-center" id="channel${cli.id}">
							<a style="font-size: 35px;" class="channel"
							href="index?channelId=${cli.id}">${cli.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>

			<!-- 中间内容 -->
			<div class="col-md-7">
		<!-- 如果栏目的id不为空，就不显示轮播图了，显示具体的栏目内容 -->
		<c:if test="${article.channelId==null}">
		
		
				<!-- 轮播 -->
				<jsp:include page="/WEB-INF/view/index/lun.jsp"></jsp:include>
				<br>
		
				<c:forEach items="${hotArticles.list}" var="artli">
					<!--网站搜med  样式  -->
					<div class="media">
						<img src="/pic/ed92b4d0-105d-4109-b7e1-4649518680b4.png" style="width: 190px;height: 124px;" class="mr-3" alt="...">
						<div class="media-body">
							<h5 class="mt-0" style="color: red;"><a href="/article/selectsBy?id=${artli.id}" target="_blank"  style="text-decoration: none;">${artli.title}</a></h5>
							<dd>作者: ${artli.user.nickname}</dd>
							<dd>日期: <fmt:formatDate value="${artli.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
								
								<span style="color: red;float: right;font-size: 24px;" onclick="wenhits(${artli.id})" >${artli.hits}
		   	 				<img alt="" src="/resources/img/ico/png/thumb-up-2x.png" style="cursor: pointer;" >
								</span>
								<!-- 点赞的~ -->
							   </dd>
						</div>
					</div>
					<hr/>
				</c:forEach>
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
		</c:if>
		<!-- 栏目不空的时候显示 -->
		<c:if test="${article.channelId!=null}">
				<!-- 分类 -->
				<ul class="nav nav-pills">
				 <li class="nav-item" id="cate" > <a class="nav-link" href="index?channelId=${article.channelId}" >全部</a></li>
					<c:forEach items="${categorys}" var="cali">
					  <li class="nav-item" id="cate${cali.id}" >
					    <a class="nav-link" href="index?channelId=${article.channelId}&categoryId=${cali.id}">${cali.name}</a>
					  </li>
					</c:forEach>
				</ul>
				
				<!-- 栏目中的项目 -->
				<br>
				<c:forEach items="${hotArticles.list}" var="artli">
					<!--网站搜med  样式  -->
					<div class="media">
						<img src="/pic/${artli.picture}" style="width: 190px;height: 124px;" class="mr-3" alt="...">
						<div class="media-body">
							<h5 class="mt-0" style="color: red;"><a  href="/article/selectsBy?id=${artli.id}" target="_blank" style="text-decoration: none;"> ${artli.title} </a> </h5>
							<dd>作者: ${artli.user.nickname}</dd>
							<dd>日期: <fmt:formatDate value="${artli.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
								<span style="color: red;float: right;font-size: 24px;" onclick="wenhits(${artli.id})" >${artli.hits}
			   	 					<img alt="" src="/resources/img/ico/png/thumb-up-2x.png" style="cursor: pointer;" >
								</span><!-- 分类中的点赞 -->
							 </dd>
						</div>
					</div>
					<hr/>
				</c:forEach>
				
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
		</c:if>
			</div>



			<!-- 右侧的 -->
			<div class="col-md-3">
				<div class="card" style="width: 18rem;">
				  <div class="card-body">
				    <h3 class="card-title">最新文章</h3>
				    <c:forEach items="${newart}" var="artli">
						<!--网站搜med  样式  -->
						<h6> <a  href="/article/selectsBy?id=${artli.id}" target="_blank"  style="text-decoration: none;"> ${artli.title} </a> </h6> 
						<p>作者: ${artli.user.nickname}</p>
					</c:forEach>
				
				  </div>
				</div>
				
			</div>

		</div>
	</div>


	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	<script type="text/javascript">
	
	 function fen(page){
			var chan='${article.channelId}';
			var cat='${article.categoryId}';
			//一定要加 ‘’  自动给他加空  要不然会出错
			location.href="/index?pageNum="+page+"&channelId="+chan+"&categoryId="+cat;
	 } 
	 //用art中的栏目id去整
	 //点击左侧栏目的时候会传值去controller给channelId赋值
	$(function(){
		
	/* alert(${article.categoryId});	 
	alert(${article.channelId});	  */
	 
	 //这个地方用的非常巧妙，在第一次进来的时候article是空的，正好‘热门’ 也是空，所以会选中'热门''
	//为栏目添加高亮的样式  用art中的栏目id去整
		$("#channel${article.channelId}").addClass('list-group-item-warning');
	
		$("#cate${article.categoryId}").addClass('list-group-item-warning');
 	});
	//文章点赞
	function wenhits(id){
		var page='${hotArticles.pageNum}';
		var chan='${article.channelId}';
		var cat='${article.categoryId}';
		$.post(
				"/article/updateArt",{
					id:id
				},function(data){
					if(data){
						$("body").load("/index?pageNum="+page+"&channelId="+chan+"&categoryId="+cat);
					}else{
						alert("请重新登录~");
						location.href="/passport/login";
					}
				}
			); 
	}
	</script>
</body>
</html>






