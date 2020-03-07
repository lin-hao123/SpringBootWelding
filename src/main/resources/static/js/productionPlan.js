$(document).ready(function(){
//	创建生产计划
    //保存
    $("#createProductionPlanSave").click(function(data){
        var productName=$("input[name='createProductName']").val();
        var specification=$("input[name='createSpecification']").val();
        var productionOrder=$("input[name='createProductionOrder']").val();
        var num=$("input[name='createNum']").val();
        var deliveryDate=$("input[name='createDeliveryDate']").val();
        var data = {
            "productName": productName,
            "specification": specification,
            "productionOrder": productionOrder,
            "num": num,
            "deliveryDate": deliveryDate,

        }
        $.ajax({
            type: "POST",
            url: "design/productionPlan",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="productionPlan.html";
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

        $.getJSON("design/productionPlan/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyProductionPlanbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyProductionPlanbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].productionPlanId
                    +"</td>"
                    +"<td>"+contentdata[i].specification
                    +"</td>"
                    +"<td>"+contentdata[i].productName
                    +"</td>"
                    +"<td>"+contentdata[i].productionOrder
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"+contentdata[i].deliveryDate
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].updateTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].productionPlanId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].productionPlanId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
            $("button[name='editBtn']").click(function(){
                var productionPlanId=this.id
                $.getJSON("design/productionPlan/"+productionPlanId,function(data){
                    console.log(data);
                    var setProductionPlanId=data.data.productionPlanId;
                    var setProductName=data.data.productName;
                    var setSpecification=data.data.specification;
                    var setProductionOrder=data.data.productionOrder;
                    var setNum=data.data.num;
                    var setDeliveryDate=data.data.deliveryDate;
                    $("#productionPlanId2").text(setProductionPlanId);
                    $("input[name='productName']").val(setProductName);
                    $("input[name='specification']").val(setSpecification);
                    $("input[name='productionOrder']").val(setProductionOrder);
                    $("input[name='num']").val(setNum);
                    $("input[name='deliveryDate']").val(setDeliveryDate);

                });
            })


            //保存修改
            $("#productionPlanSubbtn").click(function(data){
            	var id=$("#productionPlanId2").text();
                var productName=$("input[name='productName']").val();
                var specification=$("input[name='specification']").val();
                var productionOrder=$("input[name='productionOrder']").val();
                var num=$("input[name='num']").val();
                var deliveryDate=$("input[name='deliveryDate']").val();
                var data = {
                    "specification": specification,
                    "productName": productName,
                    "productionOrder": productionOrder,
                    "num": num,
                    "deliveryDate": deliveryDate,
                }
                $.ajax({
                    type: "PUT",
                    url: "design/productionPlan/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="productionPlan.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var productionPlanId=this.id;
                console.log(productionPlanId);
                $.ajax({
                    type : "DELETE",
                    url : "design/productionPlan/"+productionPlanId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="productionPlan.html";
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