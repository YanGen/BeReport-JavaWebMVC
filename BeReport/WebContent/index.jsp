<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Home</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="HomeStyle/css/normalize.css">
        <link rel="stylesheet" href="HomeStyle/css/font-awesome.css">
        <link rel="stylesheet" href="HomeStyle/css/bootstrap.min.css">
        <link rel="stylesheet" href="HomeStyle/css/templatemo-style.css">
        <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
        <script src="HomeStyle/js/vendor/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
    
    
    
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
        <div class="site-bg"></div>
        <div class="site-bg-overlay"></div>



        <!-- TOP HEADER -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <p class="phone-info">BeReport <a href="#">1.0</a></p>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="social-icons">
                        
                        <%
                            if(request.getSession().getAttribute("LoginUser")!=null){
                            %>
                            <li><a href="#" title="邮箱:<%=request.getSession().getAttribute("UserEmail") %>" target="邮箱:<%=request.getSession().getAttribute("UserEmail") %>">欢迎你!<%=request.getSession().getAttribute("LoginUser") %></a></li>
                                <li><a class="btn btn-primary" href="logoutservlet?IsIndex=T">退出</a> </li>
                            
                            <%}else{ %>
                                <li><a data-toggle="modal" class="btn btn-primary" href="index.jsp#modal-form">登录</a> </li>
                                <%} %>
                                
                            
                            <li> <a href="#" title="访问量" target="访问量" class="fa  fa-eye">2</a></li>
                            
                                <!-- .... -->
                            
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- .top-header -->




  <div id="modal-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 b-r">
                            <h3 class="m-t-none m-b">登录</h3>
                            <form action="loginservlet?IsIndex=T" method="post" role="form">
                                <div class="form-group">
                                    <label>用户名：</label>
                                    <input type="text" name="username" placeholder="请输入用户名" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>密码：</label>
                                    <input type="password" name="password" placeholder="请输入密码" class="form-control">
                                </div>
                                <input style="display:None;" name="IsIndex" value="T"/>
                                <div>
                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>登录</strong>
                                    </button>
                                   </div>
                            </form>
                        </div>
                        <div class="col-sm-6">
                            <h4>还没用户账户？</h4>
                            <p>您可以注册一个账户</p>
                            <p class="text-center">
                                <a href="register.jsp"><i class="fa fa-sign-in big-icon"></i></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>






        <div class="visible-xs visible-sm responsive-menu">
            <a href="#" class="toggle-menu">
                <i class="fa fa-bars"></i> Show Menu
            </a>
            <div class="show-menu">
                <ul class="main-menu">
                    <li>
                        <a class="show-1 reportbutton" href="#"><i class="fa fa-bar-chart-o"></i>Report</a>
                    </li>
                    <li>
                        <a class="show-2 aboutbutton" href="#"><i class="fa fa-user"></i>About Us</a>
                    </li>
             
                    <li>
                        <a class="show-5 contactbutton" href="#"><i class="fa fa-envelope"></i>Contact</a>
                    </li>
                </ul>
            </div>
        </div>
		<div class="copyrights">Collect from </div>
        <div class="container" id="page-content">
            <div class="row">


                
                <div class="col-md-9 col-sm-12 content-holder">
                    <!-- CONTENT -->
                    <div id="menu-container">
                        
                        <div class="logo-holder logo-top-margin">
                            <a href="#" class="site-brand"><img src="HomeStyle/images/logo.png" alt=""></a>
                        </div>
                        
                        
                        <div id="menu-1" class="homepage home-section text-center">
                            <div class="welcome-text">
                                <h2>Hello, Welcome to <strong>BeReport</strong>
                                
                                 </h2>
                                
                               
          						
                                <form action="#" method="get" class="subscribe-form">
                                    <div class="row">
                                        <fieldset class="col-md-offset-2 col-md-6">
                                            <input name="subscribe-email" type="email" class="email" id="subscribe-email" placeholder="Write your email here..">
                                        </fieldset>
                                        <fieldset class="col-md-4 button-holder">
                                            <input name="submit" type="submit" class="button default" id="submit" value="Submit">
                                        </fieldset>
                                    </div>
                                    <p class="subscribe-text">If it's convenient, please subscribe to your latest updated email!
