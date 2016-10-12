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
    <title>确认</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="application/javascript">
        $(function(){
            if("none" == "${msg}"){
                layer.open({
                    content: "没有信息",
                    end: function(index){
                        layer.close(index)
                        layer.open({type:2})
                        location.href = "base/index.do?openId=${openId}";
                    }
                })
            } else if("success" == "${msg}"){
                layer.open({
                    content: "操作成功",
                    time: 2000
                })
            } else if("error" == "${msg}"){
                layer.open({
                    content: "操作失败",
                    time: 2000
                })
            }
            $("#back").click(function(){
                layer.open({type: 2})
                location.href = "base/index.do?openId=${openId}";
            });
        })
        function resure(id, account, billId){
            layer.open({
                content: "确认收到￥" + account +"<br>确定后该订单将结束",
                shadeClose: false,
                btn: ['确定', '取消'],
                yes: function (index) {
                    layer.open({type:2, time:2000})
                    $.post("refund/resure.do", {id: id, billId: billId, RefundStatus: 2}, function (data) {
                        if (data == "success") {
                            layer.open({
                                content: "确认成功"
                            });
                            location.reload();
                        } else {
                            layer.open({
                                content: "操作失败，请重新尝试"
                            });
                        }
                    });
                },
                no: function (index) {
                    layer.close(index);
                }
            });
        }
        function appeal(id, openId){
            layer.open({
                content: "请双方沟通，点击删除订单将会删除该订单",
                btn: ['删除订单', '取消'],
                yes: function(index){
                    location.href = "refund/appeal.do?id=" + id + "&openId=" + openId;
                },
                no: function(index){
                    layer.close(index);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row">
                <div class="col-md-4">
                    <c:forEach var="list" items="${list}">
                        <div class="thumbnail" style="margin-top: 2.5rem">
                            <img alt="150x100" src="${list.photo}" />
                            <div class="caption">
                                <h4>
                                    金额：${list.account}
                                </h4>
                                <h4>
                                    还款人：${list.nickname}
                                </h4>
                                <h6>
                                    时间：<fmt:formatDate value="${list.updateDate}" type="both"/>
                                </h6>
                                <p>
                                    还款备注：${list.remark}
                                </p>
                                <p>
                                    <a class="btn btn-primary" onclick="resure('${list.id}','${list.account}','${list.billId}')">确认收款</a>
                                    <a class="btn" onclick="appeal('${list.id}', '${openId}')">处理异议</a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                    <button type="button" class="btn btn-default btn-block btn-warning"
                            style="margin-bottom: 1rem" id="back">返回</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
