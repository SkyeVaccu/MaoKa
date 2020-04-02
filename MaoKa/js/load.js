//每个页面都需要发送一下token，校验是否正确
$(function(){
	//判断url中是否有token,如果有token就将token放到cookie中
    if($.getUrlParam("token")!="")
    	$.cookie("token",$.getUrlParam("token"));
    //拿到token
	var token=$.cookie("token");
	$.ajax({
		url:"http://www.maoka.com:9001/parseToken",
		type:"post",
		data:{"token":$.cookie("token")},
		async:false,
		success:function(data){
			var info=eval(data);
			if(info.result==false){
				$.removeCookie("token");
				window.location.href="../html/Login.html";
			}
			else{
				$.cookie("id",info.id);
				$.cookie("image",info.image);
				$.cookie("account",info.account);
				$.cookie("username",info.username);
			}
		}
	});
})

