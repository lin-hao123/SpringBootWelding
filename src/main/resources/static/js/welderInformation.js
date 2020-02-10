$(document).ready(function(){
//	创建客户
    //保存
    $("#createWelderInformationSave").click(function(data){
        var welderName=$("input[name='createWelderName']").val();
        var welderTel=$("input[name='createWelderTel']").val();
        var sex=$("input[name='createSex']").val();
        var birthday=$("input[name='createBirthday']").val();
        var address=$("input[name='createAddress']").val();
        var trainingInformation=$("input[name='createTrainingInformation']").val();
        var data = {
            "welderName": welderName,
            "welderTel": welderTel,
            "sex": sex,
            "birthday": birthday,
            "address": address,
            "trainingInformation": trainingInformation,

        }
        $.ajax({
            type: "POST",
            url: "production/welderInformation",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="welderInformation.html";
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
        $.getJSON("production/welderInformation/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyWelderInformationbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyWelderInformationbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].welderId
                    +"</td>"
                    +"<td>"+contentdata[i].welderName
                    +"</td>"
                    +"<td>"+contentdata[i].welderTel
                    +"</td>"
                    +"<td>"+contentdata[i].sex
                    +"</td>"
                    +"<td>"+contentdata[i].birthday
                    +"</td>"
                    +"<td>"+contentdata[i].address
                    +"</td>"
                    +"<td>"+contentdata[i].trainingInformation
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].updateTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].welderId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].welderId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--仓库编辑显示--//
            $("button[name='editBtn']").click(function(){
                var welderId=this.id
                $.getJSON("production/welderInformation/"+welderId,function(data){
                    console.log(data);
                    var setWelderName=data.data.welderName;
                    var setWelderTel=data.data.welderTel;
                    var setSex=data.data.sex;
                    var setBirthday=data.data.birthday;
                    var setAddress=data.data.address;
                    var setTrainingInformation=data.data.trainingInformation;
                    $("input[name='welderName']").val(setWelderName);
                    $("input[name='welderTel']").val(setWelderTel);
                    $("input[name='sex']").val(setSex);
                    $("input[name='birthday']").val(setBirthday);
                    $("input[name='address']").val(setAddress);
                    $("input[name='trainingInformation']").val(setTrainingInformation);

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
                var welderId=this.id;
                console.log(welderId);
                $.ajax({
                    type : "DELETE",
                    url : "production/welderInformation/"+welderId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="welderInformation.html";
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