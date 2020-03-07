$(document).ready(function(){
//	创建客户
    //保存
    $("#creatCustomerSave").click(function(data){
        var customerName=$("input[name='createCustomerName']").val();
        var productId=$("input[name='createProductId']").val();
        var customerTel=$("input[name='createCustomerTel']").val();
        var address=$("input[name='createAddress']").val();
        var sex=$("input[name='createSex']").val();
        var data = {
            "customerName": customerName,
            "productId": productId,
            "customerTel": customerTel,
            "address": address,
            "sex": sex,

        }
        $.ajax({
            type: "POST",
            url: "design/customer",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="customer.html";
                }else{
                    alert("保存失败");
                }
            }
        });
    });



    //分页查询
    var startpage=0;
    var startsize=8;
    getData();
    function getData(){
        $.getJSON("design/customer/"+startpage+"/"+startsize,function(json){
//            console.log(json);
            var contentdata=json.data.content;
            $("#tbodycustomerbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodycustomerbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].customerId
                    +"</td>"
                    +"<td>"+contentdata[i].customerName
                    +"</td>"
                    +"<td>"+contentdata[i].productId
                    +"</td>"
                    +"<td>"+contentdata[i].customerTel
                    +"</td>"
                    +"<td>"+contentdata[i].address
                    +"</td>"
                    +"<td>"+contentdata[i].sex
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].updateTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].customerId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].customerId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--客户编辑显示--//
            $("button[name='editBtn']").click(function(){
                var customerId=this.id
                $.getJSON("design/customer/"+customerId,function(data){
                    console.log("---------------",data);
                   var setId=data.data.customerId;
                    var setCustomerName=data.data.customerName;
                    var setProductId=data.data.productId;
                    var setCustomerTel=data.data.customerTel;
                    var setAddress=data.data.address;
                    var setSex=data.data.sex;
                    $("#customerId2").text(setId);
                    $("input[name='customerName']").val(setCustomerName);
                    $("input[name='productId']").val(setProductId);
                    $("input[name='customerTel']").val(setCustomerTel);
                    $("input[name='address']").val(setAddress);
                    $("input[name='sex']").val(setSex);

                });
            })


            //保存修改
            $("#subbtn").click(function(data){
            	var id= $("#customerId2").text();
                var customerName= $("input[name='customerName']").val();
                var productId=$("input[name='productId']").val();
                var customerTel=$("input[name='customerTel']").val();
                var address=$("input[name='address']").val();
                var sex=$("input[name='sex']").val();
                var data = {
                    "customerName": customerName,
                    "productId": productId,
                    "customerTel": customerTel,
                    "address": address,
                    "sex": sex,
                }
                     
				 $.ajax({
	                    type: "PUT",
	                    url: "design/customer/update/"+id,
	                    data: JSON.stringify(data),
	                    contentType: 'application/json',
	                    success: function (json) {
	                    	console.log(json);
	                    	if(json.code==200){
								alert("保存成功");
	                    		window.location.href="customer.html";
	                    	}else{
	                    		alert("编辑失败");
	                    	}
			            }
				 });
                
                
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var customerId=this.id;
                console.log(customerId);
                $.ajax({
                    type : "DELETE",
                    url : "design/customer/"+customerId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="customer.html";
                    },
                });
            });
            //--删除结束--//
            //---分页开始---//
            var totalPagesnumber=json.data.totalPages;
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