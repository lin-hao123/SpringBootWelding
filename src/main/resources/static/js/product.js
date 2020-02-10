$(document).ready(function(){
//	创建设备
    //保存
    $("#createProductSave").click(function(data){
        var productName=$("input[name='createProductName']").val();
        var num=$("input[name='createNum']").val();
        var status=$("input[name='createStatus']").val();
        var customerId=$("input[name='createCustomerId']").val();
        var warehouseId=$("input[name='createWarehouseId']").val();
        var entryListId=$("input[name='createEntryListId']").val();
        var money=$("input[name='createMoney']").val();
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
            type: "POST",
            url: "warehouse/product",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="product.html";
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

        $.getJSON("warehouse/product/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyproductbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyproductbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].productId
                    +"</td>"
                    +"<td>"+contentdata[i].productName
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"+contentdata[i].status
                    +"</td>"
                    +"<td>"+contentdata[i].customerId
                    +"</td>"
                    +"<td>"+contentdata[i].warehouseId
                    +"</td>"
                    +"<td>"+contentdata[i].entryListId
                    +"</td>"
                    +"<td>"+contentdata[i].money
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].productId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].productId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--设备编辑显示--//
            $("button[name='editBtn']").click(function(){
                var productId=this.id
                $.getJSON("warehouse/product/"+productId,function(data){
                    console.log(data);
                    var setProductName=data.data.productName;
                    var setNum=data.data.num;
                    var setStatus=data.data.status;
                    var setCustomerId=data.data.customerId;
                    var setWarehouseId=data.data.warehouseId;
                    var setEntryListId=data.data.entryListId;
                    var setMoney=data.data.money;
                    $("input[name='productName']").val(setProductName);
                    $("input[name='num']").val(setNum);
                    $("input[name='status']").val(setStatus);
                    $("input[name='customerId']").val(setCustomerId);
                    $("input[name='warehouseId']").val(setWarehouseId);
                    $("input[name='entryListId']").val(setEntryListId);
                    $("input[name='money']").val(setMoney);
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
                var productId=this.id;
                console.log(productId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/product/"+productId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="product.html";
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