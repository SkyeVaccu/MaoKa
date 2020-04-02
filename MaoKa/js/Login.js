$(function(){
    //加载对应的navbar的按钮样式
    $.loadNavbarStyle("Login");
    //清除所有的cookie
    $.removeAllCookie();
    //隐藏注册模块
    $("#form_register").css("display","none");
})

//注册按钮点击
$("#goRegister-btn").click(function(){
    //隐藏注册模块
    $("#form_login").css("display","none");
    //显示注册模块
    $("#form_register").css("display","block");
})
//登录按钮点击
$("#goLogin-btn").click(function(){
    //隐藏注册模块
    $("#form_register").css("display","none");
    //显示登录模块
    $("#form_login").css("display","block");
})

// 账号输入栏的实时校验事件
$("#account").keyup(function() {
    var result=$.check("#account",/^[1-9]{6,}$/);
    switch(result){
        case "": $.changePrompt("#account_text","账号不能为空",false);break;
        case true:$.changePrompt("#account_text","符合规范",true);break;
        case false:$.changePrompt("#account_text","账号不符合规范",false);break;
    }
})

// 密码输入栏的实时校验事件
$("#password").keyup(function() {
     var result=$.check("#password",/^[1-9a-zA-Z]{4,}$/);
    switch(result){
        case "": $.changePrompt("#password_text","密码不能为空",false);break;
        case true:$.changePrompt("#password_text","符合规范",true);break;
        case false:$.changePrompt("#password_text","密码不符合规范",false);break;
    }
})

//登录按钮点击
$("#login-btn").click(function() {
    var account = $("#account").val();
    var password = $("#password").val();
    if ($("#account_text").hasClass("text-success") && $("#password_text").hasClass("text-success")) {
        $.ajax({
            type:"post",
            url:"http://www.maoka.com:7001/login",
            data:{ "account": account, "password": password,"loginType":0},
            xhrFields: {
                withCredentials: true    // 要在这里设置 跨域设置cookie
            },
            success:function(data) {
                if (""==data)
                    $("#login_text").text("登录失败");
                else {
                    var returnUrl = $.getUrlParam("returnUrl");
                    $.cookie("token",data);
                    if(returnUrl=="")
                    	window.location.href="/html/index.html";
                    else
                    	window.location.href=returnUrl;
                }
            }
            }
        )
    }
})

//微博登录
$("#weibo-btn").click(function(){
	window.location.href="https://api.weibo.com/oauth2/authorize?client_id=2415007340&response_type=code&redirect_uri=http://www.maoka.com:7001/threeLogin"
})


// 注册页用户名输入栏的实时校验事件
$("#register_username").keyup(function() {
     var result=$.check("#register_username",/^[a-zA-Z]{4,}|[\u4e00-\u9fa5]{2,}$/);
    switch(result){
        case "": $.changePrompt("#register_username_text","账号不能为空",false);break;
        case true:$.changePrompt("#register_username_text","符合规范",true);break;
        case false:$.changePrompt("#register_username_text","账号不符合规范",false);break;
    }
})

// 注册页密码输入栏的实时校验事件
$("#register_password").keyup(function() {
     var result=$.check("#register_password",/^[1-9a-zA-Z]{4,}$/);
    switch(result){
        case "": $.changePrompt("#register_password_text","密码不能为空",false);break;
        case true:$.changePrompt("#register_password_text","符合规范",true);break;
        case false:$.changePrompt("#register_password_text","密码不符合规范",false);break;
    }
})

// 注册页邮箱输入栏的实时校验事件
$("#register_email").keyup(function() {
     var result=$.check("#register_email",/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
    switch(result){
        case "": $.changePrompt("#register_email_text","邮箱不能为空",false);break;
        case true:$.changePrompt("#register_email_text","符合规范",true);break;
        case false:$.changePrompt("#register_email_text","邮箱不符合规范",false);break;
    }
})

// 注册页电话输入栏的实时校验事件
$("#register_phonenumber").keyup(function() {
     var result=$.check("#register_phonenumber",/^[1-9]{11}$/);
    switch(result){
        case "": $.changePrompt("#register_phonenumber_text","电话不能为空",false);break;
        case true:$.changePrompt("#register_phonenumber_text","符合规范",true);break;
        case false:$.changePrompt("#register_phonenumber_text","电话不符合规范",false);break;
    }
})


//头像上传事件
$("#register_touxiang").change(function(){
    //获得文件路径
    var filepath=$(this).val();
    //获得文件名
    var filename=$(this).val().split("\\").pop();
    //获得文件后缀
    var extname=filename.split(".").pop();
     var result=$.check("#register_touxiang",/^.*?\.(jpg|jpeg|bmp|png)$/);
    switch(result){
        //图片上传，将图片显示出来
        case true:
            $.changePrompt("#register_touxiang_text","符合规范",true);
            //移除图片预览图的不可见
            $("#touxiang_preview").removeClass("invisible");
            //获得文件的路径
            var file = $("#register_touxiang").prop('files')[0];
            //读取文件内容，实现预览
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function (even) {
                $('#touxiang_preview').attr("src", even.currentTarget.result);
            }
            break;
        case false:$.changePrompt("#register_touxiang_text","不是图片格式",false);break;
    }
})


//注册按钮点击
$("#register-btn").click(function() {
    //判断是否通过校验
    if ($("#register_username_text").hasClass("text-success") &&
        $("#register_password_text").hasClass("text-success")&&
        $("#register_email_text").hasClass("text-success")&&
        $("#register_phonenumber_text").hasClass("text-success")) {
        //获得表单
        var form=$("#form_register")[0];
        //构建formData对象
        var formData=new FormData(form);
        //发送ajax请求
        $.ajax({
            url: "http://www.maoka.com:7001/register",
            type: "post",
            data: formData,
            async:false,
            contentType: false, 
            processData: false, 
            success: function (account) {
                if(account=="")
                    $("#login_text").text("注册失败");
                else{
                    alert("你的账号是"+account+",请牢记你的账号");
                    //回到登录页
                    $("#goLogin-btn").click();
                }
            }
        });
    }
})

//改变提示字体的颜色
//elementTrial是被测的元素选择器，text是提示的文本，isSuccess表示其是否正确
$.changePrompt=function(elementTrial,text,isSuccess){
    $(elementTrial).text(text);
    if(isSuccess){
        $(elementTrial).removeClass("text-danger");
        $(elementTrial).addClass("text-success");
    }
    else{
        $(elementTrial).removeClass("text-success");
        $(elementTrial).addClass("text-danger");
    }  
}