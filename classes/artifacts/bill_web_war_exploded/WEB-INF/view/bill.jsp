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
    <title>提交账单</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#perTr tr:odd").addClass("success");
            $("#perTr tr:even").addClass("warning");

            if("${msg}" == "success"){
                layer.open({
                    content: "操作成功，请提醒您的舍友来订阅号对账"
                });
            } else if("${msg}" == "error"){
                layer.open({
                    content: "操作失败，请重试"
                });
            }
            $("#fileupload").uploadPreview( {Img : "ImgPr"});
            $("#fileupload").change(function(){
                $("#ImgPr").attr("style","display:block");
            });
            $("#account").change(function(){
                reg=/^\d{1,10}(\.\d{1,2})?$/;
                if(!reg.test($("#account").val())){
                    $("#account_div").addClass("has-error");
                }
            });
            $("#remark").change(function(){
                if($("#remark").val().length > 255){
                    $("#remark_div").addClass("has-error");
                }
            });

            $("#sub").click(function(){
                var a= document.getElementsByName("person");
                var str="";
                for (var i = 0; i < a.length; i++)
                {
                    if(a[i].checked){
                        str+=a[i].value+",";
                    }
                }
                if(str == ""){
                    layer.open({
                        content: "请选择分担用户"
                    });
                    return ;
                } else {
                    $("#plus_person").val(str);
                }

                reg=/^\d{1,10}(\.\d{1,2})?$/;
                if($("#account").val() == "" || !reg.test($("#account").val()) || ($("#account").val()-0) < 5){
                    layer.open({
                        content: "价格不能为为空或者格式不正确，注意金额大于￥5"
                    });
                    return ;
                }

                if($("h6").html().length == 0){
                    layer.open({
                        content: "你还没有选择消费分类"
                    });
                    return ;
                }

                if($("#remark").val().length > 255){
                    layer.open({
                        content: "备注字数超限了"
                    });
                    return ;
                }
                $("#form").submit();
            })
            $("#back").click(function(){
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
            <table class="table table-striped" id="perTable">
                <thead>
                <tr>
                    <th style="width:30%;">选择</th>
                    <th>昵称</th>
                </tr>
                </thead>
                <tbody id="perTr">
                <c:forEach items="${perlist}" var="list">
                    <tr>
                        <td>
                            <div class="checkbox">
                                <label><input type="checkbox" name="person" value="${list.openId}"/></label>
                            </div>
                        </td>
                        <td style="line-height:4rem">${list.nickname}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form role="form" action="bill/bill_add.do" method="post" id="form" enctype="multipart/form-data">
                <input value="" id="plus_person" name="plus_person" hidden="hidden">
                <input value="${openId}" name="openId" hidden="hidden">
                <input value="" name="cate" hidden="hidden" id="cate">
                <table class="table">
                    <tr>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-default" type="button" style="background-color: white">选择分类</button>
                                <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${catelist}" var="list">
                                        <li>
                                            <a onclick="chooseCate('${list.id}','${list.name}')">${list.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <h6></h6>
                        </td>
                    </tr>
                </table>

                <div class="form-group" id="account_div">
                    <label for="account">one</label>
                    <input class="form-control" id="account" type="text" name="account" placeholder="请输入正确的价格，并且大于￥5"/>
                </div>
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
                <button type="button" class="btn btn-primary btn-block" id="sub">submit</button>
            </form>
            <button type="button" class="btn btn-default btn-block btn-warning" id="back"
                    style="margin-top: 1rem;margin-bottom: 2rem">返回</button>
        </div>
    </div>
</div>

</body>
</html>
