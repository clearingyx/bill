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
    <title>单个订单</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#fileupload").uploadPreview( {Img : "ImgPr"});
            $("#fileupload").change(function(){
                $("#ImgPr").attr("style","display:block");
            });
            $("#remark").change(function(){
                if($("#remark").val().length > 255){
                    $("#remark_div").addClass("has-error");
                }
            });

            $("#sub").click(function(){
                if($("#remark").val().length > 255){
                    layer.open({
                        content: "备注字数超限了"
                    });
                    return ;
                }
                layer.open({
                    content: "还款金额共￥${refund.account}",
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
            })
            $("#back").click(function(){
                layer.open({type: 2})
                location.href = "base/index.do?openId=${openId}";
            });
        })
        function chooseCate(id, name){
            $("h6").html(name);
            $("#cate").val(id);
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row">
                    <div class="col-md-4">
                        <div class="thumbnail" style="margin-top: 2.5rem">
                            <img alt="150x100" src="${refund.img}" />
                            <div class="caption">
                                <h4>
                                    订单金额：${refund.account}
                                </h4>
                                <h4>
                                    订单发起人：${refund.nickname}
                                </h4>
                                <p>
                                    订单备注：${refund.remark}
                                </p>
                                <p>
                                    <form role="form" action="refund/refund_one.do" method="post" id="form" enctype="multipart/form-data">
                                        <input value="${old_refund.openId}" name="openId" hidden="hidden">
                                        <input value="${old_refund.refundStatus}" name="refundStatus" hidden="hidden">
                                        <input value="${old_refund.type}" name="type" hidden="hidden">
                                        <input value="${now_year}" name="now_year" hidden="hidden">
                                        <input value="${now_month}" name="now_month" hidden="hidden">
                                        <input value="${old_refund.id}" name="id" hidden="hidden">

                                        <div class="form-group" id="remark_div">
                                            <label for="remark">two</label>
                                            <input class="form-control" id="remark" type="text" name="remark" placeholder="备注不超过255个字符"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="file" style="display:none" id="fileupload" name="file">
                                            <div class="input-append">
                                                <button type="button" class="btn btn-primary" onclick="$('#fileupload').click();">上传图片</button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <img id="ImgPr" class="img-thumbnail" src="" style="display: none">
                                        </div>
                                        <button type="button" class="btn btn-primary btn-block" id="sub">确定还款</button>
                                    </form>
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
