<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0033)http://derp.lechuangzhe.com/login -->
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0, user-scalable=0">

    <title> - 登录</title>
    <meta http-equiv="keywords" content="报表,图表,excel,报表工具,web报表,Java报表,数据报表,数据应用,数据分析,电子政务,大数据,易客报表,报表开发,自定义报表,自定义工作流,智能表单,移动办公,SaaS">
    <meta http-equiv="description" content="个性化业务定制开发、报表服务">
    
    <link href="http://derp.lechuangzhe.com/res/common/images/lechuangzhe/favicon.ico?v=6_4_2_20171108" rel="shortcut icon" type="image/x-icon">
    <link href="LoginStyle/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="LoginStyle/login.css" rel="stylesheet" type="text/css">
    <style>
        @media screen and (max-width:768px){
            .portImg{
                display: none;;
            }
        }
    </style>
    
    <script src="LoginStyle/hm.js.下载"></script><script type="text/javascript" src="LoginStyle/jquery.min.js.下载"></script>
    
    <!--[if lt IE 9]>
    <script type="text/javascript" src="res/common/lib/html5shiv/html5shiv.min.js?v=160812"></script>
    <script type="text/javascript" src="res/common/lib/respond/respond.min.js?v=160812"></script>
    <![endif]-->
    
	<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "https://hm.baidu.com/hm.js?c873fda3920d9dff99a58944a938857c";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
	</script>
</head>
<body>
<div class="loginContain" style="height: 596px;">
    <div class="login" style="margin-top: 38px;">
        <div class="login-head">
	            <span class="mylogo">
	            		            </span>
            <div class="excelReport">Login The BeReport</div>
        </div>
        <div class="loginForm" style="margin-top:30px;">
            <div class="loginhead">
                		<a href="javascript:;" class="text select" id="login_user">用户登录</a>
                		<a href="javascript:;" id="login_tenant" class="text">管理员登录</a>
            </div>
            <div>
            	<div class="form-group helpPwdParent">
            		<span class="help-block helpPwd hide"></span>
            	</div>
            	<form action="loginservlet?IsIndex=N" method="post">
                <div class="form-group" style="margin-top:3px;">
                    <label for="username" class="sr-only">用户名</label>
                    	<input name="username" type="text" class="form-control" id="usernameShow" placeholder="用户名" placeholder-user="用户名" placeholder-tenant="企业标识">
                    <input name="username" type="text" class="form-control hide" id="username" placeholder="">
                </div>
                <div class="form-group has-feedback">
                    <label for="pwd" class="sr-only">密码</label>
                    <input name="password" type="password" class="form-control" id="pwd" placeholder="密码">
                    <span class="block-help form-control-feedback glyphicon glyphicon-eye-open" id="myEye" style="margin-top: 2px;">
                    </span>
                </div>
                
                <div class="checkbox" style="margin-bottom:0px;height:40px;">
                    <label class="rememberUser hide" style="color:#666">
                        <input type="checkbox" id="rmbUser" class="s_option" style="position: relative;"> 记住我
                    </label>
                    	<span style="height:20px;width:1px;"></span>
	                    <label class="fr">
	                        <a class="forgetLink">忘记密码</a>
	                    </label>
                </div>
                <button class="btn loginBtn" style="margin-top:0px;margin-bottom:10px;">登录</button>
                </form>
                	<div class="tc tryMe">
			                <span>还没有用户账号？</span><a class="btn registerBtn" href="/BeReport/register.html">立即注册</a>
	                </div>                	
            </div>
        </div>
    </div>
    
</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body showMsg">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


<script>
    //ER_load_jquery_js();
    hostIp = "derp.lechuangzhe.com";
    hostPort = "80";
    tenantIdent = "BeReport";
    multTenantMode=true;
    showTenantLogin = "true";
    allowUserNameLogin = "true";
    returnUrl = "";
    enableShowUserAlias = "false";
    enableRememberUser = "false";
    loginCodeLoginType = "0";
    enableVerifyCodeLogin = "false";
    
    		tenantLogin = false;
</script>

<script type="text/javascript" src="LoginStyle/jquery.cookie.js.下载"></script>
<script type="text/javascript" src="LoginStyle/bootstrap.min.js.下载"></script>
<script type="text/javascript" src="LoginStyle/login.js"></script>


</body></html>