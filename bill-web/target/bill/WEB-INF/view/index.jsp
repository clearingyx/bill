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
    <title>首页</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
</head>
<body>
    <table width="100%">
        <tr>
            <td style="width:50%;background-color: #40AFFE; line-height: 8rem" id="house">house</td>
            <td style="width:50%;background-color: aquamarine" id="sub_bill">sub_bill</td>
        </tr>
        <tr>
            <td style="width:50%;background-color: darkgoldenrod; height: 8rem" id="refund_bill">refund_bill</td>
            <td style="width:50%;background-color: indianred" id="my_bill">my_bill</td>
        </tr>
        <tr>
            <td style="width:50%;background-color: honeydew; height: 8rem" id="history">history_bill_search</td>
            <td style="width:50%;background-color: blueviolet" id="not_bill">other_not_refund_search</td>
        </tr>
        <tr>
            <td style="width:50%;background-color: limegreen; height: 8rem" id="info">info</td>
            <td style="width:50%;background-color: #999999" id="option">option</td>
        </tr>
    </table>
</body>
<script type="application/javascript">
    $(function(){
        $("#sub_bill").click(function(){
            layer.open({
                type: 2
            })
            $.post("house/personInHouseRepeat.do",{openId: '${openId}'}, function(data){
                if(data == "success"){
                    layer.closeAll();
                    layer.open({
                       content: "您还没有一个合租屋，请先绑定你的屋子"
                    })
                } else if (data == "repeat"){
                    location.href = "bill/sub_bill.do?openId=${openId}";
                }
            });
        });
        $("#house").click(function(){
            layer.open({
                type: 2
            })
            location.href = "/house/house.do?openId=${openId}";
        });
        $("#refund_bill").click(function(){
            layer.open({
                type: 2
            })
            location.href = "refund/search.do?openId=${openId}&refundStatus=0&type=0";
        })
        $("#my_bill").click(function(){
            layer.open({
                type: 2
            })
            location.href = "refund/confirmBill.do?openId=${openId}";
        })
        $("#info").click(function(){
            layer.open({
                type: 2
            })
            location.href = "person/person_info.do?openId=${openId}";
        })
        $("#not_bill").click(function(){
            layer.open({
                type: 2
            })
            location.href = "refund/not_refund.do?openId=${openId}";
        })
        $("#option").click(function(){
            layer.open({
                type: 2
            })
            location.href = "person/person_page.do?openId=${openId}";
        })
        $("#history").click(function(){
            layer.open({
                type: 2
            })
            location.href = "bill/search_condition.do?openId=${openId}";
        })
    })
</script>
</html>
