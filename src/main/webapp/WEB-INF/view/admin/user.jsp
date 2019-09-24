<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>
<title>用户列表</title>
</head>
<body>
	<div class="form-inline form-group mb-2">
		&nbsp; 用户名:<input class="form-control" type="text" name="name"
			placeholder="please enter">
		<button type="button" onclick="dian()" class="btn btn-warning">查询</button>
	</div>
	<table class="table table-striped">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>注册时间</td>
			<td>用户状态</td>
		</tr>
		<c:forEach items="${userpage.list}" var="li">
			<tr>
				<td>${li.id}</td>
				<td>${li.username}</td>
				<td>${li.nickname}</td>
				<td><fmt:formatDate value="${li.birthday}"
						pattern="yyyy-MM-dd-HH-mm-ss" /></td>
				<td><fmt:formatDate value="${li.createTime}"
						pattern="yyyy-MM-dd-HH-mm-ss" /></td>
				<td>
					<c:if test="${li.locked==0}">
						<button type="button" class="btn btn-success" onclick="updalo(${li.id},this)"  >禁用</button>
					</c:if> 
					<c:if test="${li.locked==1}">
						<button type="button" class="btn btn-warning" onclick="updalo(${li.id},this)"  >正常</button>
					</c:if> 
				</td>
			</tr>
		</c:forEach>
	</table>

	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${userpage.total>0}">
				<c:if test="${userpage.pageNum>1}">
					<a  class="page-link" onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a  class="page-link" onclick="fen(${userpage.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${userpage.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==userpage.pageNum}">
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${userpage.pageNum<userpage.pages}">
					<a class="page-link" onclick="fen(${userpage.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a class="page-link" onclick="fen(${userpage.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
		</ul>
	</nav>
	
</body>
<script type="text/javascript">
function fen(url){ // 分页跳转
	$("#content-wrapper").load("/user/selects?pageNum="+url);
	 /* $("body").load("/user/selects?pageNum="+url);  */
}
function dian(){
	var a=$("[name=name]").val();
	$("#content-wrapper").load("/user/selects?name="+a);
	/* $("body").load("/user/selects?name="+a);  */
}
function updalo(id,obj){
	var lo=$(obj).text()=="正常"?0:1;
	$.post(
		"user/updalo",{
			id:id,
			locked:lo
		},function(data){
			if(data){
				 $(obj).text($(obj).text()=="正常"?"禁用":"正常"); 
				$(obj).attr("class",$(obj).text()=="正常"?"btn btn-warning":"btn btn-success");
				}else{
				alert("操作失败");
			}
		}
	);
	
	
}
</script>
</html>