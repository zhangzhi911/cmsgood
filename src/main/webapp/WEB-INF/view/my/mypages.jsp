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
			<c:if test="${comentpage.total>0}">
				<c:if test="${comentpage.pageNum>1}">
					<a   onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a   onclick="fen(${comentpage.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${comentpage.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==comentpage.pageNum}">
							<a    onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a    onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${comentpage.pageNum<comentpage.pages}">
					<a   onclick="fen(${comentpage.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a   onclick="fen(${comentpage.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
</body>
</html>