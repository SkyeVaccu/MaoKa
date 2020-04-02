//页面对应的按钮id
var dict = {
    "Index": "#index-btn",
    "Community": "#community-btn",
    "Service": "#service-btn",
    "Mall": "#mall-btn",
    "Login": "#loginPage-btn"
}
$.loadNavbarStyle = function(pageName) {
    //获得按钮的id
    var btnId = dict[pageName];
    //给按钮设置效果
    $.each($("#nav-list").find("a"), function(i, n) {
        $(this).addClass("text-secondary");
        $(this).removeClass("text-light");
        $(this).removeClass("disabled");
    })
    $(btnId).removeClass("text-secondary");
    $(btnId).addClass("text-light");
    $(btnId).addClass("disabled");

    //给所有的按钮加上事件
    $.each(dict, function(i, n) {
        $(dict[i]).click(function() {
            window.location.href = "../html/" + i + ".html";
        })
    })

    //在不同页面显示可见性
    if (pageName == "Login")
        $("#user-dropdown").addClass("invisible");
    else {
        $("#user-dropdown").removeClass("invisible");
        //非登录页加载个人信息
        if ($.cookie("id") != undefined) {
            $("#username-span").text($.cookie("username"));
            if ($.cookie("image") == "null")
                $("#touxiang-img").attr("src", "../image/touxiang.png");
            else
                 $("#touxiang-img").attr("src", $.cookie("image"));
        }
    }
}