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
    <title>没还款</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="application/javascript">
        $(function(){
            $("#perTr tr:odd").addClass("success");
            $("#perTr tr:even").addClass("warning");
            if("none" == "${msg}"){
                layer.open({
                    content: "没有信息"
                })
            }
            $("#back").click(function(){
                layer.open({type: 2})
                location.href = "base/index.do?openId=${openId}";
            });
        })
        function desc(billId){
            layer.open({type: 2})
            location.href="bill/bill_info.do?id=" + billId;
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>未还款人</th>
                            <th>金额</th>
                            <th>创建时间</th>
                        </tr>
                    </thead>
                    <tbody id="perTr">
                        <c:forEach var="list" items="${list}">
                            <tr onclick="desc('${list.billId}')">
                                <td>
                                    ${list.nickname}
                                </td>
                                <td>
                                    ￥${list.account}
                                </td>
                                <td>
                                    <fmt:formatDate value="${list.updateDate}" type="both"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="btn btn-default btn-block btn-warning" style="margin-bottom: 1rem" id="back">返回</button>
            </div>
        </div>
    </div>
</body>
</html>
