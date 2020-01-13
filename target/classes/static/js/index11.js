 $(document).ready(function(){
	var startpage=0;
	var startsize=8;
	getData();
	 //分页查询
	 function getData(){
		 
		 $.getJSON("user/showall/"+startpage+"/"+startsize,function(json){
			 var contentdata=json.content;
			 $("#tbodyuserbtn").empty();
			 for(var i=0;i<contentdata.length;i++){
				 var arr=[];
				 for(var j=0;j<json.content[i].roles.length;j++){
					 arr.push(" "+json.content[i].roles[j].detail);
				 }
				 $("#tbodyuserbtn").append(
						 "<tr id='tridval"+i+"'>"
						 +"<td>"+contentdata[i].id
						 +"</td>"
						 +"<td>"+contentdata[i].name
						 +"</td>"						 		 
						 +"<td>"+ arr
						 +"</td>"
						 +"<td>"
						 +"<button type='button' class='btn btn-outline-info btn-sm' id='btn1"+contentdata[i].id+"' name='btn001' data-toggle='modal' data-target='#myModal2'>详情</button>"
						 +"&nbsp&nbsp&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id='btn3"+contentdata[i].id+"' name='btn003' >删除</button>"
						 +"&nbsp&nbsp&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id='input"+contentdata[i].id+"'  name='detailbtn'>编辑</button>"
						 +"</td></tr>"
				 ) 
			}
// 详情查看
			 $("button[name='btn001']").click(function(){
					var id=this.id
					var num=id.slice(4);
					$.getJSON("user/findById/"+num,function(data){
						var setId=data.id;
						var setName=data.name;
						var setPartment=data.partment;
						 var setAge=data.age;
						 var setSex=data.sex;
						 var setStatus=data.status;
						 var setTel=data.tel;
						 var setCreateTime=data.createTime;
						 $("#userId1").text(setId);
						 $("#name1").text(setName);
						 $("#partment1").text(setPartment);
						$("#age1").text(setAge);
						$("#sex1").text(setSex);
						$("#status1").text(setStatus);
						$("#tel1").text(setTel);
						$("#createTime1").text(setCreateTime);
						 var roleArr = [];
						 var permissionArr=[];
						 var new_permissionArr=[];
						 for (var j = 0; j < data.roles.length; j++) {
							 roleArr.push(" "+data.roles[j].detail);
							 for(var i=0;i<data.roles[j].permissions.length;i++){
								 permissionArr.push(data.roles[j].permissions[i].name);
								  for(var m=0;m<permissionArr.length;m++) {
									  　　var items=permissionArr[m];
									  　　//判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
									 　　if($.inArray(items,new_permissionArr)==-1) {
									  　　　　new_permissionArr.push(items);
									  　　}
									  }
							 }
						}
						 $("#roles1").text(roleArr);		 
						 $("#permission1").empty();
						 for(var n=0;n<new_permissionArr.length;n++){
							 $("#permission1").append(
								"<div class='form-check disabled'>"
								+"<input type='checkbox' id='permission1"+n+"' value='' name='interest' disabled/>"+permissionArr[n]
								 +"</div>"	 
								)
								$("#permission1"+n).prop("checked",true);
					    }
					});
			 });
			//--用户编辑--//
				$("button[name='detailbtn']").click(function(){
					var id=this.id
					var num=id.slice(5);
					$.getJSON("user/findById/"+num,function(data){

						var setId=data.id;
						var setName=data.name;
						var setPartment=data.partment;
						 var setAge=data.age;
						 var setSex=data.sex;
						 var setStatus=data.status;
						 var setTel=data.tel;
						 $("#userId").text(setId);
						 $("#name").text(setName);
						 $("input[name='partment']").val(setPartment);
						$("input[name='age']").val(setAge);
						$("input[name='sex']").val(setSex);
						$("input[name='status']").val(setStatus);
						$("input[name='tel']").val(setTel);
						 var str = "";
						 var strArr = [];
						 for (var j = 0; j < data.roles.length; j++) {
						 str = data.roles[j].detail;
						 strArr.push(str);
						}
					    $.getJSON("role/showall/"+0+"/"+20,function(data2){
							 console.log(data2.content);
							   var contentdata=data2.content;
								 $("#roles").empty();
								 for(var i=0;i<contentdata.length;i++){
									 $("#roles").append(
										"<div>"
										+"<input type='checkbox' id='"+i+"' value='"+contentdata[i].id+"' name='interest'/>"+contentdata[i].detail
										 +"</div>"	 
										)
									    for (var n = 0; n < strArr.length; n++) {
									        if (strArr[n] == contentdata[i].detail) {
									        	$("#"+i).prop("checked",true);
									        }
									    }
							    }
							});
						   });
				})
				//保存修改
					$("#subbtn").unbind('click').click(function(data){
						var id= $("#userId").text();
						 var name=$("#name").text();
						var age=$("input[name='age']").val();
						var partment=$("input[name='partment']").val();
						var sex=$("input[name='sex']").val();
						var status=$("input[name='status']").val();
						var tel=$("input[name='tel']").val();
						var chk_value =[];
						console.log("------------"+partment);
						 $('input[name="interest"]:checked').each(function(){
							  var temp = {};
				               temp.id = $(this).val();
				               chk_value.push(temp);
					            });
						 var data2 = {
								 "id": id,
								 "name": name,
								 "partment": partment,
								 "sex": sex,
								 "age": age,
								 "tel": tel,
								 "status": status,
					              "roleList": JSON.stringify(chk_value)
					            }
						 console.log(data2);
						 $.ajax({
			                    type: "PUT",
			                    url: "user/update",
			                    data: JSON.stringify(data2),
			                    contentType: 'application/json',
			                    success: function (json) {
			                    	console.log(json);
			                    	if(json.code==200){
										alert("保存成功");
			                    		window.location.href="index.html";
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
				 var numb=id.slice(4);
				 console.log(numb);					
						$.ajax({
							type : "DELETE",
							url : "user/remove/"+numb,
							cache : true,
							async : true,
							success: function (data){
					        		alert("删除成功了");
					        		window.location.href="index.html";
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
	/* 创建用户*/
	 $("button[name='createUserbtn']").click(function(){
			
			    $.getJSON("role/showall/"+0+"/"+30,function(data2){
					 console.log(data2.content);					 
					   var contentdata=data2.content;
						 $("#createRoles").empty();
						 for(var i=0;i<contentdata.length;i++){
							 $("#createRoles").append(
								"<div>"
								+"<input type='checkbox' id='"+i+"' value='"+contentdata[i].id+"' name='createRolesName'/>"+contentdata[i].detail
								 +"</div>"	 
								)
					    }
					});
				  
		})
	/* 确定*/
		$("#createUser").click(function(data){
			 var name=$("input[name='createName']").val();
			 var pwd=$("input[name='createPwd']").val();
			 var partment=$("input[name='createPartment']").val();
			var age=$("input[name='createAge']").val();
			var sex=$("input[name='createSex']").val();
			var status=$("input[name='createStatus']").val();
			var tel=$("input[name='createTel']").val();
			var chk_value =[];
			 $('input[name="createRolesName"]:checked').each(function(){
				  var temp = {};
	               temp.id = $(this).val();
	               chk_value.push(temp);
		            });
			 var data2 = {
					 "name": name,
					 "pwd": pwd,
					 "partment": partment,
					 "sex": sex,
					 "age": age,
					 "tel": tel,
					 "status": status,
		              "roleList": JSON.stringify(chk_value)
		            }
			 $.ajax({
                    type: "POST",
                    url: "user/createUserForm",
                    data: JSON.stringify(data2),
                    contentType: 'application/json',
                    success: function (json) {
                    	console.log(json);
                    	if(json.code==200){
							alert("保存成功");
                    		window.location.href="index.html";
                    	}else{
                    		alert("编辑失败");
                    	}
		            }
			 });	
		});
})