$(document).ready(function(){
//	创建物料
    //保存
    $("#createAnnouncementSave").click(function(data){
        var announcementContent=$("input[name='createAnnouncementContent']").val();

        var data = {
            "announcementContent": announcementContent
        }
        $.ajax({
            type: "POST",
            url: "quality/announcement",
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                if(json.code==200){
                    alert("保存成功");
                    window.location.href="announcement.html";
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

        $.getJSON("quality/announcement/"+startpage+"/"+startsize,function(json){
            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyAnnouncementBtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyAnnouncementBtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].announcementId
                    +"</td>"
                    +"<td>"+contentdata[i].announcementContent
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td>"
                    +"<td>"+contentdata[i].updateTime
                    +"</td>"
                    +"<td>"
                    +"&nbsp<button type='button' class='btn btn-outline-danger btn-sm'  id="+contentdata[i].announcementId+" name='deleteBtn' >删除</button>&nbsp;&nbsp;"
                    +"&nbsp<button type='button' class='btn btn-outline-success btn-sm'  data-toggle='modal' data-target='#myModal' id="+contentdata[i].announcementId+"  name='editBtn'>编辑</button>"
                    +"</td></tr>"
                )
            }


            //--公告信息编辑显示--//
            $("button[name='editBtn']").click(function(){
                var announcementId=this.id
                $.getJSON("quality/announcement/"+announcementId,function(data){
                    console.log(data);
                    var setAnnouncementId=data.data.announcementId;
                    var setAnnouncementContent=data.data.announcementContent;

                    $("input[name='announcementId']").val(setAnnouncementId);
                    $("input[name='announcementContent']").val(setAnnouncementContent);

                });
            })


            //保存修改
            $("#editSaveBtn").click(function(data){
                var announcementId=$("input[name='announcementId']").val();
                var announcementContent=$("input[name='announcementContent']").val();

                $('input[name="interest1"]:checked').each(function(){
                    var temp = {};
                    temp.id = $(this).val();
                    chk_value.push(temp);
                });
                var data = {
                    "announcementId": announcementId,
                    "announcementContent": announcementContent,
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
                var announcementId=this.id;
                console.log(announcementId);
                $.ajax({
                    type : "DELETE",
                    url : "quality/announcement/"+announcementId,
                    cache : true,
                    async : true,
                    success: function (data){
                        alert("删除成功了");
                        window.location.href="announcement.html";
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