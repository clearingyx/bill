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
    <title>测试系统</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
</head>
<body>
    ${openId}
    <button id="bill_person">点击选择分账人</button>
</body>
<script type="application/javascript">
    $(function(){
        $("#bill_person").click(function(){
            layer.open({
                type: 1,
                shade: 'background-color: rgba(0,0,0,.3)', //自定义遮罩的透明度
                content: '<a onclick="c()">www.baidu.com</a>',
                success: function(elem){
                    alert('弹出')
                    console.log(elem);
                },
                btn: ['提交', '返回'],
                yes: function(index){
                    alert('点击了提交')
                    layer.close(index)
                },
                no: function(index){
                    alert('点击了返回')
                    layer.close(index)
                },
                end: function(){
                    alert("关闭弹出框");
                }
            })
        });
    })
    function c(){
        alert("c");
    }
</script>
</html>
