
//个人中心,左侧导航栏的点击事件
$(function(){
	$(".channel").click(function(){
		var url =$(this).attr('data');
		$("#center").load(url)
		
	})
	
	
})
//管理员中心为导航栏添加点击事件
$(function(){
	
	$(".nav-link").click(function(){
		//获取点击的url
		var url =$(this).attr("data");
		//在中间区域加载url
		$("#content-wrapper").load(url);
		
	})
	
	
	
})