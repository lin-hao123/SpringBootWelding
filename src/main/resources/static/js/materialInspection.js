$(document).ready(function(){
//	创建物料
    //保存
    $("#createMaterialInspectionSave").click(function(data){ 
        var weldingConsumablesId=$("input[name='createWeldingConsumablesId']").val();
        var weldingConsumables=$("input[name='createWeldingConsumables']").val();
        var detectionQuantity=$("input[name='createDetectionQuantity']").val();
        var qualifiedQuantity=$("input[name='createQualifiedQuantity']").val();
        var note=$("input[name='createNote']").val();
        var inspectionStaff=$("input[name='createInspectionStaff']").val();
        var checkedTime=$("input[name='createCheckedTime']").val();

        var data = {
            "weldingConsumablesId": weldingConsumablesId,
            "weldingConsumables": weldingConsumables,
            "detectionQuantity": detectionQuantity,
            "qualifiedQuantity": qualifiedQuantity,
            "note": note,
            "inspectionStaff": inspectionStaff,
            "checkedTime": checkedTime,

        }
        $.ajax({
            type: "POST",
            url: "quality/materialInspection",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="materialInspection.html";
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

        $.getJSON("quality/materialInspection/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyMaterialInspectionBtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyMaterialInspectionBtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].materialInspectionId
                    +"</td>"
                    +"<td>"+contentdata[i].weldingConsumablesId
                    +"</td>"
                    +"<td>"+contentdata[i].weldingConsumables
                    +"</td>"
                    +"<td>"+contentdata[i].detectionQuantity
                    +"</td>"
                    +"<td>"+contentdata[i].qualifiedQuantity
                    +"</td>"
                    +"<td>"+contentdata[i].note
                    +"</td>"
                    +"<td>"+contentdata[i].inspectionStaff
                    +"</td>"
                    +"<td>"+contentdata[i].checkedTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].materialInspectionId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].materialInspectionId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--物料编辑显示--//
          $("button[name='editBtn']").click(function(){
                var materialInspectionId=this.id
                $.getJSON("quality/materialInspection/"+materialInspectionId,function(data){
                    console.log(data);
                    
                    var setMaterialInspectionId=data.data.materialInspectionId;
                    var setWeldingConsumablesId=data.data.weldingConsumablesId;
                    var setWeldingConsumables=data.data.weldingConsumables;
                    var setDetectionQuantity=data.data.detectionQuantity;
                    var setQualifiedQuantity=data.data.qualifiedQuantity;
                    var setNote=data.data.note;
                    var setInspectionStaff=data.data.inspectionStaff;
                    var setCheckedTime=data.data.checkedTime;
                    $("#materialInspectionId2").text(setMaterialInspectionId);
                    $("input[name='weldingConsumablesId']").val(setWeldingConsumablesId);
                    $("input[name='weldingConsumables']").val(setWeldingConsumables);
                    $("input[name='detectionQuantity']").val(setDetectionQuantity);
                    $("input[name='qualifiedQuantity']").val(setQualifiedQuantity);
                    $("input[name='note']").val(setNote);
                    $("input[name='inspectionStaff']").val(setInspectionStaff);
                    $("input[name='checkedTime']").val(setCheckedTime);

                });
            })


            //保存修改
            $("#materialInspectionSubbtn").click(function(data){
            	var id=$("#materialInspectionId2").text();
                var weldingConsumablesId=$("input[name='weldingConsumablesId']").val();
                var weldingConsumables=$("input[name='weldingConsumables']").val();
                var detectionQuantity=$("input[name='detectionQuantity']").val();
                var qualifiedQuantity=$("input[name='qualifiedQuantity']").val();
                var note=$("input[name='note']").val();
                var inspectionStaff=$("input[name='inspectionStaff']").val();
                var checkedTime=$("input[name='checkedTime']").val();
                var data = {
                    "weldingConsumablesId": weldingConsumablesId,
                    "weldingConsumables": weldingConsumables,
                    "detectionQuantity": detectionQuantity,
                    "qualifiedQuantity": qualifiedQuantity,
                    "note": note,
                    "inspectionStaff": inspectionStaff,
                    "checkedTime": checkedTime,
                }
                $.ajax({
                    type: "PUT",
                    url: "quality/materialInspection/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="materialInspection.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var materialInspectionId=this.id;
                console.log(materialInspectionId);
                $.ajax({
                    type : "DELETE",
                    url : "quality/materialInspection/"+materialInspectionId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="materialInspection.html";
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