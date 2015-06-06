function doesExist(email) {
    if (email.length < 6) {
        document.getElementById("does-user-exists-block").innerHTML="";
        return;
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("does-user-exists-block").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "userServiceHelper?email=" + email,  true);
    xmlhttp.send();
}