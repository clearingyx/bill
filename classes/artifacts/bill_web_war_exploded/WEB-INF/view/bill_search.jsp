<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>条件</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#back").click(function(){
                layer.open({type: 2})
                location.href = "base/index.do?openId=${bill.openId}";
            });
        })
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h4>
                选择条件
            </h4>
            <div class="btn-group">
                <button class="btn btn-default">year</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li class="disabled">
                        <a href="#">另一操作</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">其它</a>
                    </li>
                </ul>
            </div>
            <div class="btn-group">
                <button class="btn btn-default">month</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li class="disabled">
                        <a href="#">另一操作</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">其它</a>
                    </li>
                </ul>
            </div>
            <div class="btn-group">
                <button class="btn btn-default">cate</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li class="disabled">
                        <a href="#">另一操作</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">其它</a>
                    </li>
                </ul>
            </div>
            <div class="btn-group">
                <button class="btn btn-default">status</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li class="disabled">
                        <a href="#">另一操作</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">其它</a>
                    </li>
                </ul>
            </div>
            <div class="btn-group">
                <button class="btn btn-default">type</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li class="disabled">
                        <a href="#">另一操作</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">其它</a>
                    </li>
                </ul>
            </div> <button type="button" class="btn btn-default btn-warning btn-block" id="back">按钮</button>
        </div>
    </div>
</div>
</body>
</html>
