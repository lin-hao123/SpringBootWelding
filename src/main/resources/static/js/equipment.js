$(document).ready(function(){
//	创建设备
    //保存
    $("#createEquipmentSave").click(function(data){
        var equipmentName=$("input[name='createEquipmentName']").val();
        var weldingType=$("input[name='createWeldingType']").val();
        var equipmentFunction=$("input[name='createEquipmentFunction']").val();
        var useStatus=$("input[name='createUseStatus']").val();

        var data = {
            "equipmentName": equipmentName,
            "weldingType": weldingType,
            "equipmentFunction": equipmentFunction,
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
                    +"<td>"+contentdata[i].weldingType
                    +"</td>"
                    +"<td>"+contentdata[i].equipmentFunction
                    +"</td>"
                    +"<td>"+contentdata[i].useStatus
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
                    var setEquipmentName=data.data.equipmentName;
                    var setWeldingType=data.data.weldingType;
                    var setEquipmentFunction=data.data.equipmentFunction;
                    var setUseStatus=data.data.useStatus;

                    $("input[name='equipmentName']").val(setEquipmentName);
                    $("input[name='weldingType']").val(setWeldingType);
                    $("input[name='equipmentFunction']").val(setEquipmentFunction);
                    $("input[name='useStatus']").val(setUseStatus);


                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var equipmentName=$("input[name='equipmentName']").val();
                var weldingType=$("input[name='weldingType']").val();
                var equipmentFunction=$("input[name='equipmentFunction']").val();
                var useStatus=$("input[name='useStatus']").val();

                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "equipmentName": equipmentName,
                    "weldingType": weldingType,
                    "equipmentFunction": equipmentFunction,
                    "useStatus": useStatus,


                }
                $.ajax({
                    type: "PUT",
                    url: "production/equipment",
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