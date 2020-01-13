 $(document).ready(function(){

	 var startpage=0;
	 var startsize=8;
		getData();
		 //分页查询
		 function getData(){
			 
			 $.getJSON("permission/showall/"+startpage+"/"+startsize,function(json){
				 
			 console.log(json);
				 var contentdata=json.content;
				 $("#tbodypermissionbtn").empty();
				 for(var i=0;i<contentdata.length;i++){
					 $("#tbodypermissionbtn").append(
							 "<tr id='tridval"+i+"'>"
							 +"<td>"+contentdata[i].id
							 +"</td>"
							 +"<td>"+contentdata[i].name
							 +"</td>"						 		 
							 +"<td>"+contentdata[i].url
							 +"</td>"
							 +"<td>"
							 +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id='btn3"+contentdata[i].id+"' name='btn003' >删除 </button>&nbsp;&nbsp;&nbsp;&nbsp;"
							 +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id='input"+contentdata[i].id+"'  name='detailbtn'>编辑</button>"
							 +"</td></tr>"
					 ) 
				}
//				 编辑权限
				 $("button[name='detailbtn']").click(function(){
						var id=this.id
						var num=id.slice(5);
						$.getJSON("permission/findById/"+num,function(data){
							var setId=data.id;
							var setName=data.name;
							var setUrl=data.url;
							 $("#permissionId2").text(setId);
							$("input[name='permissionName2']").val(setName);
							$("input[name='permissionUrl2']").val(setUrl);
							   });
					})
					//保存修改
					$("#changePermission").click(function(data){
						var id= $("#permissionId2").text();
						var name=$("input[name='permissionName2']").val();
						var url=$("input[name='permissionUrl2']").val();
						 var data2 = {
								 "name": name,
					              "url": url
					            }
						 $.ajax({
			                    type: "PUT",
			                    url: "permission/update/"+id,
			                    data: JSON.stringify(data2),
			                    contentType: 'application/json',
			                    success: function (json) {
			                    	console.log(json);
			                    	if(json.code==200){
										alert("保存成功");
			                    		window.location.href="permission.html";
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
								url : "permission/remove/"+numb,
								cache : true,
								async : true,
								success: function (data){
						        		alert("删除成功了");
						        		window.location.href="permission.html";
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
		 };
		 
//		 添加权限
		 $("#createPermission").click(function(){
			 var permissionName=$("input[name='permissionName']").val();
			 var permissionUrl=$("input[name='permissionUrl']").val();
			 var data2 = {
					 "name": permissionName,
		              "url": permissionUrl
		            }
			 $.ajax({
                 type: "POST",
                 url: "permission/add",
                 data: JSON.stringify(data2),
                 contentType: 'application/json',
                 success: function (json) {
                 	console.log(json);
                 	if(json.code==200){
							alert("添加成功");
                 		window.location.href="permission.html";
                 	}else{
                 		alert("添加失败");
                 	}
		            }
			 });
		 });
		 
 });