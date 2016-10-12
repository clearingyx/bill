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
    <title>单个订单</title>
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
                <div class="row">
                    <div class="col-md-4">
                        <div class="thumbnail" style="margin-top: 2.5rem">
                            <img alt="150x100" src="${bill.img}" />
                            <div class="caption">
                                <h4>
                                    订单总金额：${bill.account}
                                </h4>
                                <p>
                                    订单备注：${refund.remark}
                                </p>
                                <p>
                                    订单时间：<fmt:formatDate value="${bill.createDate}" type="both"/>
                                </p>
                                <p>
                                    <button type="button" class="btn btn-default btn-block btn-warning" id="back">返回</button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
