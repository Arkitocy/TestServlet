$(function () {
    $("#pwd").blur(function () {
        var pwd = $("#pwd").val();
        if (/^[0-9a-zA-Z_]{6,15}$/.test(pwd)) {
            $("#pwdp").hide();

        } else  {
            $("#pwdp").show();
        }
    });
    $("#pwd2").blur(function () {
        var pwd2 = $("#pwd2").val();
        if (pwd2 != $("#pwd").val()) {
            $("#pwd3p").show();

        } else if (pwd2 == $("#pwd").val()) {
            $("#pwd3p").hide();

        }
        if (pwd2.length == 0) {
            $("#pwd2p").show();


        } else if (pwd2.length > 0) {
            $("#pwd2p").hide();


        }
    });
    $("#age").blur(function () {
        var age = $("#age").val();
        if (age < 0 || age > 120) {
            $("#agep").show();

        } else if (age > 0 && age <= 120) {
            $("#agep").hide();

        }
        if (age.length == 0) {
            $("#age2p").show();
        } else if (age.length > 0) {
            $("#age2p").hide();

        }

    })
    $("#namep").hide();
    $("#name").blur(function () {
        var name = $("#name").val();
        if (this.value.length == 0) {
            $("#namepp").show();
        } else if (name.length > 0) {
            $("#namepp").hide();
        }
        if (/^[0-9a-zA-Z_]{0,10}$/.test(name)) {
            $("#namep").hide();
        } else if (name.length >= 10) {
            $("#namep").show();
            $("#name").focus();
        }
        $.getJSON("CheckUserNameServlet",{username:name},function (json) {
            console.log(json.rs);
            var rs=json.rs+"";
           if(rs=='false'){
               $("#nameppp").hide();
           }else {
               $("#nameppp").show();
               $("#name").focus();
           }
        });
    });
})
