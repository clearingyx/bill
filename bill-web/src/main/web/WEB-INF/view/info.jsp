<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>${month}&nbsp;&nbsp;&nbsp;&nbsp;统计</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#back").click(function(){
                layer.open({type: 2});
                location.href = "base/index.do?openId=${openId}";
            });
        })
</script>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="alert alert-dismissable alert-success" style="margin-top: 1rem" id="admin">

                    <h4>
                        支出（不包括未收款）
                    </h4>
                    <strong>当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${month_b} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_b}
                    <%--<button type="button" class="btn btn-success btn-xs" style="float: right">查看</button>--%>
                </div>
                <div class="alert alert-dismissable alert-info" id="person">
                    <h4>
                        欠款
                    </h4>
                    <strong>当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${month_r} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_r}
                </div>
                <div class="alert alert-dismissable alert-warning" id="del_admin">
                    <h4>
                        未收款
                    </h4>
                    <strong>当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${month_n} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_n}
                </div>
                <div class="alert alert-dismissable alert-danger" id="del_person">

                    <h4>
                        分类支出
                    </h4>
                    <strong>分类：吃&nbsp;&nbsp;当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_eat} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${month_eat}<br><br>
                    <strong>分类：穿&nbsp;&nbsp;当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_wear} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${month_wear}<br><br>
                    <strong>分类：住&nbsp;&nbsp;当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_live} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_live}<br><br>
                    <strong>分类：用&nbsp;&nbsp;当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_do} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_do}<br><br>
                    <strong>分类：行&nbsp;&nbsp;当月&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_move} <br>
                    <strong>总&nbsp;&nbsp;&nbsp;&nbsp;</strong>￥${sum_move}<br>
                </div>
                <button type="button" class="btn btn-default btn-block btn-warning" id="back">返回</button>
            </div>
        </div>
    </div>
</body>
</html>
