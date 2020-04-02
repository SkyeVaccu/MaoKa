//从url中拿参数
//name是需要获得的键
$.getUrlParam = function(name) { //封装方法
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null)
        return r[2];
    return ""; //返回参数值
}

//进行片段的进入，将对应的片段通过同步的方式引入到container中，保证其加载顺序
//page是片段的url，container是用来装的容器
$.addFragment = function(page, container) {
    $.ajax({
        url: page,
        type: "get",
        async: false,
        success: function(html) {
            $(container).html(html);
        }
    })
}

//删除所有的cookie
$.removeAllCookie = function() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }

}

//校验传进来的对象与正则表达式是否匹配
//elementTrial是被测的元素选择器，regex是正则表达式
$.check = function(elementTrial,regex){
    var value=$(elementTrial).val();
    if (value=="")
        return ""
    else
        return regex.test(value);
}