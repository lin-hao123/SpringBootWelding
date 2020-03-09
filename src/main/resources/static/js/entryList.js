$(document).ready(function(){
//	创建出库单
    //保存
    $("#creatEntryListSave").click(function(data){
        var weldingProducts=$("input[name='createWeldingProducts']").val();
        var num=$("input[name='createNum']").val();
        var warehouse=$("input[name='createWarehouse']").val();
        var supplier=$("input[name='createSupplier']").val();
        var data = {
            "weldingProducts": weldingProducts,
            "num": num,
            "warehouse": warehouse,
            "supplier": supplier,
        }
        $.ajax({
            type: "POST",
            url: "warehouse/entryList",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="entryList.html";
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
        $.getJSON("warehouse/entryList/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyEntryListbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyEntryListbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].entryListId
                    +"</td>"
                    +"<td>"+contentdata[i].weldingProducts
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"+contentdata[i].warehouse
                    +"</td>"
                    +"<td>"+contentdata[i].supplier
                    +"</td>"
                    +"<td>"+contentdata[i].entryTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].entryListId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].entryListId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--入库单编辑显示--//
            $("button[name='editBtn']").click(function(){
                var entryListId=this.id
                $.getJSON("warehouse/entryList/"+entryListId,function(data){
                    console.log(data);
                    var setEntryListId=data.data.entryListId;
                    var setWeldingProducts=data.data.weldingProducts;
                    var setNum=data.data.num;
                    var setWarehouse=data.data.warehouse;
                    var setSupplier=data.data.supplier;
                    var setEntryTime=data.data.entryTime;

                    $("#entryListId2").text(setEntryListId);
                    $("input[name='weldingProducts']").val(setWeldingProducts);
                    $("input[name='num']").val(setNum);
                    $("input[name='warehouse']").val(setWarehouse);
                    $("input[name='supplier']").val(setSupplier);
                    $("input[name='entryTime']").val(setEntryTime);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var id=$("#entryListId2").text();
                var weldingProducts= $("input[name='weldingProducts']").val();
                var num=$("input[name='num']").val();
                var warehouse=$("input[name='warehouse']").val();
                var supplier=$("input[name='supplier']").val();
                var entryTime=$("input[name='entryTime']").val();

                var data = {
                    "weldingProducts": weldingProducts,
                    "num": num,
                    "warehouse": warehouse,
                    "supplier": supplier,
                    "entryTime": entryTime,

                }
                $.ajax({
                    type: "PUT",
                    url: "warehouse/entryList/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="entryList.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var entryListId=this.id;
                console.log(entryListId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/entryList/"+entryListId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="entryList.html";
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