$(document).ready(function(){
//	创建设备
    //保存
    $("#createTaskInstructionSave").click(function(data){
        var productionOrder=$("input[name='createProductionOrder']").val();
        var productName=$("input[name='createProductName']").val();
        var num=$("input[name='createNum']").val();
        var productionDepartment=$("input[name='createProductionDepartment']").val();
        var weldingMaterialId=$("input[name='createWeldingMaterialId']").val();
        var weldingMaterialName=$("input[name='createMaterialName']").val();
        var weldingMaterialNum=$("input[name='createWeldingMaterialNum']").val();
        var currentInventory=$("input[name='createCurrentInventory']").val();
        var data = {
            "productionOrder": productionOrder,
            "productName": productName,
            "num": num,
            "productionDepartment": productionDepartment,
            "weldingMaterialId": weldingMaterialId,
            "weldingMaterialName": weldingMaterialName,
            "weldingMaterialNum": weldingMaterialNum,
            "currentInventory": currentInventory,

        }
        $.ajax({
            type: "POST",
            url: "design/taskInstruction",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="taskInstruction.html";
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

        $.getJSON("design/taskInstruction/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyTaskInstructionbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyTaskInstructionbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].taskInstructionId
                    +"</td>"
                    +"<td>"+contentdata[i].productionOrder
                    +"</td>"
                    +"<td>"+contentdata[i].productName
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"+contentdata[i].productionDepartment
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialId
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialName
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialNum
                    +"</td>"
                    +"<td>"+contentdata[i].currentInventory
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].taskInstructionId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].taskInstructionId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
            $("button[name='editBtn']").click(function(){
                var productionInstructionId=this.id
                $.getJSON("design/taskInstruction/"+productionInstructionId,function(data){
                    console.log(data);
                    var setProductionOrder=data.data.productionOrder;
                    var setProductName=data.data.productName;
                    var setNum=data.data.num;
                    var setProductionDepartment=data.data.productionDepartment;
                    var setWeldingMaterialId=data.data.weldingMaterialId;
                    var setWeldingMaterialName=data.data.weldingMaterialName;
                    var setWeldingMaterialNum=data.data.weldingMaterialNum;
                    var setCurrentInventory=data.data.currentInventory;

                    $("input[name='productionOrder']").val(setProductionOrder);
                    $("input[name='productName']").val(setProductName);
                    $("input[name='num']").val(setNum);
                    $("input[name='productionDepartment']").val(setProductionDepartment);
                    $("input[name='weldingMaterialId']").val(setWeldingMaterialId);
                    $("input[name='weldingMaterialName']").val(setWeldingMaterialName);
                    $("input[name='weldingMaterialNum']").val(setWeldingMaterialNum);
                    $("input[name='currentInventory']").val(setCurrentInventory);


                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var productName=$("input[name='productName']").val();
                var num=$("input[name='num']").val();
                var status=$("input[name='status']").val();
                var customerId=$("input[name='customerId']").val();
                var warehouseId=$("input[name='warehouseId']").val();
                var entryListId=$("input[name='entryListId']").val();
                var money=$("input[name='money']").val();
                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "productName": productName,
                    "num": num,
                    "status": status,
                    "customerId": customerId,
                    "warehouseId": warehouseId,
                    "entryListId": entryListId,
                    "money": money,

                }
                $.ajax({
                    type: "PUT",
                    url: "deviceController/device",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="deviceMessage.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var taskInstructionId=this.id;
                console.log(taskInstructionId);
                $.ajax({
                    type : "DELETE",
                    url : "design/taskInstruction/"+taskInstructionId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="taskInstruction.html";
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