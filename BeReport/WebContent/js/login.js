/**
 * Created by Administrator on 2016/5/25.
 */
$(function(){
    var username=null;
    var isShowed=false;
    var errorFocusObj = 0; //0:用户名输入框，1：密码，2：服务校验错误
    var redirectUrl = "";

    $.ajaxSetup({
        beforeSend: function (xhr) {
        },
        error: function (xhr, status, e) {
            var span=$('.helpPwd');
            span.html('请求数据失败,错误状态:'+xhr.status);
            errorFocusObj = 2;
            //$('.modal').modal('show');
            $('.help-block').addClass('hide');
            span.removeClass('hide');
        }
    });

    if(!String.prototype.trim) {
        String.prototype.trim = function () {
            return this.replace(/^\s+|\s+$/g,'');
        };
    }
    
    //更换验证码图片
    function changeVerifyCodeImg() {
    	if ("true" === enableVerifyCodeLogin) {
    		var timestamp=new Date().getTime();
        	$('#verifyCodeImg').attr("src","sysData?type=getVerifyCodeImg&t="+timestamp);
    	}    	
    }
    
    changeVerifyCodeImg();


    //设置页面高度
    function resizeLoginHeight(){
        var winHeight=$(window).height();
        if(winHeight>480){
            $('.loginContain').height(winHeight-2);
            //$('.loginUrl').height(winHeight);
            var loginTop=((winHeight-$('.login').height()))/2;  //-70
            if (loginTop > 50) {
            	loginTop-=20;
            }
            $('.login').css('margin-top',loginTop);
        }
    }
    $(window).resize(resizeLoginHeight);
    resizeLoginHeight();

    //处理记住密码
    if(enableRememberUser === 'true'){
        $('.rememberUser').removeClass('hide')
    }else{
        $('.rememberUser').addClass('hide').prop('check',false);
    }
    
    //切断到用户
    function toSelUserLogin(a) {
    	isShowed = false;
    	tenantLogin = false;
    	$("#login_tenant").removeClass("select");
    	$(a).addClass("select");
    	$("#usernameShow").attr("placeholder",$("#usernameShow").attr("placeholder-user"));
    	$("#usernameShow").val('');
    	$("#username").val('');
    	$("#pwd").val('');
    	$("#usernameShow").removeAttr("readonly");
    	
    	$('.helpPwd').html('');
    	$('.helpPwd').addClass('hide');
    	
    	//alert('toSelUserLogin');
    }
    
    //切断到租户
    function login_tenant(a) {
    	isShowed = false;
    	tenantLogin = true;
    	$("#login_user").removeClass("select");
    	$(a).addClass("select");
    	$("#usernameShow").attr("placeholder",$("#usernameShow").attr("placeholder-tenant"));
    	$("#username").val(''); 
    	$("#pwd").val('');
    	$('.helpPwd').html('');
    	$('.helpPwd').addClass('hide');
    	
    	if (undefined != tenantIdent && "" != tenantIdent) {
    		$("#usernameShow").val(tenantIdent);
    		$("#usernameShow").attr("readonly","readonly");
    		$("#usernameShow").blur();
    	}else {
    		$("#usernameShow").val('');
    		$("#usernameShow").attr("readonly","");
    	}
    	
    	//$(a).addClass("on");
    	//alert('login_tenant');
    }
    
    //换一张验证码
    $('#verifyCodeChange').on('click',function(){
    	$('#verifyCode').val('');
    	var timestamp=new Date().getTime();
    	$('#verifyCodeImg').attr("src","sysData?type=getVerifyCodeImg&t="+timestamp);
    })
    
    //检测验证码有效性
    $('#verifyCode').on('blur',function(){
    	var verifyCode=$('#verifyCode').val();
        if(undefined == verifyCode || verifyCode==null || verifyCode.trim()==''){
            return ;
        }
        
        verifyCode=verifyCode.trim();
        if (verifyCode.length < 4) {
        	$('.helpPwd').html('您输入的验证码有误');
            errorFocusObj = 1;
            $('.helpPwd').removeClass('hide');
            return ;
        }
        
        $.post('sysData?type=checkVerifyCode',{
            "verifyCode" : verifyCode,
            "resultType":'json'
        },function(data){
            if(data.success){
            	
            }else{
                $('.helpPwd').html(data.msg);
                errorFocusObj = 1;
                $('.helpPwd').removeClass('hide')
            }
        },"json")
    })

    //登录
    function toLogin(){
        $('.help-block').addClass('hide');
        var placeholder = $('#usernameShow').attr("placeholder");
        if(!isShowed){
            username=$('#usernameShow').val();
        }else{
            username=$('#username').val();
        }
        if(username.trim()==''||username==null){
            $('.helpPwd').html('请输入您的'+placeholder);
            errorFocusObj = 0;
            $('.helpPwd').removeClass('hide');
            return ;
        }else if(username.length<1){
            $('.helpPwd').html('请输入正确的用户名，用户名必须大于1位');
            errorFocusObj = 0;
            $('.helpPwd').removeClass('hide');
            return ;
        }
        var pwd=$('#pwd').val();
        if(pwd.trim()==''||pwd==null){
            $('.helpPwd').html('请输入您的密码');
            errorFocusObj = 1;
            $('.helpPwd').removeClass('hide');
            return ;
        }

        var verifyCode='';
        if ("true" === enableVerifyCodeLogin) {
        	verifyCode=$('#verifyCode').val();
        	if(undefined == verifyCode || verifyCode==null || verifyCode.trim()==''){
            	$('.helpPwd').html('请输入验证码');
                errorFocusObj = 1;
                $('.helpPwd').removeClass('hide');
                return ;
            }
            
            verifyCode=verifyCode.trim();
            if (verifyCode.length < 4) {
            	$('.helpPwd').html('您输入的验证码有误');
                errorFocusObj = 1;
                $('.helpPwd').removeClass('hide');
                return ;
            }
        }        
        
        $.post('Login',{
            "userName":username,
            "password":pwd,
            "resultType":'json',
            "tenantIdent":tenantIdent,
            "clientName":'HappyPortal',
            "tenantLogin":tenantLogin,
            "retUrl" : encodeURIComponent(returnUrl),
            "verifyCode":verifyCode
        },function(data){
            if(data.success){
            	redirectUrl = data.url;
                //cookie相关
                if($('#rmbUser').is(':checked')){
                    $.cookie("user",username,{expires:7});
                    $.cookie("rmbUser","true",{expires:7});
                }else {
                	$.cookie("user",null);
                	$.cookie("rmbUser",null);
                }
                
                if(data.logInfo && data.logInfo!=''){
                    $('#myModal .close').hide();
                    $('#myModal .modal-footer button').addClass('turnHome')
                    $('.showMsg').html(data.logInfo);
                    $('.modal').modal({
                        backdrop:'static',
                        show:true
                    });
                    errorFocusObj = 2;
                    $('.modal').modal('show');
                }else{
                    $('.container-fluid').animate({
                        'opacity':0
                    },200);
//                    if(!isShowed){
//                        $('#username').val($('#usernameShow').val());
//                        username=$('#username').val();
//                        valiUser(username,1);
//                    }else{
//                        setTimeout(function(){
//                            location.href=redirectUrl;
//                        },200)
//                    }
                	
                	setTimeout(function(){
                        location.href=redirectUrl;
                    },200)
                    
                    //暂时不检测用户名
                    //setTimeout(function(){
                    //    location.href=redirectUrl;
                    //},200)

                }
            }else{
//            	if ("unsettingModifyPage" === data.errorType) {
//            		$('#myModal .close').hide();
//                    $('.showMsg').html(data.msg);
//                    $('.modal').modal({
//                        backdrop:'static',
//                        show:true
//                    });
//                    $('.modal').modal('show');
//                    
//            		$('.helpPwd').html("");
//            		$('.helpPwd').addClass('hide')
//            	}else {
//            		$('.helpPwd').html(data.msg);
//                    errorFocusObj = 1;
//                    $('.helpPwd').removeClass('hide')
//            	} 
            	
            	$('.helpPwd').html(data.msg);
            	$('.helpPwd').removeClass('hide')
                errorFocusObj = 2;
            	
            	showVerifyCode(username);
            }
        },"json")
    }
    $('.container-fluid').animate({
        'opacity':1
    },250);
    //验证手机号码是否存在
    //var checkPhone=function(phone){
    //    $.ajax({
    //        url:'userData?type=checkPhoneNo&op=check&phoneNo='+phone,
    //        type:'post',
    //        async:false,
    //        data:{
    //
    //        },
    //        success:function(data){
    //            if(data.success){
    //                if(data.exist){
    //                    return true
    //                }else{
    //                    return false
    //                }
    //            }
    //        }
    //    })
    //}
    //切换到用户登录
    $('#login_user').on('click',function(){
        toSelUserLogin(this)
    })
    
    //切换到租户登录
    $('#login_tenant').on('click',function(){
    	login_tenant(this)
    })
    
    //忘记密码
    $('.forgetLink').on('click',function($event){
    	if (tenantLogin) {
    		location.href="forget?tenantMode=1";
    	}else {
    		location.href="forget";
    	}        
    });
    
    //点击登录后验证
    $('.loginBtn').on('click',function(){
        toLogin()
    });
    $('#pwd').on('keyup',function($event){
        var e = $event;
        var keyvalue = e.keyCode ? e.keyCode : e.which;
        var shifKey = e.shiftKey ? e.shiftKey:((keyvalue == 16) ? true : false);
        var strlen = $(this).val().length;
        if(strlen){
            var uniCode = $(this).val().charCodeAt($(this).val().length-1);
            if( (keyvalue>=65 && keyvalue<=90)   || keyvalue==20){     //如果是字母键
                if(((uniCode >= 65 && uniCode <= 90) && !shifKey)||((uniCode >= 97 && uniCode <= 122) && shifKey)){
                    //$('.helpPwd').html('Caps Lock键已开启');
                    $('.helpPwd').removeClass('hide')
                    //return true;  //开启
                }else{
                    $('.helpPwd').addClass('hide')
                    //return false;  //未开启
                }
                if(keyvalue==20 && $('.helpPwd').hasClass('hide')){
                    //$('.helpPwd').html('Caps Lock键已开启');
                    $('.helpPwd').removeClass('hide')
                }else if(keyvalue==20 && !$('.helpPwd').hasClass('hide')){
                    $('.helpPwd').addClass('hide')
                }
            }
        }

        if($event.which==13){
            toLogin();
        }
    })
    $('#pwd').on('blur',function($event){
//        if( $('.helpPwd').html()=='Caps Lock键已开启'){
//            $('.helpPwd').addClass('hide')
//        }
    })
    $('#myModal').on('keyup',function($event){
        if($event.which==13 && !((navigator.userAgent.indexOf('MSIE') >= 0)
            && (navigator.userAgent.indexOf('Opera') < 0))){
            $('#myModal .btn').click();
        }
    })
    $('#usernameShow').on('keyup',function($event){
        if($event.which!=9){
            //toLogin();
            isShowed=false;
        }
        if($event.which==13){
            //toLogin();
        }
    });
    
    //登录弹出警告信息后
    $(document).on('click','.turnHome',function($event){
        location.href=redirectUrl;
    });
    $('.modal').on('hidden.bs.modal', function () {
        setTimeout(function(){
            if (0 == errorFocusObj) {
                $('#usernameShow').focus();
            }else if (1 == errorFocusObj){
                $('#pwd').focus();
            }
        },100);
    });

    //输入框失去焦点时 提示信息
    $('#usernameShow').on('blur',function($event){
    	$('.helpPwd').html('');
        $('.helpPwd').addClass('hide');
        
    	//处理图片校验码是否显示
    	showVerifyCode($(this).val());
    	
    	//处理显示用户姓名
    	if ("true" != enableShowUserAlias) { return; }
        if(!isShowed){
            $('#username').val($(this).val());
            username=$('#username').val();
            var placeholder = $('#usernameShow').attr("placeholder");
            if(username.trim()==''||username==null){
                //$('.showMsg').html('请输入您的用户名');
                $('.helpPwd').html('请输入您的'+placeholder);
                $('.helpPwd').removeClass('hide');
                errorFocusObj = 0;
                return ;
            }
            //暂时不验证用户名
            valiUser(username);
        }
    });
    
    //输入框失去焦点时 提示信息
//    $('#pwd').on('blur',function($event){
//        //$('.help-block').addClass('hide');
//        var pwd=$(this).val();
//        if(isAllowEmptyPwd){
//           return;
//        }
//        if(pwd.trim()==''||pwd==null){
//            //$('.showMsg').html('请输入您的用户名');
//            $('.helpPwd').html('请输入您的密码');
//            $('.helpPwd').removeClass('hide');
//            //errorFocusObj = 0;
//            //$('.modal').modal('show');
//        }else{
//            $('.helpPwd').addClass('hide');
//        }
//    })
    
    var showVerifyCode=function (userName) {
    	if (undefined == userName || null == userName || "" == userName.trim()) { return; }
    	
    	//处理图片校验码是否显示
    	if ('2' === loginCodeLoginType || 2 === loginCodeLoginType) {
    		if ("true" === enableVerifyCodeLogin) {return;}
    		
    		$('div.verifyCode').addClass('hide');
    		enableVerifyCodeLogin = "false";
    		
    		$.ajax({
                type:'post',
                dataType:"json",
                url:'sysData?type=checkNeedVerifyCode',
                async:false,
                data : {
                	tenantIdent : tenantIdent,
                	userName : userName,
                	resultType : 'json'
                },
                success:function(data) {
                    if(data.success){
                        if (data.needVerifyCode) {
                        	$('div.verifyCode').removeClass('hide');
                        	enableVerifyCodeLogin = "true";
                        	changeVerifyCodeImg();
                        }
                    }else{
//                    	$('#myModal .close').hide();
//                        $('.showMsg').html(data.msg);
//                        $('.modal').modal({
//                            backdrop:'static',
//                            show:true
//                        });
//                        $('.modal').modal('show');
                    	
                    	$('.helpPwd').html(data.msg);
                        $('.helpPwd').removeClass('hide');
                        errorFocusObj = 0;
                    }
                }
            })
    	}
    }
    
    var valiUser=function(username,type){
    	if ("true" != enableShowUserAlias) { return; }
    	if (undefined == username || "" == username.trim()) { return ;}
        var url='userDataEx?type=userBaseInfo&op=query&resultType=json&userName='+username;
        if (true === tenantLogin) {
        	url += "&tenantLogin=true";
        }
        if(tenantIdent && tenantIdent!='' ){
            url +='&tenantIdent='+tenantIdent;
        }
        $.ajax({
            type:'post',
            dataType:"json",
            url:url,
            success:function(data) {
                if(data.success){
                    //redirectUrl=data.url;
                    $('.helpPwd').addClass('hide');
                    if(!isShowed){
                    }
                    isShowed=true;
                    $('#usernameShow').val(data.user.alias);
                    if(type){
                        setTimeout(function() {
                            location.href=redirectUrl;
                        },500)
                    }
                }else{
                    //$('#usernameShow').focus();
                    //if(data.msg=='服务器错误(getUser): 用户不存在'){
                    $('.help-block').removeClass('hide')
                    if(data.msg){
                        if(data.msg.indexOf('getUser') >= 0){
                            $('.helpPwd').html('用户不存在');
                            errorFocusObj = 0;
                        }else {
                            $('.helpPwd').html(data.msg);
                            errorFocusObj = 2;
                        }
                    }else{
                        $('.helpPwd').html('请求失败：未知错误');
                        errorFocusObj = 2;
                    }
                }
            }
        })
    }

    $('#myEye').on('click',function(){
        $(this).toggleClass('glyphicon-eye-open glyphicon-eye-close');
        if($(this).siblings('#pwd').attr('type')=='text'){
            $(this).siblings('#pwd').attr('type','password');
        }else{
            $(this).siblings('#pwd').attr('type','text');
        }
    })
    $('.DataNewHref').on('click',function(){
        window.open('http://www.datanew.com')
    })
    if( $.cookie("user")){
    	var userName = $.cookie("user");
    	if ("null" != userName) {
    		$('#username').val(userName);
            $('#usernameShow').val(userName);
            
            valiUser(userName);
            showVerifyCode(userName);
    	}
        
        var checked = $.cookie("rmbUser");
        if ("true" == checked) {
        	$('#rmbUser').prop('checked', true);
        }
    };
})