</p>
                                    
                                </form>
                                
                                <a href="report.jsp">
                               <button class="btn btn-sm btn-primary pull-right m-t-n-xs"><h4><strong><span class="green">Go BeReport</span> <span class="orange"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span><span class="orange"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span><span class="orange"><i class="fa fa-angle-double-right" aria-hidden="true">
                               </i></span></strong></h4></button>
                               </a>
                                
                                
                            </div>
                        </div>

                        <div id="menu-2" class="content about-section">
                            <div class="row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="box-content profile">
                                        <h3 class="widget-title">Learn about us</h3>
                                        <div class="profile-thumb">
                                            <img src="HomeStyle/images/8.jpg" alt="">
                                        </div>
                                        <div class="profile-content">
                                            <h5 class="profile-name">BeReport</h5>
                                            <span class="profile-role">College students' entrepreneurs</span>
                                            <p>We are a team from the Sun Yat-Sen university nanfang college, we are committed to create convenient,
                                             for more people through this one, as long as you have the data, what do you want to report, what we can give you to report output.</p>
                                            <p>产品总监:<b>陈天鹏</b></p>
                                            <p>茶水间主任:<b>江剑艺</b></p>
                                            <p>前端工程师:<b>陈泽楠</b></p>
                                            <p>后端工程师:<b>陈昱熹</b></p>
                                            <p>总工程师：<b>黄杨耿(CEO)</b></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <div class="box-content">
                                        <h3 class="widget-title">Background</h3>
                                        <p>Now that the technology is getting more and more advanced, it's becoming very convenient to make things, basically what you want to do, the software you want on the Internet, to meet your requirements.</p>
                                        <div class="about-social">
                                            <ul>
                                                <li><a href="#" class="fa fa-facebook"></a></li>
                                                <li><a href="#" class="fa fa-twitter"></a></li>
                                                <li><a href="#" class="fa fa-linkedin"></a></li>
                                                <li><a href="#" class="fa fa-dribbble"></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                           <div class="row">
                                <div class="col-md-5 col-sm-5">
                                    <div class="box-content">
                                        <h3 class="widget-title">Our Studio</h3>
                                        <div class="project-item">
                                            <img src="HomeStyle/images/7.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Great Nature Capture</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-7 col-sm-7">
                                    <div class="box-content">
                                        <h3 class="widget-title">Our Technical Skills</h3>
                                        <ul class="progess-bars">
                                            <li>
                                                <span>HTML CSS 80%</span>
                                                <div class="progress">
                                                    <div class="progress-bar" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%;"></div>
                                                </div>
                                            </li>
                                            <li>
                                                <span>PHOTOSHOP 95%</span>
                                                <div class="progress">
                                                    <div class="progress-bar" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" style="width: 95%;"></div>
                                                </div>
                                            </li>
                                            <li>
                                                <span>WORDPRESS 70%</span>
                                                <div class="progress">
                                                    <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;"></div>
                                                </div>
                                            </li>
                                            <li>
                                                <span>PHP mySQL 50%</span>
                                                <div class="progress">
                                                    <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;"></div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="menu-3" class="content gallery-section">
                            <div class="box-content">
                                <h3 class="widget-title">Past Projects</h3>
                                <div class="row">
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/1.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Great Nature Capture</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/2.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Another Image Caption</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/3.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Great Nature Capture</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/4.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Another Image Caption</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/5.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Great Nature Capture</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="project-item">
                                            <img src="HomeStyle/images/6.jpg" alt="">
                                            <div class="project-hover">
                                                <h4>Another Image Caption</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="project-pages">
                                    <ul>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">...</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div id="menu-4" class="content contact-section">
                            <div class="row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="box-content">
                                        <h3 class="widget-title">Send Us A Message</h3>
                                        <form action="#" class="contact-form">
                                            <fieldset>
                                                <input type="text" class="name" id="name" placeholder="Name...">
                                            </fieldset> 
                                            <fieldset>
                                                <input type="email" class="email" id="email" placeholder="Email...">
                                            </fieldset> 
                                            <fieldset>
                                                <input type="text" class="subject" id="subject" placeholder="Subject...">
                                            </fieldset>
                                            <fieldset>
                                                <textarea name="message" id="message" cols="30" rows="4" placeholder="Message.."></textarea>
                                            </fieldset>
                                            <fieldset>
                                                <input type="submit" class="button" id="button" value="Send Message">
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <div class="box-content">
                                        <h3 class="widget-title">Stay In Touch</h3>
                                        <div><p>电话：0663-88888888 </p>
                                        <p>传真：0663-88888888</p>
                                        <p>地址：广东省广州市从化区温泉镇温泉大道882号</p>
                                        <p>邮编：510970</p>
                                        <p>电邮：brp@BeReport.com.cn</p></div>
                             
                                        <div class="about-social">
                                            <ul>
                                                <li><a href="#" class="fa fa-facebook"></a></li>
                                                <li><a href="#" class="fa fa-twitter"></a></li>
                                                <li><a href="#" class="fa fa-linkedin"></a></li>
                                                <li><a href="#" class="fa fa-dribbble"></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


                <div class="col-md-3 hidden-sm">
                    
                    <nav id="nav" class="main-navigation hidden-xs hidden-sm">
                        <ul class="main-menu">
                            <li>
                                <a class="show-1 active homebutton" href="report.jsp"><i class="fa fa-bar-chart-o"></i>Report</a>
                            </li>
                            <li>
                                <a class="show-2 aboutbutton" href="#"><i class="fa fa-user"></i>About Us</a>
                            </li>
                            
                            <li>
                                <a class="show-5 contactbutton" href="#"><i class="fa fa-envelope"></i>Contact</a>
                            </li>
                        </ul>
                    </nav>

                </div>
            </div>
        </div>

        <!-- SITE-FOOTER -->
        <div class="site-footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <p><a href="#" target="_blank" title="友情链接">友情链接</a> </p>
                    </div>
                </div>
            </div>
        </div> <!-- .site-footer -->

        <script src="HomeStyle/js/vendor/jquery-1.10.2.min.js"></script>
        <script src="HomeStyle/js/plugins.js"></script>
        <script src="HomeStyle/js/main.js"></script>
        
         <!-- 全局js -->
    <script src="HomeStyle/js/jquery.min.js?v=2.1.4"></script>
    <script src="HomeStyle/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="HomeStyle/js/content.js?v=1.0.0"></script>

    <!-- iCheck -->
    <script src="HomeStyle/js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>

        
		<!-- templatemo 439 rectangle -->
    </body>
</html>