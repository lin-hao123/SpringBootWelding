$(document).ready(function(){
//检查用户是否存在	
	$("#inname").blur(function(){
		var inname=$("#inname").val();
		$.getJSON("user/checkName/"+inname,function(json){
			  if(json){
					alert("用户名已存在")
					$("#inname").val("");
					$("#inname").focus();
				}
			});
	});
//注册
	$("#inregister").click(function(){
			var inname=$("#inname").val();
			var intel=$("#intel").val();
			var inpwd=$("#inpwd").val();
			var inage=$("#inage").val();
			var insex=$("input[name='insex']:checked").val();
			 var data2 = {
					 "name": inname,
					 "pwd": inpwd,
					 "sex": insex,
					 "age": inage,
					 "tel": intel,
		            }
			 $.ajax({
                 type: "POST",
                 url: "user/basicUserForm",
                 data: JSON.stringify(data2),
                 contentType: 'application/json',
                 success: function (json) {
                 	console.log(json);
                 	if(json.code==200){
							alert("注册成功");
                 		window.location.href="login.html";
                 	}else{
                 		alert("编辑失败");
                 	}
		            }
			 });

	})
});