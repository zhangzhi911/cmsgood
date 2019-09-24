<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="/resources/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resources/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resources/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="/resources/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="/resources/kindeditor/lang/zh-CN.js"></script>
	<script
		src="/resources/js/jquery-3.2.1.js"></script>

	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resources/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resources/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resources/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		function query(){
		alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
	</script>
</head>
<body>
	<%=htmlData%>
	<form id="form1" enctype="application/x-www-form-urlencoded" > 
		<div class="form-group">
		<label for="title">文章标题:</label>
		<input type="text" class="form-control" name="title" id="title">
		</div>
		
		<div class="form-group">
		<textarea name="content1" cols="100" rows="8" style="width:825px;height:225px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		</div>		

		<div class="form-group form-inline">
		文章栏目:<select name="channelId" id="channel" class="form-control">  </select>
		文章分类:<select name="categoryId" id="category" class="form-control">  </select>
		</div>			       
		
		<div class="form-group"> <!-- 图片 -->
			标题图片:<input type="file" name="file" value="" class="form-control">		
		</div>
				
		<input type="button" name="button" value="提交内容" onclick="pulish()" class="btn" /> (提交快捷键: Ctrl + Enter)

	</form>
</body>
<script type="text/javascript">
	
	 function pulish(){
		var formdata=new FormData($("#form1")[0]);
		alert(editor1.html()); 
		formdata.set("content",editor1.html())
		
		$.ajax({
			type:"post",
			data:formdata,
			processData:false,
			//告诉jquery不要处理发送的数据
			contentType:false,
			//告诉jquery不要设置content-type请求
			url:"/article/publish",
			success:function(flag){
				if(flag){
					alert("发布成功");
					$("#center").load("/article/selectsByUser");
				}
			}
		});
		
	 }
	 
	
	 	$.get("/channel/selecs",
		function(data){
			for (var i = 0; i < data.length; i++) {
				$("#channel").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
			}
			if($("#channel").val()!=null){
				getcategory($("#channel").val());
			}
	 	}
		); 
	 	
	 	
	 	$("#channel").change(function(){
	 		var id=$(this).val();
	 		$("#category").empty();
	 		getcategory(id);
	 	});
	 	//分类的函数
	 	function getcategory(id){
	 		 $.get(
	 				"/category/selecs",{
	 					cid:id
	 				},
	 				function(data){
	 					for (var i = 0; i < data.length; i++) {
	 						$("#category").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
	 					}
	 				}
	 			);  
	 	}

</script>

</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>