<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Liquor Shop by DS</title>
    <link href="css/global/global.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet" />
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet" />
    <link href="css/global/footer.css" type="text/css" rel="stylesheet" />
    <link href="css/registration/registration.css" type="text/css" rel="stylesheet" />
    <link href="css/registration/avatar_loader.css" type="text/css" rel="stylesheet" />
</head>
<body>

<div id="header-wrapper">
    <div id="header">
        <div id="site-title">Implicit Search Engine</div>
    </div>
</div>

<div id="main_wrapper">
    <div id="main">
            <div class="section">
                <h1 style="padding-bottom: 15px;">Registration</h1>
                <div class="half">
                    <div id="customer-form">
                        <form method="post" id="addNewCustomerForm" action="registration" enctype="multipart/form-data">

                            <div class="col_175 left">
                                <label for="firstName">First name:</label> <input type="text" id="firstName" name="fn" class="required input_field" value="${param.fn}"/>
                            </div>

                            <div class="col_175 right">
                                <label for="lastName">Second name:</label> <input type="text" id="lastName" name="ln" class="required input_field" value="${param.ln}"/>
                            </div>

                            <div style="width: 175px; height: 145px; float: left; margin-top: 17px;">
                                <div>
					                <img style="width: 100px; height: 100px;" id="cover" class="center" src="images/avatars/${empty param.avatarName? 'defaultAvatar.png': param.an}" >
				                </div>
				                <div class="btn-browse btn-file medium-button" style="margin-left: 32px;">
                                    Browse
                                    <input type="file" id="inputFile" name="an" onChange="setUpCoverRepresentation(this);">
                                </div>
				            </div>

                            <div style="width: 175px; float: right;  margin-top: 10px;">
                                <label for="emailToRegister">Email:<input type="text" id="emailToRegister" name="em" class="validate-email required input_field" value="${param.em}" oninput="doesExist(this.value)" /></label>
                                <label for="passwordToRegister">Password:<input type="password" id="passwordToRegister" name="password" class="required input_field" /></label>
                                <label for="passwordRetype">Retype password:<input type="password" id="passwordRetype" name="passwordRetype" class="required input_field" /></label>
                            </div>


                            <%-- User defined tag used. --%>
                            <u:captcha captchaCode="${requestScope.captchaCode}"/>

                            <div style="width: 375px; height: 60px; float: left; margin-top: 10px;">
                                <input type="submit" value="Register" id="submit" class="medium-button" style="float: left;"/>
                                <input type="reset" value="Reset" id="reset" class="medium-button" style="float:right;"/>
                            </div>
                        </form>

                    </div>
                    <div id="does-user-exists-block"></div>

                </div>

                <div id="regErrorBlock" class="errorBlock cleaner h10">
                    <c:if test="${not empty param.regErrorMessage}">
                        <c:out value="${param.regErrorMessage}" />
                    </c:if>
                </div>
            </div>
    </div>
</div>

</body>

<script type="text/javascript" src="js/registration-support.js"></script>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/validateLogin.js"></script>
<script type="text/javascript" src="js/validateRegistration.js"></script>
<script type="text/javascript" src="js/avatarUpdate.js"></script>

</html>