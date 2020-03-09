$(document).ready(function(){
//	创建供应商
    //保存
    $("#creatSupplierSave").click(function(data){
        var supplierName=$("input[name='createSupplierName']").val();
        var material=$("input[name='createMaterial']").val();
        var responsible=$("input[name='createResponsible']").val();
        var supplierTel=$("input[name='createSupplierTel']").val();
        var address=$("input[name='createAddress']").val();
        var note=$("input[name='createNote']").val();

        var data = {
            "supplierName": supplierName,
            "material": material,
            "responsible": responsible,
            "supplierTel": supplierTel,
            "address": address,
            "note": note,

        }
        $.ajax({
            type: "POST",
            url: "warehouse/supplier",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="supplier.html";
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
        $.getJSON("warehouse/supplier/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodySupplierbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodySupplierbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].supplierId
                    +"</td>"
                    +"<td>"+contentdata[i].supplierName
                    +"</td>"
                    +"<td>"+contentdata[i].material
                    +"</td>"
                    +"<td>"+contentdata[i].responsible
                    +"</td>"
                    +"<td>"+contentdata[i].supplierTel
                    +"</td>"
                    +"<td>"+contentdata[i].address
                    +"</td>"
                    +"<td>"+contentdata[i].note
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].supplierId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].supplierId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--仓库编辑显示--//
            $("button[name='editBtn']").click(function(){
                var supplierId=this.id
                $.getJSON("warehouse/supplier/"+supplierId,function(data){
                    console.log(data);
                    var setSupplierId=data.data.supplierId;
                    var setSupplierName=data.data.supplierName;
                    var setMaterial=data.data.material;
                    var setResponsible=data.data.responsible;
                    var setSupplierTel=data.data.supplierTel;
                    var setAddress=data.data.address;
                    var setNote=data.data.note;

                    $("#supplierId2").text(setSupplierId);
                    $("input[name='supplierName']").val(setSupplierName);
                    $("input[name='material']").val(setMaterial);
                    $("input[name='responsible']").val(setResponsible);
                    $("input[name='supplierTel']").val(setSupplierTel);
                    $("input[name='address']").val(setAddress);
                    $("input[name='note']").val(setNote);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var id= $("#supplierId2").text();
                var supplierName= $("input[name='supplierName']").val();
                var material=$("input[name='material']").val();
                var responsible=$("input[name='responsible']").val();
                var supplierTel=$("input[name='supplierTel']").val();
                var address=$("input[name='address']").val();
                var note=$("input[name='note']").val();

                var data = {
                    "supplierName": supplierName,
                    "material": material,
                    "responsible": responsible,
                    "supplierTel": supplierTel,
                    "address": address,
                    "note": note,

                }
                $.ajax({
                    type: "PUT",
                    url: "warehouse/supplier/update/"+id,
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="supplier.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var supplierId=this.id;
                console.log(supplierId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/supplier/"+supplierId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="supplier.html";
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