// 账号输入栏的实时校验事件
$("#account").keyup(function() {
    var account = $("#account").val();
    if ("".equals(account)) {
        $("#account_text").text("账号不能为空");
        $("#account_text").addClass("text-danger");
    } else if (!/^[1-9]{6,}$/.test(account)) {
        $("#account_text").text("账号不符合规范");
        $("#account_text").addClass("text-danger");
    } else {
        $("#account_text").text("符合规范");
        $("#account_text").removeClass("text-success");
    }
})

// 密码输入栏的实时校验事件
$("#password").keyup(function() {
    var password = $("#password").val();
    if ("".equals(password)) {
        $("#password_text").text("密码不能为空");
        $("#password_text").addClass("text-danger");
    } else if (!/^[1-9a-zA-Z]{4,}$/.test(password)) {
        $("#password_text").text("密码不符合规范");
        $("#password_text").addClass("text-danger");
    } else {
        $("#password_text").text("符合规范");
        $("#password_text").removeClass("text-success");
    }
})

//按钮点击
$("#login-btn").click(function() {
    var account = $("#account").val();
    var password = $("#password").val();
    if ($("#account_text").hasClass("text-success") && $("#password_text").hasClass("text-success")) {
        $.post(
            "www.maoka.com:7001/login", 
            { "account": account, "password": password },
            function(data) {
                if ("".equals(data))
                    $("#login_text").text("登录失败");
                else {
                    var returnUrl = $.getUrlParam("returnUrl");
                    $.cookie("token",data);
                    window.location.href=returnUrl;
                }
            }
        )
    }
})

//从url中拿参数
$.getUrlParam=function(name){ //封装方法
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null)
        return r[2];
    return null; //返回参数值
}