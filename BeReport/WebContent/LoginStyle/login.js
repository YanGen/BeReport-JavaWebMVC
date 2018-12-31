/**
 * Created by Administrator on 2016/5/25.
 */
$(function(){
    var username=null;
    var isShowed=false;
    var errorFocusObj = 0; //0:用户名输入框，1：密码，2：服务校验错误
    var redirectUrl = "";


    if(!String.prototype.trim) {
        String.prototype.trim = function () {
            return this.replace(/^\s+|\s+$/g,'');
        };
    }
    


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

   }
   
