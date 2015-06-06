<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Welcome to ISE Beta</title>
    <link href="css/global/global.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet" />
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet" />
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet" />
    <link href="css/global/footer.css" type="text/css" rel="stylesheet" />
</head>
<body onload="onLoadClient();">

<div id="label-wrapper">

    <div id="label">
        <!--<%-- User defined tag used. --%>-->
        <u:login user="${sessionScope.user}" errorMessage="${param.loginErrorMessage}"/>
    </div>

</div>
<div id="header-wrapper">
    <div id="header">
        <div id="site-title">Implicit Search Engine</div>
    </div>
    <div style="width: 14em; float: right; margin-right: 20px; margin-top: -150px;">
    <c:choose>
        <c:when test="${not empty user}">
            <input type="submit" value="Add note" class="medium-button" onclick="window.location.replace('saveNote')" style="background-color: #89c187"/>
            <input type="submit" value="List notes" class="medium-button" onclick="window.location.replace('noteList')" style="background-color: #6688c1"/>
        </c:when>
        <c:otherwise>
            <p style="font-size: 1.3em;">Please, login to use note functions of this application.</p>
        </c:otherwise>
    </c:choose>
    </div>
</div>

<div id="main-wrapper">
    <div id="main">
        <div id="question-box" class="header-text">
        </div>
        <div class="section">
                <div class="question-block">
                    <input type="submit" value="Yes" class="big-button" onclick="postAnswer(0);"/>
                    <input type="submit" value="No" class="big-button" onclick="postAnswer(1);"/>
                    <input type="submit" value="Probably yes" class="big-button" onclick="postAnswer(2);"/>
                    <input type="submit" value="Probably no" class="big-button" onclick="postAnswer(3);"/>
                    <input type="submit" value="Don't know" class="big-button" onclick="postAnswer(4);"/>
                </div>
        </div>
    </div>
</div>

<div id="footer-wrapper">
    <div id="footer">
        <p>Copyright &copy; 2015 ISE by Denys Shevchenko</p>
    </div>
</div>

<script type="text/javascript" src="js/core-ajax-functions.js"></script>
</body>

</html>
