<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script id="allmobilize" charset="utf-8" src="http://a.yunshipei.com/6a4b3f63883b63133dcb8f2689166293/allmobilize.min.js"></script>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate" media="handheld" href="#" />
<meta charset="utf-8" />
<title>安全设置</title>
<link href="${ctx}/styles/uc/register.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/uc/user_reset.css" rel="stylesheet" type="text/css" />
 <link href="${ctx}/styles/uc/v2.0/top.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
 <script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx}/js/jquery/jquery.validate.js"></script>

<script type="text/javascript">
	  function verify(){
		  var mailV = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
		  var email = $("#email").attr("value");
		  if(email == null || email == ''){
				layer.alert('请输入邮箱');
				return;
			}
		  if(!mailV.test(email)){
				layer.alert('请输入正确邮箱');
				return;
			}
			$.ajax({
				type : 'POST',
				url : "${ctx}/code/sendEmailVerifyCode.ht",
				data : {email : email},
				success : function(dataMap) {
					if(dataMap && dataMap.flag=="1"){
						layer.alert('验证码下发成功，注意查收');
						var sendObj = $('input#verifyBtn');
						start_sms_button(sendObj);
					}else{
						layer.alert("验证码下发失败");
					}
				},
				error : function(dataMap){
					layer.alert("验证码下发失败");
				}
			});
		  
		}
	  
	function start_sms_button(obj){
	    var count = 1 ;
	    var sum = 60;
	    obj.attr('disabled',true); 
	    var i = setInterval(function(){
	      if(count > sum){
	        obj.attr('disabled',false);
	        obj.val('发送验证码');
	        $("#sendCode").remove();
	        clearInterval(i);
	      }else{
	        obj.val(parseInt(sum - count)+'秒后可重发');
	        if($("#sendCode").html()==null){
	        	obj.parent().append("<span id='sendCode' style='font-size:14px;'>发送成功</span>");
	        }
	      }
	      count++;
	    },1000);
	  }
	
	//邮箱 
	jQuery.validator.addMethod("mail", function (value, element) {
		var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
		return this.optional(element) || (mail.test(value));
	}, "邮箱格式不对");

 			$(function(){
				$("#verifyBtn").attr('disabled',false);
			});
		          
			$.extend($.validator.messages, {
				required: '不能为空',
			});
	
</script>

<style type="text/css">
.changeMobile_title {
    width: 100%;
    border-bottom: 1px solid #ddd;
    height: 50px;
    margin-top:50px;
    }
.changeMobile {
	border-left: 5px solid #ff771d;
    font-family: "Microsoft YaHei";
    color: #656565;
    font-size: 24px;
    width: 580px;
    float: left;
    padding: 5px 0 0 10px;}
.changeMobile_title a{
	color: #666;
    text-decoration: none;
	line-height: 60px;}
.changeMobile_intro{
	padding-top:60px;
	padding-left:100px;}
.btn-confirm{
	width:60px;
	height:30px;
	background:#ff771d;
	color:#fff;
	border-radius:6px;
	cursor:pointer;
	font-size:14px;
	padding:10px 0 0 130px;
	margin-left:80px;}	
.btn-return{
	width:60px;
	height:30px;
	background:#ff771d;
	color:#fff;
	border-radius:6px;
	cursor:pointer;
	font-size:14px;
	padding:10px 0 0 130px;
	margin-left:80px;}	
.form-list1 {
    padding-left: 20px;
    padding-bottom: 20px;
    width: 720px;
    height: 50px;
    font-size: 16px;}
.form-list{
	padding-left:20px;
	padding-bottom:20px;
	margin-top: 50px;}
.form-list .form-list-input{
	width:230px;
	padding:10px;
	border-radius:5px;
	border:1px solid #ddd;
	height:22px;
	font-family:"Microsoft YaHei";}
