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
		<ul class="list-unstyled">
			<c:forEach items="${artpageBy.list}" var="li">
				<li class="media from-group" >
					<img alt="无图" src="/pic/${li.picture}" class="mr-3" style="width: 120px;height:80px; ">                                      
					<div class="media-body">
						<h4 class="mt-0 mb-1">标题:<a href="javascript:myopen(${li.id})"> ${li.title}</a> </h4>
						<h5 class="mt-0 mv-1">作者:${li.user.nickname}&nbsp; 发布时间: <fmt:formatDate value="${li.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </h5>
					</div>
				<hr>
				</li>
			</c:forEach>
		</ul>
	</div>

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
	 function fen(page){
		$("#center").load("/article/selectsByUser?pageNum="+page);
	} 
	function myopen(id){
		
		var url="/article/selectsBy?id="+id;
		window.open(url,"_blank");
	}
	
	
</script>
</html>