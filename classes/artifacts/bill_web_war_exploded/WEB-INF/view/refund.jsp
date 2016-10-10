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
    <title>Title</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-browser.js"></script>
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    <script type="text/javascript" src="plugin/layer_mobile/layer.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="application/javascript">
        $(function() {
            $("#content tr:odd").addClass("success");
            $("#content tr:even").addClass("warning");
        })
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <table class="table table-condensed" style="text-align: center">
                <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" id="status">状态
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="status">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">0</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">1</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="#">2</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" id="year">年
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="year">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">Java</a>
                                </li>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">其他</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="month" data-toggle="dropdown">月份
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="month">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">1月</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">2月</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="">3月</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>



            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="content">
                <tr>
                    <td>
                        <div class="checkbox">
                            <label><input type="checkbox" name="person" value=""/></label>
                        </div>
                    </td>
                    <td style="line-height:4rem">
                        10.5
                    </td>
                    <td style="line-height:4rem">
                        admin
                    </td>
                    <td style="line-height:4rem">
                        <button type="button" class="btn btn-sm btn-success">refund</button>
                        <button type="button" style="margin-left: 5%" class="btn btn-sm btn-info">info</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <button type="button" class="btn btn-default btn-block btn-primary">整体</button>
        </div>
    </div>
</div>
</body>
</html>
