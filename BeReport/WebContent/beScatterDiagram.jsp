<%@page import="com.report.domain.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单向导</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        	<div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>制作</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                           
                            
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal" action="display" method="post">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">URL：</label>

                                <div class="col-sm-8">
                                    <input name="url" type="text" class="form-control"> 
                                    <span class="help-block m-b-none">请输入您生成的URL</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-info" type="submit">生成</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>折线图</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="echarts" id="echarts-line-chart"  style="-webkit-tap-highlight-color: transparent; user-select: none; background-color: rgba(0, 0, 0, 0);"><div style="position: relative; overflow: hidden; width: 558px; height: 240px;"><div data-zr-dom-id="bg" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 558px; height: 240px; user-select: none;"></div><canvas width="558" height="240" data-zr-dom-id="0" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 558px; height: 240px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="558" height="240" data-zr-dom-id="1" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 558px; height: 240px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="558" height="240" data-zr-dom-id="_zrender_hover_" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 558px; height: 240px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas></div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

	<script src="js/plugins/echarts/echarts-all.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <!-- Steps -->
    <script src="js/plugins/staps/jquery.steps.min.js"></script>

    <!-- Jquery Validate -->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>

<script>
    $(function () {
        var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
        <% 
        if(request.getAttribute("jdata")!=(null)){
        %>
        var lineoption = <%=request.getAttribute("jdata") %>;
        <%}else{%>
        var lineoption = {};
        <%}%>
        lineChart.setOption(lineoption);
        $(window).resize(lineChart.resize);
        });
    </script>
    
    

</body>

</html>
