<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>house</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <style type="text/css">
        ul {width: 80%; margin-left: 2%; line-height: 1rem; margin-bottom: 1rem; list-style:none;}
        ul li input {height: 2rem}
    </style>
</head>
<body>
    <div id="admin" style="width: 100%;height: 4rem;background-color: darkorange;margin-bottom: 1rem">
        admin
    </div>
    <div id="person" style="width: 100%;height: 4rem;background-color: khaki">
        person
    </div>
</body>
<script type="application/javascript">
    $(function(){
        $("#admin").click(function(){
            var content = '<ul id="info"><li>必填：<input value="" placeholder="请填写昵称" name="nickname" id="nickname"/></li>'+
                    '<li>选填：<input value="" placeholder="请填写电话" name="phone" id="phone"/></li></ul>';
            var power = "";
            if(this.id == "admin") power = "super";
            else power = "person";

            layer.open({
                type: 1,
                shade: 'background-color: rgba(0,0,0,.3)', //自定义遮罩的透明度
                content: content,
                btn: ['提交', '取消'],
                yes: function(index){
                    var nickname = $("#nickname").val();
                    var phone = $("#phone").val();
                    if(nickname == "") {
                        layer.open({content: "您的昵称填错了，不能有空格或是乱码"});
                        return ;
                    }

                    $.post("bill/house_add.do",{openId: "${openId}", power: "sup", nickname: nickname, phone: phone},function(data){
                        if(data == true) {
                            layer.open({
                               content: "您现在是承租人了"
                            });
                            layer.close(index)
                        } else {
                            if(data == "error")
                                layer.open({content: "您的昵称填错了，不能有空格或是乱码"});
                            else{
                                layer.open({
                                    content: "报错了，重新提交试试？"
                                });
                            }
                        }
                    });
                },
                no: function(index){
                    layer.close(index)
                }
            })
        });
    })
</script>
</html>
