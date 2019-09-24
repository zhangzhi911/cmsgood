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
			<c:forEach items="${coms}" var="li">
				<li class="media from-group" >
					<div class="media-body">
						<h4 class="mt-0 mb-1">标题:<a href="javascript:myopen(${li.id})"> ${li.article.title}</a> </h4>
						<h5 class="mt-0 mv-1">内容:${li.content}&nbsp; 发布时间: <fmt:formatDate value="${li.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </h5>
					</div>
				<div style="float: right;">
					
				</div>
				</li>
				<hr>
			</c:forEach>
		</ul>
	</div>
<!--分页 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${compage.total>0}">
				<c:if test="${compage.pageNum>1}">
					<a  class="page-link" onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a  class="page-link" onclick="fen(${compage.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${compage.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==compage.pageNum}">
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${compage.pageNum<compage.pages}">
					<a class="page-link" onclick="fen(${compage.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a class="page-link" onclick="fen(${compage.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
		</ul>
	</nav>
	
</body>
<script type="text/javascript">
//打开选择的文章
		function myopen(id){
			var url="/article/selectsBy?id="+id;
			window.open(url,"_blank");
		}
		function fen(page){
			$("#center").load("/comment/selects?pageNum="+page);
		}
	
</script>
</html>