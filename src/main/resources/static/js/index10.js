 $(document).ready(function(){

	 //--用户名查询开始--//
	 $("#searchbtn").click(function(){	
		 $(".pagination").hide();
		 $("#insertrkbtn").hide();
		 getData2();	 
	 function getData2(){
		 var userName=$("#searchName").val();
		 $.getJSON("user/checkName/"+userName,function(data){
			 $("#tbodyuserbtn").empty();
				 var arr=[];
				 for(var j=0;j<data.roles.length;j++){
					 arr.push(" "+data.roles[j].detail);
				 }
				 $("#tbodyuserbtn").append(
						 "<tr id='tridval'>"
						 +"<td>"+data.id
						 +"</td>"
						 +"<td>"+data.name
						 +"</td>"						 		 
						 +"<td>"+ arr
						 +"</td>"
						 +"<td>"
						 +"<button type='button' class='btn btn-outline-info btn-sm' id='btn1"+data.id+"' name='btn0013' data-toggle='modal' data-target='#myModal2'>详情</button>"
						 +"&nbsp&nbsp&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id='btn3"+data.id+"' name='btn0033' >删除</button>"
						 +"&nbsp&nbsp&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal3' id='input"+data.id+"'  name='detailbtn3'>编辑</button>"
						 +"</td></tr>"
				 ) 
// 详情查看
			 $("button[name='btn0013']").click(function(){
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
				$("button[name='detailbtn3']").click(function(){
					var id=this.id
					var num=id.slice(5);
					$.getJSON("user/findById/"+num,function(data){

						var setId3=data.id;
						var setName3=data.name;
						var setPartment3=data.partment;
						 var setAge3=data.age;
						 var setSex3=data.sex;
						 var setStatus3=data.status;
						 var setTel3=data.tel;
						 $("#userId3").text(setId3);
						 $("#name3").text(setName3);
						 $("input[name='partment3']").val(setPartment3);
						$("input[name='age3']").val(setAge3);
						$("input[name='sex3']").val(setSex3);
						$("input[name='status3']").val(setStatus3);
						$("input[name='tel3']").val(setTel3);
						 var str = "";
						 var strArr = [];
						 for (var j = 0; j < data.roles.length; j++) {
						 str = data.roles[j].detail;
						 strArr.push(str);
						}
					    $.getJSON("role/showall/"+0+"/"+20,function(data2){
							   var contentdata=data2.content;
								 $("#roles3").empty();
								 for(var i=0;i<contentdata.length;i++){
									 $("#roles3").append(
										"<div>"
										+"<input type='checkbox' id='3"+i+"' value='"+contentdata[i].id+"' name='interest'/>"+contentdata[i].detail
										 +"</div>"	 
										)
									    for (var n = 0; n < strArr.length; n++) {
									        if (strArr[n] == contentdata[i].detail) {
									        	$("#3"+i).prop("checked",true);
									        }
									    }
							    }
							});
						   });
				})
//				//保存修改
					$("#subbtn3").unbind('click').click(function(data){
						var id3= $("#userId3").text();
						 var name3=$("#name3").text();
						 var partment3=$("input[name='partment3']").val();
						var age3=$("input[name='age3']").val();
						var sex3=$("input[name='sex3']").val();
						var status3=$("input[name='status3']").val();
						var tel3=$("input[name='tel3']").val();
						var chk_value =[];
						 $('input[name="interest"]:checked').each(function(){
							  var temp = {};
				               temp.id = $(this).val();				             
				               chk_value.push(temp);
					            });
						 var data2 = {
								 "id": id3,
								 "name": name3,
								 "partment": partment3,
								 "sex": sex3,
								 "age": age3,
								 "tel": tel3,
								 "status": status3,
					              "roleList": JSON.stringify(chk_value),
					            }
						 $.ajax({
			                    type: "PUT",
			                    url: "user/update",
			                    data: JSON.stringify(data2),
			                    contentType: 'application/json',
			                    success: function (json) {
			                    	console.log("code:"+json.code);
			                    	if(json.code==200){
										alert("保存成功");
										$('#myModal3').modal('hide');
										getData2();
			                    	}else{
			                    		alert("编辑失败");
			                    	}
					            }
						 });	
					});
			 //--修改结束--//
			 //--删除开始--//
			 $("button[name=btn0033]").click(function(){
				 var id=this.id;
				 var numb=id.slice(4);			
						$.ajax({
							type : "DELETE",
							url : "user/remove/"+numb,
							cache : true,
							async : true,
							success: function (data){
					        		alert("删除成功");
					        		window.location.href="index.html";
					        	
						    },
						});
					});
			 //--删除结束--//
		 });	 
	 } 
	 });
	 //--查询结束--//
	 //--取消查询--//
	 $("#selectbtn2").click(function(){
		 $("#searchName").val("");
			var startpage=0;
			window.location.href="index.html";
		});
	 //--取消查询--//	 
 })