.findPwd_mainCon_tit{
	width: 160px;
	text-align: right;
	display: inline-block;
	margin-right: 15px;
	color:#333;}
.findPwd_mainCon_con{
	width:270px;
	padding-left:10px;
	height:38px;
	border:1px solid #e5e5e5;
	font-size: 16px;
	color:#343434;
	line-height: 38px;}
.error{
	color:red;
}
 body {
    color: #666;
    font-family: Microsoft YaHei;
    font-size: 14px;}

</style>
</head>
	 <body>
	   
        <!-- <div class="top">
			<div class="block">
			<a class="logo" href="#"><span class="decorate"></span></a>
			<div class="tit">安全设置</div>
			</div>
		</div> -->
        <div class="changeMobile_title">
		     <div class="changeMobile">更换邮箱</div>
		</div>
	 	<div style="width:400px;float:left;font-size:14px;margin-top: 20px; margin-left: 180px;">
		 	<img src="${ctx}/pages/images/warn.png" style="margin: -10px 0px;"></img>请确保您输入的邮箱能正常接收激活邮件，邮箱才能生效。
		</div>
		<div class="changeMobile_intro">
		     <form id="loginForm" method="post" >
      			  <div class="form-list1">
				    <label class="findPwd_mainCon_tit">新邮箱：</label>
				   <%--  <input type="text" id="mobile" name="mobile" maxlength="60" class="findPwd_mainCon_con" required email="true" data-msg-email="请输入正确的email地址" data-rule-remote="${ctx}/user/checkEmailRepeat.ht" data-msg-remote="该邮箱已被注册"/> --%>
				    <input type="text" id="email" name="email" maxlength="60" class="findPwd_mainCon_con" required email="true" data-msg-email="请输入正确的email地址"/>
				  </div> 
				 <div class="form-list1">
				   <label class="findPwd_mainCon_tit">邮箱验证码：</label>
				   <input type="text"  style="width: 180px"  id="verifycode" name="verifycode" maxlength="4" required class="findPwd_mainCon_con"  /> &nbsp;&nbsp;<input type="button" style="height: 38px;border-radius:5px;padding:0 5px;cursor: pointer;" name="verifyBtn" id="verifyBtn" value="发送验证码" onclick="verify();"/>
				 </div>
				  <div class="form-list" > 
			  		<input type="button" class="btn-confirm" value="确定" onclick="clickConfirm()" style="border-width: 0px; padding-left: 0px; padding-top: 0px; margin-left: 170px; width: 80px; height: 40px;"/>
			  		<input type="button" class="btn-return" value="返回" onclick="clickReturn()" style="border-width: 0px; padding-left: 0px; padding-top: 0px; margin-left: 50px; width: 80px; height: 40px;"/>
				  </div>
				   <div class="form-list">
				 	<div style="width:450px;float:left;font-size:14px;margin-left:70px;color:blue;">没收到邮箱验证码</div>
				 	<div style="width:370px;float:left;font-size:14px;margin-left: -450px;color:blue;margin-top: 30px;">
					 	<p>1、网络通讯异常可能会造成邮件丢失，请重新获取或稍后再试。</p>
					 	<p>2、请核实邮箱是否正常使用，并检查垃圾邮箱夹。</p>
					</div>
				 </div>
	          </form>
		 </div>
	</body>
	
	<script type="text/javascript">
		
		$("#loginForm").validate(); 
		
		function clickConfirm(){
			var email=$("#email").val();
			var verifycode = $("#verifycode").val();
			if(email == null ||email == ''){
				layer.alert('请输入邮箱');
				return false;
			}
			if(verifycode == null ||verifycode == ''){
				layer.alert('请输入邮箱验证码');
				return false;
			}
			$.ajax({
				type : 'POST',
				url : "${ctx}/security/checkEmailVerifyCode.ht",
				data : {
					email : email,
					verifycode : $("#verifycode").val()
				},
				success : function(resultMessage) {
					if(resultMessage && resultMessage.indexOf("成功") > 0){
						layer.alert("邮箱验证码校验成功",function(){
							window.location.href="${ctx}/security/changeEmailStep3.ht?email="+email;
						});
						//window.location.href="${ctx}/security/changeEmailStep3.ht?email="+email;
					}else{
						layer.alert(resultMessage);
					}
				},
				error : function(dataMap){
					layer.alert("更换邮箱失败");
				}
			});
		}
		
		function clickReturn(){
			window.location.href="${ctx}/security/changeEmail.ht";
		}		
		
</script>
</html>
