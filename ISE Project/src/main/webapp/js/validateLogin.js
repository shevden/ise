document.getElementById("loginForm").onsubmit = function(){

    document.getElementById("jsErrorBlock").innerHTML = "";

    var email = document.getElementById("email").value;
    if (email == null || email == "") {
        document.getElementById("jsErrorBlock").innerHTML = "Email field must be filled out.";
        return false;
    }
    if(email.length > 40){
        document.getElementById("jsErrorBlock").innerHTML = "Email value must be lower than 40 symbols.";
        return false;
    }
    var atPosition = email.indexOf("@");
    var dotPosition = email.lastIndexOf(".");
    if(atPosition < 1 || dotPosition < atPosition + 2 || dotPosition + 2 >= email.length) {
        document.getElementById("jsErrorBlock").innerHTML = "Email field must be filled out according to the pattern 'email@mail.com'.";
        return false;
    }

    var password = document.getElementById("password").value;
    if (password == null || password == "") {
        document.getElementById("jsErrorBlock").innerHTML = "Password field must be filled out.";
        return false;
    }
    if(password.length > 40){
        document.getElementById("jsErrorBlock").innerHTML = "Password value must be lower than 40 symbols.";
        return false;
    }

    return true;
};