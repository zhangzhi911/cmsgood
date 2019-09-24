<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>

<title>文章列表</title>
</head>
<body>

	<div class="form-inline form-group mb-2">
		&nbsp; 用户名:<input class="form-control" type="text" name="name"
			placeholder="please enter">
			<select name="status">
				<option value="">全部</option>
				<option value="0">待审</option>
				<option value="1">已阅</option>
				<option value="-1">驳回</option>
			</select>
		<button type="button" onclick="dian()" class="btn btn-warning">查询${statu}</button>
	</div>
	<table class="table table-striped">
		<tr>
			<td>序号</td>
			<td>标题</td>
			<td>作者</td>
			<td>状态</td>
			<td>发布时间</td>
			<td>点击量</td>
			<td>热门</td>
		</tr>
		<c:forEach items="${artpage.list}" var="li" varStatus="i">
			<tr>
				<td>${i.index+1}</td>
				<td>${li.title}</td>
				<td>${li.user.username}</td>
				<td>${li.status==0?"待审核":li.status==1?"已审核":"驳回" }</td>
				<td>${li.hits}</td>
				<td>${li.hot==1?"是":"否"}</td>
				<td>
					<a style="cursor: pointer;" onclick="javascript:myopen(${li.id})">文章详情</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${artpage.total>0}">
				<c:if test="${artpage.pageNum>1}">
					<a  class="page-link" onclick="fen(1)" style="cursor: pointer;">首页</a>
					<a  class="page-link" onclick="fen(${artpage.pageNum-1})" style="cursor: pointer;">上一页</a>
				</c:if>

				<c:forEach end="${artpage.pages}" step="1" begin="1" var="pa">
					<c:choose>
						<c:when test="${pa==artpage.pageNum}">
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer; color: red;">${pa}</a>
						</c:when>
						<c:otherwise>
							<a  class="page-link" onclick="fen(${pa})" style="cursor: pointer;">${pa}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${artpage.pageNum<artpage.pages}">
					<a class="page-link" onclick="fen(${artpage.pageNum+1})" style="cursor: pointer;">下一页</a>
					<a class="page-link" onclick="fen(${artpage.pages})" style="cursor: pointer;">尾页</a>
				</c:if>
			</c:if>
		</ul>
	</nav>
	
</body>
<script type="text/javascript">
	 function fen(page){
		 var status='${statu}';
		$("#content-wrapper").load("/article/selects?pageNum="+page+"&status="+status);
	} 
	//复杂查询
	function dian(){
		var a=$("[name=name]").val();
		var status=$("[name=status]").val();
		
		$("#content-wrapper").load("/article/selects?title="+a+"&status="+status);
	}
	//个人文章打开
	function myopen(id){
		var url="/article/select?id="+id;
		window.open(url,"_blank");

		alert("审核完毕!~");
		var status='${statu}';
		var page='${artpage.pageNum}';
		$("#content-wrapper").load("/article/selects?pageNum="+page+"&status="+status);
	}
	//下拉框默认选择
	$(function(){
		$("[name='status']").val(${statu});
	});
	
	
</script>
</html>