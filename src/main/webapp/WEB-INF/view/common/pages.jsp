<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 使用的common的 分页 -->
			<c:if test="${hotArticles.total>0}">
				<c:if test="${hotArticles.pageNum>1}">
					<a   onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a   onclick="fen(${hotArticles.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${hotArticles.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==hotArticles.pageNum}">
							<a    onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a    onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${hotArticles.pageNum<hotArticles.pages}">
					<a   onclick="fen(${hotArticles.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a   onclick="fen(${hotArticles.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
</body>
</html>