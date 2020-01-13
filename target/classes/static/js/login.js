$(document).ready(function(){
	//登录
	$("#loginbtn").click(function(){
			var nameval=$("#inname").val();
			var pwdval=$("#inpwd").val();
			var rememberMe =$("input[name='rememberMe']").is(':checked');
			console.log("rememberMe:"+rememberMe);
		$.post("user/login", { name: nameval, pwd: pwdval , rememberMe:rememberMe},
			function(data){
				if(data.code==200){
					localStorage.setItem("token",data.msg);
					window.location.href="index.html";
				}else{
					alert("用户名或者密码错误")
				}		
			});
	})
});