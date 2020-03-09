$(document).ready(function(){
//	创建设备
    //保存
    $("#creatMaterialRequestionSave").click(function(data){
        var department=$("input[name='createDepartment']").val();
        var user=$("input[name='createUser']").val();
        var material=$("input[name='createMaterial']").val();
        var finished=$("input[name='createFinished']").val();
        var materialNote=$("input[name='createMaterialNote']").val();


        var data = {
            "department":department,
            "user":user,
            "material": material,
            "finished": finished,
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
                    +"<td>"+contentdata[i].department
                    +"</td>"
                    +"<td>"+contentdata[i].user
                    +"</td>"
                    +"<td>"+contentdata[i].material
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].finished
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
                    var setMaterialRequestionId=data.data.materialRequestionId;
                    var setDepartment=data.data.department;
                    var setUser=data.data.user;
                    var setMaterial=data.data.material;
                    var setCreateTime=data.data.createTime;
                    var setFinished=data.data.finished;
                    var setMaterialNote=data.data.materialNote;

                    $("#materialRequestionId2").text(setMaterialRequestionId);
                    $("input[name='department']").val(setDepartment);
                    $("input[name='user']").val(setUser);
                    $("input[name='material']").val(setMaterial);
                    $("input[name='createTime']").val(setCreateTime);
                    $("input[name='finished']").val(setFinished);
                    $("input[name='materialNote']").val(setMaterialNote);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){

                var id= $("#materialRequestionId2").text();
                var department=$("input[name='department']").val();
                var user=$("input[name='user']").val();
                var material=$("input[name='material']").val();
                var createTime=$("input[name='createTime']").val();
                var finished=$("input[name='finished']").val();
                var materialNote=$("input[name='materialNote']").val();

                var data = {
                    "department": department,
                    "user": user,
                    "material": material,
                    "createTime": createTime,
                    "finished": finished,
                    "materialNote": materialNote,
                }
                $.ajax({
                    type: "PUT",
                    url: "warehouse/materialRequestion/update/"+id,
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