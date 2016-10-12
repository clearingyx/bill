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
    <title>用户</title>
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
                location.href = "base/index.do?openId=${person.openId}";
            });
            $("#sub").click(function(){
                var new_name = $("#nickname").val();
                if(new_name != "" && new_name != '${person.nickname}'){
                    $.post("person/judge_name.do", {nickname: new_name, openId:'${person.openId}'}, function(data){
                        if(data == "success"){
                            layer.open({
                                content: "确定修改昵称？合租人只能通过昵称搜索到您。<br>电话为备选，不做验证",
                                shadeClose: false,
                                btn: ['确定', '取消'],
                                yes: function (index) {
                                    layer.open({
                                        type:2
                                    });
                                    $("#form").submit();
                                },
                                no: function (index) {
                                    layer.close(index);
                                }
                            });
                        } else {
                            layer.open({
                                content: "您的昵称重复了"
                            })
                        }
                    })
                } else {
                    layer.open({
                        content: "请填写不重复的昵称"
                    })
                }
            })
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
                            <div class="caption">
                                <h4>
                                    用户名：${person.nickname}
                                </h4>
                                <p>
                                    用户电话：${person.phone}
                                </p>
                                <p>
                                <form role="form" action="person/update.do" method="post" id="form">
                                    <input value="${person.openId}" name="openId" id="openId" hidden="hidden">

                                    <div class="form-group">
                                        <label for="nickname">name</label>
                                        <input class="form-control" id="nickname" type="text" name="nickname"/>
                                    </div>
                                <div class="form-group">
                                    <label for="phone">phone</label>
                                    <input class="form-control" id="phone" type="text" name="phone"/>
                                </div>

                                    <button type="button" class="btn btn-primary btn-block" id="sub">确定提交</button>
                                </form>
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
