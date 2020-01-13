$(document).ready(function(){

    $.ajax({
        type: "GET",
        url: "user/isAdmin",
        headers: {"TOKEN":localStorage.getItem("token")},
        contentType: 'application/json',
        success: function (json) {
            if("Y"==json.msg){
                $("#resourcePermission").show();
            }
        }
    });
    });