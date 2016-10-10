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
    <title>house</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <style type="text/css">
        ul {width: 80%; margin-left: 2%; line-height: 1rem; margin-bottom: 1rem; list-style:none;}
        ul li input {height: 2rem}
    </style>
</head>
<body>
    <div id="admin" style="width: 100%;height: 4rem;background-color: darkorange; margin-bottom: 1rem">
        admin
    </div>
    <div id="person" style="width: 100%;height: 4rem;background-color: khaki; margin-bottom: 1rem">
        person
    </div>
    <div id="del_admin" style="width: 100%;height: 4rem;background-color: #999999; margin-bottom: 1rem">
        del_admin
    </div>
    <div id="del_person" style="width: 100%;height: 4rem;background-color: lightblue">
        del_person
    </div>
</body>
<script type="application/javascript">
    $(function(){
        $("#admin").click(function(){
            var content = '<ul><li>必填：<input value="" placeholder="请填写昵称" name="nickname" id="nickname"/></li>'+
                    '<li>选填：<input value="" placeholder="请填写电话" name="phone" id="phone"/></li></ul>';
            var flag = false;
            //如果已经是承租人，则直接弹窗提醒。
            $.post("house/personInHouseRepeat.do", {openId: "${openId}"}, function(data){
                if(data == "repeat"){
                    layer.open({content: "您已经是承租人或是租了房子了！"});
                    return ;
                } else if (data == "success"){
                    layer.open({
                        title: "创建承租人",
                        type: 1,
                        shade: 'background-color: rgba(0,0,0,.3)', //自定义遮罩的透明度
                        content: content,
                        btn: ['提交', '取消'],
                        yes: function(index){
                            var nickname = $("#nickname").val();
                            var phone = $("#phone").val();

                            if(nickname == "") {
                                layer.open({content: "昵称不能为空，其他人只能通过这个找到您！"});
                                return ;
                            }

                            $.post("house/house_add.do",{openId: "${openId}", power: "sup", nickname: nickname, phone: phone},function(data){
                                if(data == true) {
                                    layer.open({content: "操作成功，你现在是承租人了"});
                                    layer.close(index)
                                } else {
                                    if(data == "error") {
                                        layer.open({content: "您的昵称填错了，不能有空格或是乱码"});
                                    }else if(data == "repeat"){
                                        layer.open({content: "您的昵称重复了"});
                                    }else{
                                        layer.open({
                                            content: "报错了，重新提交试试？"
                                        });
                                    }
                                }
                            });
                        },
                        no: function(index){
                            layer.close(index)
                        }
                    })
                }
            });
        });

        $("#person").click(function(){
            var content = '<ul><li>必填：<input value="" placeholder="请填写昵称" name="nickname" id="nickname"/></li>'+
                    '<li>选填：<input value="" placeholder="请填写电话" name="phone" id="phone"/></li></ul>';

            var flag = false;
            //如果已经租了房子，则直接弹窗提醒。
            $.post("house/personInHouseRepeat.do", {openId: "${openId}"}, function(data){
                if(data == "repeat"){
                    layer.open({content: "您已经是承租人或是租了房子了！"});
                    return ;
                } else if (data == "success"){
                    layer.open({
                        title: "搜索承租人",
                        type: 1,
                        shadeClose: false,
                        shade: 'background-color: rgba(0,0,0,.3)', //自定义遮罩的透明度
                        content: '<ul style="margin-left: -10%"><li><input id="adminname" placeholder="输入承租人昵称"></li></ul>',
                        btn: ['提交', '取消'],
                        yes: function (index) {
                            var nickname = $("#adminname").val();

                            if (nickname == "") {
                                layer.open({content: "搜索昵称不能为空！"});
                                return;
                            }

                            $.post("house/search_admin.do", {nickname: nickname}, function (data) {
                                if (data == false) {
                                    layer.open({content: "没有找到该用户！"});
                                } else if (data == "error") {
                                    layer.open({
                                        content: "报错了，重新提交试试？"
                                    });
                                } else {
                                    var title = "承租人：" + data.nickname;
                                    layer.open({
                                        shadeClose: false,
                                        title: title,
                                        content: content,
                                        btn: ['提交', '取消'],
                                        yes: function(index1){
                                            var nickname = $("#nickname").val();
                                            var phone = $("#phone").val();

                                            if(nickname == "") {
                                                layer.open({content: "昵称不能为空，其他人只能通过这个找到您！"});
                                                return ;
                                            }

                                            $.post("house/house_add.do",{openId: data.openId,
                                                        power: "person", nickname: nickname, phone: phone, myOpenId: "${openId}"},
                                                    function(data){
                                                        if(data == true) {
                                                            layer.open({content: "操作成功，你现在是合租人了"});
                                                            layer.close(index);
                                                            layer.close(index1);
                                                        } else {
                                                            if(data == "error"){
                                                                layer.open({content: "您的昵称填错了，不能有空格或是乱码"});
                                                            }else if(data == "repeat"){
                                                                layer.open({content: "您的昵称重复了"});
                                                            }else{
                                                                layer.open({
                                                                    content: "报错了，重新提交试试？"
                                                                });
                                                            }
                                                        }
                                                    });
                                        },
                                        no: function(index1){
                                            layer.close(index1);
                                        }
                                    });

                                }
                            });
                        },
                        no: function (index) {
                            layer.close(index)
                        }
                    })
                }
            });
        })

        $("#del_admin").click(function(){
            layer.open({
                content: "确定解绑？解绑会造成该合租房解散",
                shadeClose: false,
                btn: ['确定', '取消'],
                yes: function(index){
                    $.post("house/unbundling.do",{openId, '${openId}', power: 'admin'},function(data){
                        if(data == "success"){
                            layer.open({
                                content: "解绑成功"
                            });
                        } else {
                            layer.open({
                                content: "你确定你是承租人？没有找到你的信息"
                            });
                        }
                    });
                },
                no: function(){
                    layer.close(index);
                }
            });
        })

        $("#del_person").click(function(){
            layer.open({
                content: "确定解绑？你将会被你的合租屋移除",
                shadeClose: false,
                btn: ['确定', '取消'],
                yes: function(index){
                    $.post("house/unbundling.do",{openId, '${openId}', power: 'person'},function(data){
                        if(data == "success"){
                            layer.open({
                                content: "解绑成功"
                            });
                        } else {
                            layer.open({
                                content: "你确定租了房子了？没有找到你的信息"
                            });
                        }
                    });
                },
                no: function(){
                    layer.close(index);
                }
            });
        })
    })
</script>
</html>
