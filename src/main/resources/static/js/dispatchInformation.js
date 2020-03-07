$(document).ready(function(){
//	创建客户
    //保存
    $("#createDispatchInformationSave").click(function(data){
        var productionId=$("input[name='createProductionId']").val();
        var productName=$("input[name='createProductName']").val();
        var taskVolume=$("input[name='createTaskVolume']").val();
        var startTime=$("input[name='createStartTime']").val();
        var finishTime=$("input[name='createFinishTime']").val();
        var productionInstruction=$("input[name='createProductionInstruction']").val();
        var production=$("input[name='createProduction']").val();
        var deliveryTime=$("input[name='createDeliveryTime']").val();
        var data = {
            "productionId": productionId,
            "productName": productName,
            "taskVolume": taskVolume,
            "startTime": startTime,
            "finishTime": finishTime,
            "productionInstruction": productionInstruction,
            "production": production,
            "deliveryTime": deliveryTime,
        }
        $.ajax({
            type: "POST",
            url: "production/dispatchInformation",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="dispatchInformation.html";
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
        $.getJSON("production/dispatchInformation/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyDispatchInformationbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyDispatchInformationbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].dispatchId
                    +"</td>"
                    +"<td>"+contentdata[i].productionId
                    +"</td>"
                    +"<td>"+contentdata[i].productName
                    +"</td>"
                    +"<td>"+contentdata[i].taskVolume
                    +"</td>"
                    +"<td>"+contentdata[i].startTime
                    +"</td>"
                    +"<td>"+contentdata[i].finishTime
                    +"</td>"
                    +"<td>"+contentdata[i].productionInstruction
                    +"</td>"
                    +"<td>"+contentdata[i].production
                    +"</td>"
                    +"<td>"+contentdata[i].deliveryTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].dispatchId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].dispatchId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--仓库编辑显示--//
            $("button[name='editBtn']").click(function(){
                var dispatchId=this.id
                $.getJSON("production/dispatchInformation/"+dispatchId,function(data){
                    console.log(data);
                    var setDispatchId=data.data.dispatchId;
                    var setProductionId=data.data.productionId;
                    var setProductName=data.data.productName;
                    var setTaskVolume=data.data.taskVolume;
                    var setStartTime=data.data.startTime;
                    var setFinishTime=data.data.finishTime;
                    var setProductionInstruction=data.data.productionInstruction;
                    var setProduction=data.data.production;
                    var setDeliveryTime=data.data.deliveryTime;

                    $("#dispatchInformationId2").text(setDispatchId);
                    $("input[name='productionId']").val(setProductionId);
                    $("input[name='productName']").val(setProductName);
                    $("input[name='taskVolume']").val(setTaskVolume);
                    $("input[name='startTime']").val(setStartTime);
                    $("input[name='finishTime']").val(setFinishTime);
                    $("input[name='productionInstruction']").val(setProductionInstruction);
                    $("input[name='production']").val(setProduction);
                    $("input[name='deliveryTime']").val(setDeliveryTime);

                });
            })


            //保存修改
            $("#dispatchInformationSubbtn").click(function(data){
            	var id=$("#dispatchInformationId2").text();
                var productionId= $("input[name='productionId']").val();
                var productName=$("input[name='productName']").val();
                var taskVolume=$("input[name='taskVolume']").val();
                var startTime=$("input[name='startTime']").val();
                var finishTime=$("input[name='finishTime']").val();
                var productionInstruction=$("input[name='productionInstruction']").val();
                var production=$("input[name='production']").val();
                var deliveryTime=$("input[name='deliveryTime']").val();
               
                var data = {
                    "productionId": productionId,
                    "productName": productName,
                    "taskVolume": taskVolume,
                    "startTime": startTime,
                    "finishTime": finishTime,
                    "productionInstruction": productionInstruction,
                    "production": production,
                    "deliveryTime": deliveryTime,

                }
                $.ajax({
                    type: "PUT",
                    url: "production/dispatchInformation/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="dispatchInformation.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var dispatchId=this.id;
                console.log(dispatchId);
                $.ajax({
                    type : "DELETE",
                    url : "production/dispatchInformation/"+dispatchId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="dispatchInformation.html";
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