$(document).ready(function(){
//	创建设备
    //保存
    $("#creatMaterialRequestionSave").click(function(data){
        var userId=$("input[name='createUserId']").val();
        var materialRequestionId=$("input[name='createMaterialRequestionId']").val();
        var materialId=$("input[name='createMaterialId']").val();
        var finishTime=$("input[name='createFinishTime']").val();
        var createTime=$("input[name='createCreateTime']").val();
        var materialNote=$("input[name='createMaterialRequestionNote']").val();
        var data = {
            "userId":userId,
            "materialRequestionId":materialRequestionId,
            "materialId": materialId,
            "finishTime": finishTime,
            "createTime": createTime,
            "materialNote": materialNote,

        }
        $.ajax({
            type: "POST",
            url: "warehouse/materialRequestion",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="materialRequestion.html";
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

        $.getJSON("warehouse/materialRequestion/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodymaterialRequestionbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodymaterialRequestionbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].materialRequestionId
                    +"</td>"
                    +"<td>"+contentdata[i].userId
                    +"</td>"
                    +"<td>"+contentdata[i].materialId
                    +"</td>"
                    +"<td>"+contentdata[i].finishTime
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].materialNote
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].materialRequestionId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].materialRequestionId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //-领料单编辑显示--//
            $("button[name='editBtn']").click(function(){
                var materialRequestionId=this.id
                $.getJSON("warehouse/materialRequestion/"+materialRequestionId,function(data){
                    console.log(data);
                    var setUserId=data.data.userId;
                    var setMateriaId=data.data.materialId;
                    var setMaterialNote=data.data.materialNote;
                    $("input[name='userId']").val(setUserId);
                    $("input[name='materialId']").val(setMateriaId);
                    $("input[name='materialNote']").val(setMaterialNote);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var userId=$("input[name='userId']").val();
                var materialId=$("input[name='materialId']").val();
                var materialNote=$("input[name='materialNote']").val();
                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "userId": userId,
                    "materialId": materialId,
                    "materialNote": materialNote,
                }
                $.ajax({
                    type: "PUT",
                    url: "warehouse/materialRequestion",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (json) {
                        console.log(json);
                        if(json.code==200){
                            alert("保存成功");
                            window.location.href="materialRequestion.html";
                        }else{
                            alert("编辑失败");
                        }
                    }
                });
            });
            //--修改结束--//
            //--删除开始--//
            $("button[name=deleteBtn]").click(function(){
                var materialRequestionId=this.id;
                console.log(materialRequestionId);
                $.ajax({
                    type : "DELETE",
                    url : "warehouse/materialRequestion/"+materialRequestionId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="materialRequestion.html";
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