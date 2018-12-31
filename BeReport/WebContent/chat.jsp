<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zorage.chat.ChatObject"%>
<%@page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> - 聊天窗口</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        
            <div class="col-sm-12">

                <div class="ibox chat-view">

                    <%
					List<ChatObject> massages = (List)request.getAttribute("massages"); 
					ChatObject LastMassage = massages.get(massages.size() - 1);
					
					%>

                    <div class="ibox-title">
                        <small class="pull-right text-muted">最新消息：<%=LastMassage.getDate() %></small> 聊天窗口
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div id="ChatContent" class="col-md-9 " style=" width:1100px; height:500px; overflow:auto">
                                <%
                                for(ChatObject massage : massages){ 
                                %>
                                    <div class="chat-message">
									 <%
									 if(massage.getManager().equals("no")){
										 %>
                                        <img class="message-avatar" src="img/user.jpg" alt="">
                                        <%}else{ %>
                                                                                <img class="message-avatar" src="img/manager.jpg" alt="">
                                        <%} %>                                        <div class="message">
                                            <a class="message-author" href="#"> <%=massage.getUsername() %> </a>
                                            <span class="message-date"> <%=massage.getDate() %> </span>
                                            <span class="message-content">
											<%=massage.getContent() %>
                                            </span>
                                        </div>
                                    </div>
                                    <%
                                    }
                                %>
                                    
                                 
                            </div>
                            
 
                       </div>
                       <div class="row">
                            <div class="col-sm-12">
                                <div class="chat-message-form">
                                <%
                                HttpSession httpSession = request.getSession();
                                if(httpSession.getAttribute("LoginUser")==null){
                                %>
                                <div class="col-sm-10">
                                    <input type="text" disabled="" placeholder="发言请先登录" class="form-control">
                                </div>
                                <%}else{ %>
<form action="/BeReport/chatform" method="post">
                                    <div class="form-group">

                                        <div class="input-group">
                                        <input type="text" class="form-control" name="massage"> <span class="input-group-btn"> <button type="submit" class="btn btn-primary">发送
                                        </button> </span>
                                    </div>
                                    </div>
<br>
                                </form>
                                <%} %>
                                </div>
                            </div>
                       


                    </div>

                </div>
            </div>

      

    </div>


    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    
    
</body>
 <script type="text/javascript">
                
       //  让滚动条始终保持在最下方
         
         var div = document.getElementById('ChatContent');
         div.scrollTop = div.scrollHeight;
           
            </script>
</html>
