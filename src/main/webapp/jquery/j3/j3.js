$(function () {
    $("#pwd").blur(function () {
        if ($("#pwd2").val() != $("#pwd").val()) {
            alert("两次密码必须相同");
        }
        if(this.value.length>10){
            $("#pwdp").show();
        }
    });
    $("#pwd2").blur(function () {
        if ($("#pwd2").val()) {
            alert("两次密码必须相同");
        }
        if(this.value.length>10){
            $("#pwd2p").show();
        }
    });

    $("#namep").hide();

    $("#name").blur(function () {
        if(this.value.length==0){
            $("#namepp").show();
            $("#name").focus();
        }
        if(this.value.length>10){
            $("#namep").show();
            $("#name").focus();
        }
    });


    $("#btn").click(function () {
        if ($("#name").value.length==0) {
            alert("请输入名字");
        }
        if ($("#pwd").value.length==0) {
            alert("请输入密码");
        }
        if ($("#pwd2").value.length==0) {
            alert("请再次输入密码");
        }
    });
})
