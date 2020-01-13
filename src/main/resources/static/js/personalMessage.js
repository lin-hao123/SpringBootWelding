 $(document).ready(function(){
	 $.ajax({
		   type: "GET",
		   url: "user/personalMessage",
		   headers: {"TOKEN":localStorage.getItem("token")},
		   contentType: 'application/json',
		   success: function(msg){
			   console.log(msg);
			   $("#userId").text(msg.data.id);
			   $("#userName").text(msg.data.name);
			   $("#department").text(msg.data.partment);
			   $("#position").text(msg.data.position);
			   $("#sex").text(msg.data.sex);
			   $("#age").text(msg.data.age);
			   $("#tel").text(msg.data.tel);
			   $("#status").text(msg.data.status);
			   $("#createTime").text(msg.data.createTime);
			   $("#credential").text(msg.data.credential);
				 var roleArr = [];
				 var permissionArr=[];
				 var new_permissionArr=[];
				 for (var j = 0; j <msg.data.roles.length; j++) {
					 roleArr.push("    "+msg.data.roles[j].detail);
					 for(var i=0;i<msg.data.roles[j].permissions.length;i++){
						 permissionArr.push(msg.data.roles[j].permissions[i].name);
					 }
				}
				 $("#role").text(roleArr);
				  for(var m=0;m<permissionArr.length;m++) {
					  　　var items=permissionArr[m];
					  　　//判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
					 　　if($.inArray(items,new_permissionArr)==-1) {
					  　　　　new_permissionArr.push("    "+items);
					  　　}
					  }
				  $("#permission").text(new_permissionArr);
		   }
		});
	
	 
})