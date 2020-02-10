 $(document).ready(function(){

//	创建角色 
	 $("#creadRole").click(function(){
	    $.getJSON("permission/showall/"+0+"/"+20,function(data2){
			 console.log(data2);
			   var contentdata=data2.content;
				 $("#setPermission").empty();
				 for(var i=0;i<contentdata.length;i++){
					 $("#setPermission").append(
						"<div>"
						+"<input type='checkbox' id='"+i+"' value='"+contentdata[i].id+"' name='interest1'/>"+contentdata[i].name
						 +"</div>"	 
						)
			    }
			});
	  //保存
		$("#creatRoleSave").click(function(data){
			var roleName=$("input[name='createRoleName']").val();
			var chk_value =[];
			 $('input[name="interest1"]:checked').each(function(){
				  var temp = {};
	               temp.id = $(this).val();
	               chk_value.push(temp);
		            });
			 var data2 = {
					 "detail": roleName,
		              "permissionList": JSON.stringify(chk_value)
		            }
			 $.ajax({
                    type: "POST",
                    url: "role/roleForm",
                    data: JSON.stringify(data2),
                    contentType: 'application/json',
                    success: function (json) {
                    	console.log(json);
                    	if(json.code==200){
							alert("保存成功");
                    		window.location.href="role.html";
                    	}else{
                    		alert("保存失败");
                    	}
		            }
			 });	
		});
	    
	    
	    
	 });
	var startpage=0;
	var startsize=8;
	getData();
	 //分页查询
	 function getData(){
		 
		 $.getJSON("role/showall/"+startpage+"/"+startsize,function(json){
			 
		 console.log(json);
			 var contentdata=json.content;
			 $("#tbodyrolebtn").empty();
			 for(var i=0;i<contentdata.length;i++){
				 var arr=[];
				 for(var j=0;j<json.content[i].permissions.length;j++){
					 arr.push(" "+json.content[i].permissions[j].name);
				 }
				 $("#tbodyrolebtn").append(
						 "<tr id='tridval"+i+"'>"
						 +"<td>"+contentdata[i].id
						 +"</td>"
						 +"<td>"+contentdata[i].detail
						 +"</td>"						 		 
						 +"<td>"+ arr
						 +"</td>"
						 +"<td>"
						 +"<button type='button' class='btn btn-outline-info btn-sm' id='btn1"+contentdata[i].id+"' name='btn001' data-toggle='modal' data-target='#myModal2'>详情</button>&nbsp;&nbsp;"
						 +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id='btn3"+contentdata[i].id+"' name='btn003' >删除</button>&nbsp;&nbsp;"
						 +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id='input"+contentdata[i].id+"'  name='detailbtn'>编辑</button>"
						 +"</td></tr>"
				 ) 
			}
// 详情查看
			 $("button[name='btn001']").click(function(){
					var id=this.id
					var num=id.slice(4);
					$.getJSON("role/findById/"+num,function(data){
						var setId=data.id;
						var setName=data.detail;
						 $("#roleId1").text(setId);
						 $("#role1").text(setName);
						 var permissionArr=[];
						 var permissionUrlArr=[];
					     for(var i=0;i<data.permissions.length;i++){
								 permissionArr.push(data.permissions[i].name);
								 permissionUrlArr.push(data.permissions[i].url);
							 }
						 $("#permission1").empty();
						 for(var n=0;n<permissionArr.length;n++){
							 $("#permission1").append(
								"<div class='form-check disabled'>"
								+"<input type='checkbox' id='permission1"+n+"' value='' name='interest' disabled/>"+permissionArr[n]+"("+permissionUrlArr[n]+")"
								 +"</div>"	 
								)
								$("#permission1"+n).prop("checked",true);
					    }
					});
			 });
			//--角色编辑--//
				$("button[name='detailbtn']").click(function(){
					var id=this.id
					var num=id.slice(5);
					$.getJSON("role/findById/"+num,function(data){
						console.log(data);
						var setId=data.id;
						var setName=data.detail;
						 $("#roleId").text(setId);
						$("input[name='roleName']").val(setName);
						 var str = "";
						 var strArr = [];
						 for (var j = 0; j < data.permissions.length; j++) {
						 str = data.permissions[j].name;
						 strArr.push(str);
						}
						 console.log("++++++++"+strArr);
					    $.getJSON("permission/showall/"+0+"/"+20,function(data2){
							 console.log(data2);
							   var contentdata=data2.content;
								 $("#permissions").empty();
								 for(var i=0;i<contentdata.length;i++){
									 $("#permissions").append(
										"<div>"
										+"<input type='checkbox' id='"+i+"' value='"+contentdata[i].id+"' name='interest'/>"+contentdata[i].name
										 +"</div>"	 
										)
									    for (var n = 0; n < strArr.length; n++) {
									        if (strArr[n] == contentdata[i].name) {
									        	$("#"+i).prop("checked",true);
									        }
									    }
							    }
							});
						   });
				})
				//保存修改
					$("#subbtn").click(function(data){
						var id= $("#roleId").text();
						var roleName=$("input[name='roleName']").val();
						var chk_value =[];
						 $('input[name="interest"]:checked').each(function(){
							  var temp = {};
				               temp.id = $(this).val();
				               chk_value.push(temp);
					            });
						 var data2 = {
								 "id": id,
								 "detail": roleName,
					              "permissionList": JSON.stringify(chk_value)
					            }
						 $.ajax({
			                    type: "PUT",
			                    url: "role/update",
			                    data: JSON.stringify(data2),
			                    contentType: 'application/json',
			                    success: function (json) {
			                    	console.log(json);
			                    	if(json.code==200){
										alert("保存成功");
			                    		window.location.href="role.html";
			                    	}else{
			                    		alert("编辑失败");
			                    	}
					            }
						 });	
					});
			 //--修改结束--//
			 //--删除开始--//
			 $("button[name=btn003]").click(function(){
				 var id=this.id;
				 console.log(id);
				 var numb=id.slice(4);
						$.ajax({
							type : "DELETE",
							url : "role/remove/"+numb,
							cache : true,
							async : true,
							success: function (data){
					        		alert("删除成功了");
					        		window.location.href="role.html";
						    },
						});
					});
			 //--删除结束--//
			//---分页开始---//
			  var totalPagesnumber=json.totalPages;
			  $(".pagination").empty();
			  $(".pagination").append('<li class=""><a class="page-link" href="#" id="firstpage">首页</a></li>' );
			  $(".pagination").append('<li class=""><a class="page-link" href="#" id="previouspage">前一页</a></li>' );
			  for(var j=0;j<totalPagesnumber;j++){
				  $(".pagination").append(' <li class="page-item" id="pageno'+j+'"><a class="page-link" href="#">'+(j+1)+'</a></li>');
			  }
			  $(".pagination").append('<li class=""><a class="page-link" href="#" id="nextpage">下一页</a></li>' );
			  $(".pagination").append('<li class=""><a class="page-link" href="#" id="lastpage">尾页</a></li>' );
			  
			//把当前页，颜色变为蓝色
				$(".page-item").removeClass("active");
				$("#pageno"+startpage).addClass("active");
				//分页跳转
				$(".page-item").click(function(){
					var idval=this.id;
					startpage=idval.substr(6);
					getData();
				}) 
					
				//上一页
				$("#previouspage").click(function(){
					var curr=new Number(startpage);
					if(curr>0){
						startpage=curr-1;
					}else{
						startpage=0;
					}
					
					getData();
				})
						
				//下一页
				$("#nextpage").click(function(){
					var curr=new Number(startpage) ;
					if(curr<totalPagesnumber-1){
						startpage=curr+1;
					}else{
						startpage=totalPagesnumber-1;
					}
					
					getData();
				})
				
				//首页
				$("#firstpage").click(function(){
					startpage=0;
					  getData();
					
				}) 
				
				//尾页
				$("#lastpage").click(function(){
					startpage=totalPagesnumber-1;
					getData();
				})
			 //--分页结束--//
		 });
	 }	
})