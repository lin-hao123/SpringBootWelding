$(document).ready(function(){
//	创建物料
    //保存
    $("#creatMaterialSave").click(function(data){
        var materialId=$("input[name='createMaterialId']").val();
        var materialName=$("input[name='createMaterialName']").val();
        var materialSize=$("input[name='createMaterialSize']").val();
        var warehouse=$("input[name='createWarehouse']").val();
        var location=$("input[name='createLocation']").val();
        var leftNum=$("input[name='createLeftNum']").val();
        var supplier=$("input[name='createSupplier']").val();
        var data = {
            "materialId": materialId,
            "materialName": materialName,
            "materialSize": materialSize,
            "warehouse": warehouse,
            "location": location,
            "leftNum": leftNum,
            "supplier": supplier,

        }
        $.ajax({
            type: "POST",
            url: "warehouse/material",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="material.html";
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

        $.getJSON("warehouse/material/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyMaterialbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyMaterialbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].materialId
                    +"</td>"
                    +"<td>"+contentdata[i].materialName
                    +"</td>"
                    +"<td>"+contentdata[i].materialSize
                    +"</td>"
                    +"<td>"+contentdata[i].warehouse
                    +"</td>"
                    +"<td>"+contentdata[i].location
                    +"</td>"
                    +"<td>"+contentdata[i].leftNum
                    +"</td>"
                    +"<td>"+contentdata[i].supplier
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].materialId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].materialId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--物料编辑显示--//
            $("button[name='editBtn']").click(function(){
                var materialId=this.id
                $.getJSON("warehouse/material/"+materialId,function(data){
                    console.log(data);
                    var setMaterialId=data.data.materialId;
                    var setMaterialName=data.data.materialName;
                    var setMaterialSize=data.data.materialSize;
                    var setWarehouse=data.data.warehouse;
                    var setLocation=data.data.location;
                    var setLeftNum=data.data.leftNum;
                    var setSupplier=data.data.supplier;

                    $("#materialId2").text(setMaterialId);
                    $("input[name='materialName']").val(setMaterialName);
                    $("input[name='materialSize']").val(setMaterialSize);
                    $("input[name='warehouse']").val(setWarehouse);
                    $("input[name='location']").val(setLocation);
                    $("input[name='leftNum']").val(setLeftNum);
                    $("input[name='supplier']").val(setSupplier);


                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){

                var id= $("#materialId2").text();
                var materialName=$("input[name='materialName']").val();
                var materialSize=$("input[name='materialSize']").val();
                var warehouse=$("input[name='warehouse']").val();
                var location=$("input[name='location']").val();
                var leftNum=$("input[name='leftNum']").val();
                var supplier=$("input[name='supplier']").val();

                var data = {
                    "materialName": materialName,
                    "materialSize": materialSize,
                    "warehouse": warehouse,
                    "location": location,
                    "leftNum": leftNum,
                    "supplier": supplier,

                }
                $.ajax({
                    type: "PUT",
                    url: "warehouse/material/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="material.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var materialId=this.id;
                console.log(materialId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/material/"+materialId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="material.html";
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