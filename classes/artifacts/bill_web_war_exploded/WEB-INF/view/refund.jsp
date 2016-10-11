<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>还款</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="application/javascript">
        var accounts = 0.0;
        var ids = "";
        $(function() {
            if("${msg}" == "success"){
                layer.open({
                    content: "操作成功，请提醒您的舍友来订阅号对账"
                });
            } else if("${msg}" == "error"){
                layer.open({
                    content: "操作失败，请重试"
                });
            }

            $("#content tr:odd").addClass("success");
            $("#content tr:even").addClass("warning");
            $("#search").click(function () {
                layer.open({
                    type: 2
                })
                $("#form_sub_btn").click();
            })
            $("#refundall").click(function () {
                if (ids != ""){
                    layer.open({
                        content: "还款金额共￥" + accounts +"<br>确定批量还款？批量还款将无法上传还款图片",
                        shadeClose: false,
                        btn: ['确定', '取消'],
                        yes: function (index) {
                            $.post("refund/batch_refund.do", {bill_ids: ids}, function (data) {
                                if (data == "success") {
                                    layer.open({
                                        content: "还款成功，请提醒您的舍友您已经还款"
                                    });
                                    $("#form_sub_btn").click();
                                } else {
                                    layer.open({
                                        content: "有订单操作失败，刷新后再试"
                                    });
                                }
                            });
                        },
                        no: function (index) {
                            layer.close(index);
                        }
                    });
                } else {
                    layer.open({
                        content: "最少选择一个"
                    });
                }
            });
            $("#back").click(function(){
                location.href = "base/index.do?openId=${openId}";
            });
        })

        function statue(num){
            $("#refundStatus").val(num);
            $("#status").html(num);
        }
        function year(num){
            $("#now_year").val(num);
            $("#year").html(num + "年");
        }
        function month(num){
            if (num == "") {
                $("#now_month").val("all");
                $("#month").html("全部");
            } else {
                $("#now_month").val(num);
                $("#month").html(num + "月");
            }
        }

        function changeprice(obj, id, account){
            if(obj.checked){
                accounts += parseFloat(account);
                ids += id+",";
                $("#refundall").html("选择还款 - ￥" + accounts);
            } else {
                accounts = parseFloat(accounts) - parseFloat(account);
                accounts = Math.round(accounts,2);
                ids = ids.replace(id+",","");
                $("#refundall").html("选择还款 - ￥" + accounts);
            }
        }
        function refundone(id){
            location.href="refund/jump_refund_one.do?id=" + id +
                    "&openId=${openId}&refundStatus=${refund.refundStatus}&type=${refund.type}"+
                    "&now_year=${now_year}&now_month=${now_month}";
        }
    </script>
</head>
<body>
<form action="refund/search.do" method="post">
    <input type="hidden" value="${openId}" name="openId" >
    <input type="hidden" value="${refund.refundStatus}" id="refundStatus" name="refundStatus" >
    <input type="hidden" value="${refund.type}" id="type" name="type" >
    <input type="hidden" value="${now_year}" id="now_year" name="now_year" >
    <input type="hidden" value="${now_month}" id="now_month" name="now_month" >
    <input type="submit" style="display: none" id="form_sub_btn">
</form>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h4 class="text-warning">
                总账：${account}
            </h4>
            <table class="table table-condensed" style="text-align: center">
                <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" id="status">
                            <c:if test="${refund.refundStatus == '0'}">0</c:if>
                            <c:if test="${refund.refundStatus == '1'}">1</c:if>
                            <c:if test="${refund.refundStatus == '2'}">2</c:if>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="status">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" onclick="statue('0')">0</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" onclick="statue('1')">1</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" id="year">${now_year}年
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="year">
                                <c:forEach var="list" items="${yearList}">
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" onclick="year('${list}')">${list}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="month" data-toggle="dropdown">${now_month}月
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="month">
                                <c:forEach var="list" items="${monthList}">
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" onclick="month('${list}')">${list}月</a>
                                    </li>
                                </c:forEach>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" onclick="month('')">全部</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <button type="button" class="btn btn-warning btn-sm" id="search">搜索</button>
                    </td>
                </tr>
                </tbody>
            </table>



            <table class="table table-striped">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>p</th>
                    <th>admin</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content">
                    <c:forEach var="list" items="${list}">
                        <tr>
                            <td>
                                <div class="checkbox">
                                    <label><input type="checkbox" name="choose"
                                                  onclick="changeprice(this,'${list.id}','${list.account}')"/></label>
                                </div>
                            </td>
                            <td style="line-height:4rem">
                                ${list.account}
                            </td>
                            <td style="line-height:4rem">
                                ${list.nickname}
                            </td>
                            <td style="line-height:4rem">
                                <button type="button" class="btn btn-sm btn-success"
                                        onclick="refundone('${list.id}')">refund</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="button" class="btn btn-default btn-block btn-primary" id="refundall">选择还款</button>
            <button type="button" class="btn btn-default btn-block btn-warning" id="back"
                    style="margin-bottom: 2rem">返回</button>
        </div>
    </div>
</div>
</body>
</html>
