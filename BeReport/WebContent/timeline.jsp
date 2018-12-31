<%@page import="java.util.List"%>
<%@page import="com.report.domain.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 时间轴</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>数据项列表</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="timeline.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="timeline.html#">选项1</a>
                                </li>
                                <li><a href="timeline.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
					
                    <div class="ibox-content timeline">
                    <% 
                    List<Report> reportObjectList = (List<Report>)request.getAttribute("reportObjectList");
					for(Report report : reportObjectList){
						%>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i> <%=report.getTday() %>
                                    <br>
                                    <small class="text-navy"><%=report.getTsecond() %></small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>图表类型:<%=report.getType() %></strong>
                                    </p>
                                    <br>
                                    <p><a href="/BeReport/display?id=<%=report.getId() %>&type=<%=report.getType()%>&username=<%=report.getUsername()%>"><%=report.getTitle()%>（点击前往~）</a></p>
                                </div>
                            </div>
                        </div>
					<%
					} 
					%>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>



    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <!-- Peity -->
    <script src="js/demo/peity-demo.js"></script>

    
    

</body>

</html>
