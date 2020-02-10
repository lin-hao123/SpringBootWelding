$(document).ready(function(){
//	创建出库单
    //保存
    $("#creatDeliveryListSave").click(function(data){
        var productId=$("input[name='createProductId']").val();
        var warehouseId=$("input[name='createWarehouseId']").val();
        var customerId=$("input[name='createCustomerId']").val();
        var num=$("input[name='createNum']").val();
        var data = {
            "warehouseId": warehouseId,
            "productId": productId,
            "customerId": customerId,
            "num": num,
        }
        $.ajax({
            type: "POST",
            url: "warehouse/deliveryList",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="deliveryList.html";
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
        $.getJSON("warehouse/deliveryList/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodydeliveryListbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodydeliveryListbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].deliveryListId
                    +"</td>"
                    +"<td>"+contentdata[i].customerId
                    +"</td>"
                    +"<td>"+contentdata[i].productId
                    +"</td>"
                    +"<td>"+contentdata[i].warehouseId
                    +"</td>"
                    +"<td>"+contentdata[i].outTime
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].deliveryListId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].deliveryListId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--客户编辑显示--//
            $("button[name='editBtn']").click(function(){
                var deliveryListId=this.id
                $.getJSON("warehouse/deliveryList/"+deliveryListId,function(data){
                    console.log(data);
                    var setCustomerId=data.data.customerId;
                    var setProductId=data.data.productId;
                    var setWarehouseId=data.data.warehouseId;
                    var setNum=data.data.num;

                    $("input[name='customerId']").val(setCustomerId);
                    $("input[name='productId']").val(setProductId);
                    $("input[name='warehouseId']").val(setWarehouseId);
                    $("input[name='num']").val(setNum);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var customerName= $("input[name='customerName']").val();
                var productId=$("input[name='productId']").val();
                var customerTel=$("input[name='customerTel']").val();
                var address=$("input[name='address']").val();
                var sex=$("input[name='sex']").val();
                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "customerName": customerName,
                    "productId": productId,
                    "customerTel": customerTel,
                    "address": address,
                    "sex": sex,

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
                var deliveryListId=this.id;
                console.log(deliveryListId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/deliveryList/"+deliveryListId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="deliveryList.html";
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