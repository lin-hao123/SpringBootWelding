$(document).ready(function(){
//	创建物料
    //保存
    $("#createUnqualifiedProductSave").click(function(data){
        var productionOrder=$("input[name='createProductionOrder']").val();
        var weldingProducts=$("input[name='createWeldingProducts']").val();
        var disqualificationReason=$("input[name='createDisqualificationReason']").val();

        var data = {
            "productionOrder": productionOrder,
            "weldingProducts": weldingProducts,
            "disqualificationReason": disqualificationReason,

        }
        $.ajax({
            type: "POST",
            url: "quality/unqualifiedProduct",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="unqualifiedProduct.html";
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

        $.getJSON("quality/unqualifiedProduct/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyUnqualifiedProductBtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyUnqualifiedProductBtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].unqualifiedProductId
                    +"</td>"
                    +"<td>"+contentdata[i].productionOrder
                    +"</td>"
                    +"<td>"+contentdata[i].weldingProducts
                    +"</td>"
                    +"<td>"+contentdata[i].discoveryTime
                    +"</td>"
                    +"<td>"+contentdata[i].disqualificationReason
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].unqualifiedProductId+" name='deleteBtn' >报废</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].unqualifiedProductId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--物料编辑显示--//
            $("button[name='editBtn']").click(function(){
                var unqualifiedProductId=this.id
                $.getJSON("quality/unqualifiedProduct/"+unqualifiedProductId,function(data){
                    console.log(data);
                    var setUnqualifiedProductId=data.data.unqualifiedProductId;
                    var setProductionOrder=data.data.productionOrder;
                    var setWeldingProducts=data.data.weldingProducts;
                    var setDisqualificationReason=data.data.disqualificationReason;


                    $("input[name='unqualifiedProductId']").val(setUnqualifiedProductId);
                    $("input[name='productionOrder']").val(setProductionOrder);
                    $("input[name='weldingProducts']").val(setWeldingProducts);
                    $("input[name='disqualificationReason']").val(setDisqualificationReason);


                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var unqualifiedProductId=$("input[name='unqualifiedProductId']").val();
                var productionOrder=$("input[name='productionOrder']").val();
                var weldingProducts=$("input[name='weldingProducts']").val();
                var disqualificationReason=$("input[name='disqualificationReason']").val();


                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "unqualifiedProductId": unqualifiedProductId,
                    "productionOrder": productionOrder,
                    "weldingProducts": weldingProducts,
                    "disqualificationReason": disqualificationReason,

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
                var unqualifiedProductId=this.id;
                console.log(unqualifiedProductId);
                $.ajax({
                    type : "DELETE",
                    url : "quality/unqualifiedProduct/"+unqualifiedProductId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="unqualifiedProduct.html";
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