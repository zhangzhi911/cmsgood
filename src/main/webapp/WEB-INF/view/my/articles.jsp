<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
	<!-- 搜索 -->
	<div class="form-inline">
		<input type="text" name="title" class="form-control" placeholder="please enter" value="${bytitle}"> &nbsp;&nbsp;&nbsp;
		<button class="btn btn-info" onclick="query()">搜索</button>
	</div>
	<hr/>
		<ul class="list-unstyled">
			<c:forEach items="${artpageBy.list}" var="li">
				<li class="media from-group" >
					<img alt="无图" src="/pic/${li.picture}" class="mr-3" style="width: 120px;height:80px; ">                                      
					<div class="media-body">
						<h4 class="mt-0 mb-1">标题:<a href="javascript:myopen(${li.id})"> ${li.title}</a> </h4>
						<h5 class="mt-0 mv-1">作者:${li.user.nickname}&nbsp; 发布时间: <fmt:formatDate value="${li.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </h5>
					</div>
				<div style="float: right;">
					<button class="btn btn-success" onclick="toUpdate(${li.id})">修改</button>
					
					<c:if test="${li.deleted==1}">
						<button class="btn btn-warning" onclick="upda(${li.id},0)">删除</button>
					</c:if>
					<c:if test="${li.deleted==0}">
						<button class="btn btn-sccess" onclick="upda(${li.id},1)">恢复</button>
					</c:if>
					
				</div>
				</li>
				<hr>
			</c:forEach>
		</ul>
	</div>
<!--分页 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${artpageBy.total>0}">
				<c:if test="${artpageBy.pageNum>1}">
					<a  class="page-link" onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a  class="page-link" onclick="fen(${artpageBy.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${artpageBy.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==artpageBy.pageNum}">
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${artpageBy.pageNum<artpageBy.pages}">
					<a class="page-link" onclick="fen(${artpageBy.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a class="page-link" onclick="fen(${artpageBy.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
		</ul>
	</nav>
	
</body>
<script type="text/javascript">
	//去修改
	function toUpdate(id){
		$("#center").load("/article/update?id="+id);
	}
	
	//搜索
	function query(){
		$("#center").load("/article/selectsByUser?title="+$("[name=title]").val());
	}

	 //分页的东西
	 function fen(page){ 
		var title='${bytitle}';
		 $("#center").load("/article/selectsByUser?pageNum="+page+"&title="+title);
	} 
	 //打开选择的文章
	function myopen(id){
		
		var url="/article/selectsBy?id="+id;
		window.open(url,"_blank");
	}
	function upda(id,de){
		$.post(
			"/article/upda",{
			id:id,
			deleted:de
			},function(data){
				if(data){
					if(de==1){
						alert("恢复成功");
					}else{
						alert("删除成功");
					}
					$("#center").load("/article/selectsByUser");
				}
			}
		);
		
		
	}

	
</script>
</html>