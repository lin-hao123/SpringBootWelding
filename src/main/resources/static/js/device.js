$(document).ready(function(){
//	创建设备
    //保存
    $("#createDeviceSave").click(function(data){
        var deviceId=$("input[name='createDeviceId']").val();
        var deviceName=$("input[name='createDeviceName']").val();
        var weldingType=$("input[name='createWeldingType']").val();
        var location=$("input[name='createLocation']").val();
        var vendor=$("input[name='createVendor']").val();
        var vendorTel=$("input[name='createVendorTel']").val();
        var responsible=$("input[name='createResponsible']").val();
        var data = {
            "deviceId": deviceId,
            "deviceName": deviceName,
            "vendor": vendor,
            "vendorTel": vendorTel,
            "weldingType": weldingType,
            "location": location,
            "responsible": responsible,

        }
        $.ajax({
            type: "POST",
            url: "deviceController/device",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="deviceMessage.html";
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

        $.getJSON("deviceController/device/"+startpage+"/"+startsize,function(json){


            console.log(json);

            var contentdata=json.data.content;
            $("#tbodydevicebtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodydevicebtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].deviceId
                    +"</td>"
                    +"<td>"+contentdata[i].deviceName
                    +"</td>"
                    +"<td>"+contentdata[i].weldingType
                    +"</td>"
                    +"<td>"+contentdata[i].location
                    +"</td>"
                    +"<td>"+contentdata[i].vendor
                    +"</td>"
                    +"<td>"+contentdata[i].vendorTel
                    +"</td>"
                    +"<td>"+contentdata[i].purchaseTime
                    +"</td>"
                    +"<td>"+contentdata[i].responsible
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].deviceId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].deviceId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
            $("button[name='editBtn']").click(function(){
                var deviceId=this.id
                $.getJSON("deviceController/device/"+deviceId,function(data){
                    console.log(data);
                    var setDeviceId=data.data.deviceId;
                    var setDeviceName=data.data.deviceName;
                    var setWeldingType=data.data.weldingType;
                    var setLocation=data.data.location;
                    var setVendor=data.data.vendor;
                    var setVendorTel=data.data.vendorTel;
                    var setPurchaseTime=data.data.purchaseTime;
                    var setResponsible=data.data.responsible;

                    $("#deviceId2").text(setDeviceId);
                    $("input[name='deviceName']").val(setDeviceName);
                    $("input[name='weldingType']").val(setWeldingType);
                    $("input[name='location']").val(setLocation);
                    $("input[name='vendor']").val(setVendor);
                    $("input[name='vendorTel']").val(setVendorTel);
                    $("input[name='purchaseTime']").val(setPurchaseTime);
                    $("input[name='responsible']").val(setResponsible);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){

                var id= $("#deviceId2").text();
                var deviceName=$("input[name='deviceName']").val();
                var weldingType=$("input[name='weldingType']").val();
                var location=$("input[name='location']").val();
                var vendor=$("input[name='vendor']").val();
                var vendorTel=$("input[name='vendorTel']").val();
                var purchaseTime=$("input[name='purchaseTime']").val();
                var responsible=$("input[name='responsible']").val();

                var data = {
                    "deviceName": deviceName,
                    "vendor": vendor,
                    "vendorTel": vendorTel,
                    "weldingType": weldingType,
                    "location": location,
                    "purchaseTime": purchaseTime,
                    "responsible": responsible,

                }
                $.ajax({
                    type: "PUT",
                    url: "deviceController/device/update/"+id,
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
                var deviceId=this.id;
                console.log(deviceId);
                $.ajax({
                    type : "DELETE",
                    url : "deviceController/device/"+deviceId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="deviceMessage.html";
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