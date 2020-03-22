window.onload=function(){
    $.each($("#nav-list").find("a"),function(i,n){
        $(this).addClass("text-secondary");
       $(this).removeClass("loginPage-light");
       $(this).removeClass("disabled");
    })
    $("#loginPage-btn").removeClass("text-secondary");
    $("#loginPage-btn").addClass("text-light");
    $("#loginPage-btn").addClass("disabled");

    $("#loginPage-btn").click(function(){
        window.location.href="/html/Login.html";
    })
    $("#index-btn").click(function(){
        window.location.href="/html/Index.html";
    })
}

// 账号输入栏的实时校验事件
$("#account").keyup(function() {
    var account = $("#account").val();
    if (""==account) {
        $("#account_text").text("账号不能为空");
        $("#account_text").removeClass("text-success");
        $("#account_text").addClass("text-danger");
    } else if (!/^[1-9]{6,}$/.test(account)) {
        $("#account_text").text("账号不符合规范");
        $("#account_text").removeClass("text-success");
        $("#account_text").addClass("text-danger");
    } else {
        $("#account_text").text("符合规范");
        $("#account_text").removeClass("text-danger");
        $("#account_text").addClass("text-success");
    }
})

// 密码输入栏的实时校验事件
$("#password").keyup(function() {
    var password = $("#password").val();
    if (""==password) {
        $("#password_text").text("密码不能为空");
        $("#password_text").removeClass("text-success");
        $("#password_text").addClass("text-danger");
    } else if (!/^[1-9a-zA-Z]{4,}$/.test(password)) {
        $("#password_text").text("密码不符合规范");
        $("#password_text").removeClass("text-success");
        $("#password_text").addClass("text-danger");
    } else {
        $("#password_text").text("符合规范");
        $("#password_text").removeClass("text-danger");
        $("#password_text").addClass("text-success");
    }
})

//按钮点击
$("#login-btn").click(function() {
    var account = $("#account").val();
    var password = $("#password").val();
    if ($("#account_text").hasClass("text-success") && $("#password_text").hasClass("text-success")) {
        $.post(
            "http://www.maoka.com:7001/login", 
            { "account": account, "password": password },
            function(data) {
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
        )
    }
})

$("#weibo-btn").click(function(){
	window.location.href="https://api.weibo.com/oauth2/authorize?client_id=2415007340&response_type=code&redirect_uri=http://www.maoka.com:7001/threeLogin"
})

//从url中拿参数
$.getUrlParam=function(name){ //封装方法
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null)
        return r[2];
    return ""; //返回参数值
}