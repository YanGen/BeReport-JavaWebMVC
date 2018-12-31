<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">

    <title> BeRport - 制作页面</title>

   

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    
</head>

<body class="fixed-sidebar full-height-layout gray-bg  pace-done" style="overflow:hidden"><div class="pace  pace-inactive">







<div class="pace-progress" data-progress-text="100%" data-progress="99" style="width: 100%;">
  <div class="pace-progress-inner"></div>
</div>

<div class="pace-activity"></div></div>
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;"><div class="sidebar-collapse" style="width: auto; height: 100%;">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">ReportAdmin</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">ReportAdmin
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">分类</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="chatwin.jsp">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">聊天室</span>
                        </a>
                    </li>
                    <li class="line dk"></li>
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">报表展示</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse">
                            <li>
                                <a class="J_menuItem" href="graph_echarts.html">百度ECharts</a>
                            </li>
                            
                        </ul>
                    </li>
                    
                    <li class="line dk"></li>
                    
                    <li>
                    	<a href="#">
                            <i class="fa fa fa-pie-chart"></i>
                            <span class="nav-label">制作报表</span>
                            <span class="fa arrow"></span>
                        </a>
                                                <ul class="nav nav-second-level collapse">
                        
                    
                            <li>
                                <a href="#">百度ECharts <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level collapse" aria-expanded="false" style="height: 0px;">
                                <li>
                                <a class="J_menuItem" href="bePieChart.jsp">饼图</a>
                            </li>
                                   <li>
                                <a class="J_menuItem" href="beHistogram.jsp">柱状图</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="beLineChart.jsp">折线图</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="beFunnelChart.jsp">漏斗图</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="beScatterDiagram.jsp">散点图</a>
                            </li>
                            
                            
                                </ul>
                            </li>
                            </ul>
                            </li>
                            
                    <li class="line dk"></li>
                    
                    <li>
                    	<a href="#">
                            <i class="fa fa-user"></i>
                            <span class="nav-label">我</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse">
                        <%if(request.getSession().getAttribute("LoginUser")==null){ %>
                    <li>
                                <a class="J_menuItem" href="loginwin.jsp">登录</a>
                            </li>
                            <%}else{ %>
                            <li>
                            <a class="J_menuItem" href="bedataitem.jsp">生成我的数据项</a>
                            </li>
                            <li>
                            <a class="J_menuItem" href="timeline">查看我的数据项</a>
                            </li>
                            <li>
                            <a class="J_menuItem" href="logoutservlet?IsIndex=F">退出</a>
                            </li>
                            
                            <%} %>
                  	  </ul>
                  	  
                    </li>
                    
                    
                    
                    
                    

                </ul>
            </div><div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 537.393px;"></div><div class="slimScrollRail" style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.9; z-index: 90; right: 1px;"></div></div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="chatwin.jsp" frameborder="0" data-id="chatwin.jsp" seamless=""></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="js/plugins/pace/pace.min.js"></script>




</body>

</html>
