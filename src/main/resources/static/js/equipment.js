$(document).ready(function(){
//	创建设备
    //保存
	 $("#createEquipmentSave").click(function(data){
        var equipmentName=$("input[name='createEquipmentName']").val();
        var equipmentLocation=$("input[name='createEquipmentLocation']").val();
        var weldingType=$("input[name='createWeldingType']").val();
        var useStatus=$("input[name='createUseStatus']").val();

        var data = {
            "equipmentName": equipmentName,
            "weldingType": weldingType,
            "equipmentLocation": equipmentLocation,
            "useStatus": useStatus,


        }
        $.ajax({
            type: "POST",
            url: "production/equipment",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="equipment.html";
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

        $.getJSON("production/equipment/"+startpage+"/"+startsize,function(json){


            console.log(json);

            var contentdata=json.data.content;
            $("#tbodyEquipmentBtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyEquipmentBtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].equipmentId
                    +"</td>"
                    +"<td>"+contentdata[i].equipmentName
                    +"</td>"
                    +"<td>"+contentdata[i].equipmentLocation
                    +"</td>"
                    +"<td>"+contentdata[i].weldingType
                    +"</td>"
                    +"<td>"+contentdata[i].useStatus
                    +"</td>"
                    +"<td>"+contentdata[i].weldingVoltage
                    +"</td>"
                    +"<td>"+contentdata[i].weldingCurrent
                    +"</td>"
                    +"<td>"+contentdata[i].weldingSpeed
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].equipmentId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].equipmentId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
           $("button[name='editBtn']").click(function(){
                var equipmentId=this.id
                $.getJSON("production/equipment/"+equipmentId,function(data){
                    console.log(data);
                    var setEquipmentId=data.data.equipmentId;
                    var setEquipmentName=data.data.equipmentName;
                    var setEquipmentLocation=data.data.equipmentLocation;
                    var setWeldingType=data.data.weldingType;
                    var setUseStatus=data.data.useStatus;
                    var setWeldingVoltage=data.data.weldingVoltage;
                    var setWeldingCurrent=data.data.weldingCurrent;
                    var setWeldingSpeed=data.data.weldingSpeed;

                    $("#equipmentId2").text(setEquipmentId);
                    $("input[name='equipmentName']").val(setEquipmentName);
                    $("input[name='equipmentLocation']").val(setEquipmentLocation);
                    $("input[name='weldingType']").val(setWeldingType);
                    $("input[name='useStatus']").val(setUseStatus);
                    $("input[name='weldingVoltage']").val(setWeldingVoltage);
                    $("input[name='weldingCurrent']").val(setWeldingCurrent);
                    $("input[name='weldingSpeed']").val(setWeldingSpeed);
                });
            })


            //保存修改
            $("#equitmentSaveBtn").click(function(data){
            	var id=$("#equipmentId2").text();
                var equipmentName=$("input[name='equipmentName']").val();
                var equipmentLocation=$("input[name='equipmentLocation']").val();
                var weldingType=$("input[name='weldingType']").val();
                var useStatus=$("input[name='useStatus']").val();
                var weldingVoltage=$("input[name='weldingVoltage']").val();
                var weldingCurrent=$("input[name='weldingCurrent']").val();
                var weldingSpeed=$("input[name='weldingSpeed']").val();
                var data = {
                    "equipmentName": equipmentName,
                    "equipmentLocation": equipmentLocation,
                    "weldingType": weldingType,
                    "useStatus": useStatus,
                    "weldingVoltage": weldingVoltage,
                    "weldingCurrent": weldingCurrent,
                    "weldingSpeed": weldingSpeed,
                }
                $.ajax({
                    type: "PUT",
                    url: "production/equipment/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="equipment.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var equipmentId=this.id;
                console.log(equipmentId);
                $.ajax({
                    type : "DELETE",
                    url : "production/equipment/"+equipmentId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="equipment.html";
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