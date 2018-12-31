/**
 * Created by Administrator on 2016/5/25.
 */
$(function(){
    var username=null;
    var isShowed=false;
    var errorFocusObj = 0; //0:�û��������1�����룬2������У�����
    var redirectUrl = "";

    $.ajaxSetup({
        beforeSend: function (xhr) {
        },
        error: function (xhr, status, e) {
            var span=$('.helpPwd');
            span.html('��������ʧ��,����״̬:'+xhr.status);
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
    
    //������֤��ͼƬ
    function changeVerifyCodeImg() {
    	if ("true" === enableVerifyCodeLogin) {
    		var timestamp=new Date().getTime();
        	$('#verifyCodeImg').attr("src","sysData?type=getVerifyCodeImg&t="+timestamp);
    	}    	
    }
    
    changeVerifyCodeImg();


    //����ҳ��߶�
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

    //�����ס����
    if(enableRememberUser === 'true'){
        $('.rememberUser').removeClass('hide')
    }else{
        $('.rememberUser').addClass('hide').prop('check',false);
    }
    
    //�жϵ��û�
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
    
    //�жϵ��⻧
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
    
    //��һ����֤��
    $('#verifyCodeChange').on('click',function(){
    	$('#verifyCode').val('');
    	var timestamp=new Date().getTime();
    	$('#verifyCodeImg').attr("src","sysData?type=getVerifyCodeImg&t="+timestamp);
    })
    
    //�����֤����Ч��
    $('#verifyCode').on('blur',function(){
    	var verifyCode=$('#verifyCode').val();
        if(undefined == verifyCode || verifyCode==null || verifyCode.trim()==''){
            return ;
        }
        
        verifyCode=verifyCode.trim();
        if (verifyCode.length < 4) {
        	$('.helpPwd').html('���������֤������');
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

    //��¼
    function toLogin(){
        $('.help-block').addClass('hide');
        var placeholder = $('#usernameShow').attr("placeholder");
        if(!isShowed){
            username=$('#usernameShow').val();
        }else{
            username=$('#username').val();
        }
        if(username.trim()==''||username==null){
            $('.helpPwd').html('����������'+placeholder);
            errorFocusObj = 0;
            $('.helpPwd').removeClass('hide');
            return ;
        }else if(username.length<1){
            $('.helpPwd').html('��������ȷ���û������û����������1λ');
            errorFocusObj = 0;
            $('.helpPwd').removeClass('hide');
            return ;
        }
        var pwd=$('#pwd').val();
        if(pwd.trim()==''||pwd==null){
            $('.helpPwd').html('��������������');
            errorFocusObj = 1;
            $('.helpPwd').removeClass('hide');
            return ;
        }

        var verifyCode='';
        if ("true" === enableVerifyCodeLogin) {
        	verifyCode=$('#verifyCode').val();
        	if(undefined == verifyCode || verifyCode==null || verifyCode.trim()==''){
            	$('.helpPwd').html('��������֤��');
                errorFocusObj = 1;
                $('.helpPwd').removeClass('hide');
                return ;
            }
            
            verifyCode=verifyCode.trim();
            if (verifyCode.length < 4) {
            	$('.helpPwd').html('���������֤������');
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
                //cookie���
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
                    
                    //��ʱ������û���
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
    //��֤�ֻ������Ƿ����
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
    //�л����û���¼
    $('#login_user').on('click',function(){
        toSelUserLogin(this)
    })
    
    //�л����⻧��¼
    $('#login_tenant').on('click',function(){
    	login_tenant(this)
    })
    
    //��������
    $('.forgetLink').on('click',function($event){
    	if (tenantLogin) {
    		location.href="forget?tenantMode=1";
    	}else {
    		location.href="forget";
    	}        
    });
    
    //�����¼����֤
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
            if( (keyvalue>=65 && keyvalue<=90)   || keyvalue==20){     //�������ĸ��
                if(((uniCode >= 65 && uniCode <= 90) && !shifKey)||((uniCode >= 97 && uniCode <= 122) && shifKey)){
                    //$('.helpPwd').html('Caps Lock���ѿ���');
                    $('.helpPwd').removeClass('hide')
                    //return true;  //����
                }else{
                    $('.helpPwd').addClass('hide')
                    //return false;  //δ����
                }
                if(keyvalue==20 && $('.helpPwd').hasClass('hide')){
                    //$('.helpPwd').html('Caps Lock���ѿ���');
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
//        if( $('.helpPwd').html()=='Caps Lock���ѿ���'){
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
    
    //��¼����������Ϣ��
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

    //�����ʧȥ����ʱ ��ʾ��Ϣ
    $('#usernameShow').on('blur',function($event){
    	$('.helpPwd').html('');
        $('.helpPwd').addClass('hide');
        
    	//����ͼƬУ�����Ƿ���ʾ
    	showVerifyCode($(this).val());
    	
    	//������ʾ�û�����
    	if ("true" != enableShowUserAlias) { return; }
        if(!isShowed){
            $('#username').val($(this).val());
            username=$('#username').val();
            var placeholder = $('#usernameShow').attr("placeholder");
            if(username.trim()==''||username==null){
                //$('.showMsg').html('�����������û���');
                $('.helpPwd').html('����������'+placeholder);
                $('.helpPwd').removeClass('hide');
                errorFocusObj = 0;
                return ;
            }
            //��ʱ����֤�û���
            valiUser(username);
        }
    });
    
    //�����ʧȥ����ʱ ��ʾ��Ϣ
//    $('#pwd').on('blur',function($event){
//        //$('.help-block').addClass('hide');
//        var pwd=$(this).val();
//        if(isAllowEmptyPwd){
//           return;
//        }
//        if(pwd.trim()==''||pwd==null){
//            //$('.showMsg').html('�����������û���');
//            $('.helpPwd').html('��������������');
//            $('.helpPwd').removeClass('hide');
//            //errorFocusObj = 0;
//            //$('.modal').modal('show');
//        }else{
//            $('.helpPwd').addClass('hide');
//        }
//    })
    
    var showVerifyCode=function (userName) {
    	if (undefined == userName || null == userName || "" == userName.trim()) { return; }
    	
    	//����ͼƬУ�����Ƿ���ʾ
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
                    //if(data.msg=='����������(getUser): �û�������'){
                    $('.help-block').removeClass('hide')
                    if(data.msg){
                        if(data.msg.indexOf('getUser') >= 0){
                            $('.helpPwd').html('�û�������');
                            errorFocusObj = 0;
                        }else {
                            $('.helpPwd').html(data.msg);
                            errorFocusObj = 2;
                        }
                    }else{
                        $('.helpPwd').html('����ʧ�ܣ�δ֪����');
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
        $(self).text('���·��ͣ�'+i+'��').addClass('grayColor').removeClass('codeBtn');
        var timer=setInterval(function(){
            if(i==0){
                clearInterval(timer);
                $(self).text('��ȡ��֤��').removeClass('grayColor').addClass('codeBtn');
                return false;
            }
            i--;
            $(self).text('���·��ͣ�'+i+'��')
        },1000)
    }
    $(document).on('click','.codeBtn',sendCode);
})







//��֧��placeholder  ��������
$(function() {
    // �����֧��placeholder����jQuery�����
    if(!isSupportPlaceholder()) {
        // ��������input����, ���������
        $('input').not("input[type='password']").each(
            function() {
                var self = $(this);
                var val = self.attr("placeholder");
                input(self, val);
            }
        );

        /**//* ��password������⴦��
         * 1.����һ��text��
         * 2.��ȡ�����ʧȥ�����ʱ���л�
         */
        $('input[type="password"]').each(
            function() {
                var pwdField    = $(this);
                var pwdVal      = pwdField.attr('placeholder');
                var pwdId       = pwdField.attr('id');
                // ��������input��idΪԭid���1
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

// �ж�������Ƿ�֧��placeholder����
function isSupportPlaceholder() {
    var input = document.createElement('input');
    return 'placeholder' in input;
}

// jQuery�滻placeholder�Ĵ���
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