$(function(){
    var sendCode=function(){
        var self=this,
            i=60;
        $(self).text('重新发送（'+i+'）').addClass('grayColor').removeClass('codeBtn');
        var timer=setInterval(function(){
            if(i==0){
                clearInterval(timer);
                $(self).text('获取验证码').removeClass('grayColor').addClass('codeBtn');
                return false;
            }
            i--;
            $(self).text('重新发送（'+i+'）')
        },1000)
    }
    $(document).on('click','.codeBtn',sendCode);
})







//不支持placeholder  兼容问题
$(function() {
    // 如果不支持placeholder，用jQuery来完成
    if(!isSupportPlaceholder()) {
        // 遍历所有input对象, 除了密码框
        $('input').not("input[type='password']").each(
            function() {
                var self = $(this);
                var val = self.attr("placeholder");
                input(self, val);
            }
        );

        /**//* 对password框的特殊处理
         * 1.创建一个text框
         * 2.获取焦点和失去焦点的时候切换
         */
        $('input[type="password"]').each(
            function() {
                var pwdField    = $(this);
                var pwdVal      = pwdField.attr('placeholder');
                var pwdId       = pwdField.attr('id');
                // 重命名该input的id为原id后跟1
                pwdField.after('<input id="' + pwdId +'1" class="form-control" type="text" value='+pwdVal+' autocomplete="off" />');
                var pwdPlaceholder = $('#' + pwdId + '1');
                pwdPlaceholder.show();
                pwdField.hide();

                pwdPlaceholder.focus(function(){
                    pwdPlaceholder.hide();
                    pwdField.show();
                    pwdField.focus();
                });

                pwdField.blur(function(){
                    if(pwdField.val() == '') {
                        pwdPlaceholder.show();
                        pwdField.hide();
                    }
                });
            }
        );
    }
});

// 判断浏览器是否支持placeholder属性
function isSupportPlaceholder() {
    var input = document.createElement('input');
    return 'placeholder' in input;
}

// jQuery替换placeholder的处理
function input(obj, val) {
    var $input = obj;
    var val = val;
    $input.attr({value:val});
    $input.focus(function() {
        if ($input.val() == val) {
            $(this).attr({value:""});
        }
    }).blur(function() {
        if ($input.val() == "") {
            $(this).attr({value:val});
        }
    });
}












