$(document).ready(function(){
    //分页查询
    var startpage=0;
    var startsize=8;
    getData();
    function getData(){

        $.getJSON("design/taskInstruction/"+startpage+"/"+startsize,function(json){

            console.log(json);
            var contentdata=json.data.content;
            $("#tbodyTaskInstructionbtn").empty();
            for(var i=0;i<contentdata.length;i++){
                $("#tbodyTaskInstructionbtn").append(
                    "<tr id='tridval"+i+"'>"
                    +"<td>"+contentdata[i].taskInstructionId
                    +"</td>"
                    +"<td>"+contentdata[i].productionOrder
                    +"</td>"
                    +"<td>"+contentdata[i].productName
                    +"</td>"
                    +"<td>"+contentdata[i].num
                    +"</td>"
                    +"<td>"+contentdata[i].productionDepartment
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialId
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialName
                    +"</td>"
                    +"<td>"+contentdata[i].weldingMaterialNum
                    +"</td>"
                    +"<td>"+contentdata[i].currentInventory
                    +"</td>"
                    +"<td>"+contentdata[i].createTime
                    +"</td></tr>"
                )
            }
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