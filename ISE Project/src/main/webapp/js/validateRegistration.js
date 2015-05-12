
    $('#addNewCustomerForm').submit(function(event){
        $('#regErrorBlock').text("");

        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var email = $('#emailToRegister').val();
        var password = $('#passwordToRegister').val();
        var passwordRetype = $('#passwordRetype').val();
        var captchaInput = $('#captchaInput').val();

        var emailRegexp = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;

        if (firstName == null || firstName == "") {
            $('#regErrorBlock').text("First name field must be filled out.");
            return false;
        } if(firstName.length > 40){
            $('#regErrorBlock').text("First name value must be lower than 40 symbols.");
            return false;
        } if(lastName == null || lastName == "") {
            $('#regErrorBlock').text("Last name field must be filled out.");
            return false;
        } if(lastName.length > 40){
            $('#regErrorBlock').text("Last name value must be lower than 40 symbols.");
            return false;
        }if(email == null || email == "") {
            $('#regErrorBlock').text("Email field must be filled out.");
            return false;
        } if(email.length > 40) {
            $('#regErrorBlock').text("Email value must be lower than 40 symbols.");
            return false;
        } if(!emailRegexp.test(email)) {
            $('#regErrorBlock').text("Email field must be filled out according to the pattern 'email@mail.com'.");
            return false;
        } if (password == null || password == "") {
            $('#regErrorBlock').text("Password field must be filled out.");
            return false;
        } if (passwordRetype == null || passwordRetype == "") {
            $('#regErrorBlock').text("Password-retype field must be filled out.");
            return false;
        } if(password.length > 40){
            $('#regErrorBlock').text("Password value must be lower than 40 symbols.");
            return false;
        } if (password != passwordRetype) {
            $('#regErrorBlock').text("Password and retyped password must be equals to each other.");
            return false;
        } if (captchaInput == null || captchaInput == "") {
            $('#regErrorBlock').text("Test number field must be filled out.");
            return false;
        }
    });