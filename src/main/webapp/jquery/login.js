$(function () {
    $("#pwd").blur(function () {
        var pwd=$("#pwd").val();
        if(pwd.length==0){
            $("#pwdp").show();
        }
    });
    $("#pwd2").blur(function () {
        var pwd2=$("#pwd2").val();
        if(pwd2.length==0){
            $("#pwd2p").show();
        }
    });

    $("#namep").hide();

    $("#name").blur(function () {
        var name =$("#name").val();
        if(name.length==0){
            $("#namepp").show();
            $("#name").focus();
        }
        if(name.length>10){
            $("#namep").show();
            $("#name").focus();
        }
    });


})
