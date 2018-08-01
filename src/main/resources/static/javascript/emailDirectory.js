$(function () {
    $("#btnSubmit").click(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirm_password").val();
        if (password != confirmPassword) {
            //document.getElementById("demo").innerHTML = "password ne correspond pas";
            alert("bla bla");
            return false;
        }
        return true;
    });
});