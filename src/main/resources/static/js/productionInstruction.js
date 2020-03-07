$(document).ready(function(){
//	创建生产指导书
    //保存
    $("#createProductionInstructionSave").click(function(data){
        var productionOrder=$("input[name='createProductionOrder']").val();
        var weldingMethod=$("input[name='createWeldingMethod']").val();
        var weldingEquipment=$("input[name='createWeldingEquipment']").val();
        var processParameter=$("input[name='createProcessParameter']").val();
        var preWeldingTreatment=$("input[name='createPreWeldingTreatment']").val();
        var postWeldProcessing=$("input[name='createPostWeldProcessing']").val();
        var data = {
            "productionOrder": productionOrder,
            "weldingMethod": weldingMethod,
            "weldingEquipment": weldingEquipment,
            "processParameter": processParameter,
            "preWeldingTreatment": preWeldingTreatment,
            "postWeldProcessing": postWeldProcessing,

        }
        $.ajax({
            type: "POST",
            url: "design/productionInstruction",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="productionInstruction.html";
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

        $.getJSON("design/productionInstruction/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyProductionInstructionbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyProductionInstructionbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].productionInstructionId
                    +"</td>"
                    +"<td>"+contentdata[i].productionOrder
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMethod
                    +"</td>"
                    +"<td>"+contentdata[i].weldingEquipment
                    +"</td>"
                    +"<td>"+contentdata[i].processParameter
                    +"</td>"
                    +"<td>"+contentdata[i].preWeldingTreatment
                    +"</td>"
                    +"<td>"+contentdata[i].postWeldProcessing
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].productionInstructionId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].productionInstructionId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
            $("button[name='editBtn']").click(function(){
                var productionInstructionId=this.id
                $.getJSON("design/productionInstruction/"+productionInstructionId,function(data){
                    console.log(data);
                    var setProductionInstructionId=data.data.productionInstructionId;
                    var setProductionOrder=data.data.productionOrder;
                    var setWeldingMethod=data.data.weldingMethod;
                    var setWeldingEquipment=data.data.weldingEquipment;
                    var setProcessParameter=data.data.processParameter;
                    var setPreWeldingTreatment=data.data.preWeldingTreatment;
                    var setPostWeldProcessing=data.data.postWeldProcessing;
                    $("#productionOrderId2").text(setProductionInstructionId); 
                    $("input[name='productionOrder']").val(setProductionOrder);
                    $("input[name='weldingMethod']").val(setWeldingMethod);
                    $("input[name='weldingEquipment']").val(setWeldingEquipment);
                    $("input[name='processParameter']").val(setProcessParameter);
                    $("input[name='preWeldingTreatment']").val(setPreWeldingTreatment);
                    $("input[name='postWeldProcessing']").val(setPostWeldProcessing);

                });
            })


            //保存修改
            $("#productionInstructionSubbtn").click(function(data){
            	var id=$("#productionOrderId2").text();
                var productionOrder=$("input[name='productionOrder']").val();
                var weldingMethod=$("input[name='weldingMethod']").val();
                var weldingEquipment=$("input[name='weldingEquipment']").val();
                var processParameter=$("input[name='processParameter']").val();
                var preWeldingTreatment=$("input[name='preWeldingTreatment']").val();
                var postWeldProcessing=$("input[name='postWeldProcessing']").val();
                var data = {
                    "productionOrder": productionOrder,
                    "weldingMethod": weldingMethod,
                    "weldingEquipment": weldingEquipment,
                    "processParameter": processParameter,
                    "preWeldingTreatment": preWeldingTreatment,
                    "postWeldProcessing": postWeldProcessing,
                }
                $.ajax({
                    type: "PUT",
                    url: "design/productionInstruction/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="productionInstruction.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var productionInstructionId=this.id;
                console.log(productionInstructionId);
                $.ajax({
                    type : "DELETE",
                    url : "design/productionInstruction/"+productionInstructionId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="productionInstruction.html";
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