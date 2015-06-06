<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Welcome to ISE Beta</title>
    <link href="css/global/global.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/header/header.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/footer.css" type="text/css" rel="stylesheet"/>
</head>
<body onload="fulfillResult();">

<div id="label-wrapper">
    <div id="label">
        <!--<%-- User defined tag used. --%>-->
        <u:login user="${sessionScope.user}" errorMessage="${param.loginErrorMessage}"/>
    </div>

    <div id="change-lang-block">
    </div>
</div>
<div id="header-wrapper">
    <div id="header">
        <div id="site-title">Implicit Search Engine</div>
    </div>
    <div style="width: 14em; float: right; margin-right: 20px; margin-top: -150px;">
        <c:choose>
            <c:when test="${not empty user}">
                <input type="submit" value="Attach note" class="medium-button" onclick="window.location.replace('saveNote?itemId=${item.id}')" style="background-color: #89c187"/>
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
        <div class="section" id="addNewCustomer">
            <div id="title-box" class="answer-title" style="font-size: 3em"></div>
            <div>
                <div class="answer-image-wrapper">
                    <img id="cover-box" class="answer-image center"/>
                </div>
                <div class="answer-block">
                    <div class="answer-title">Genre:</div>
                    <div id="genre-box" class="answer-text"></div>
                    <div class="answer-title">Publisher:</div>
                    <div id="publisher-box" class="answer-text"></div>
                    <div class="answer-title">Developer:</div>
                    <div id="developer-box" class="answer-text"></div>
                    <div class="answer-title">Release Date:</div>
                    <div id="release-date-box" class="answer-text"></div>
                    <div class="answer-title">Description:</div>
                    <div id="description-box" class="answer-text"></div>
                </div>
                <div style="width: 100%; height: 70px; float: left; margin-top: 10px;">
                    <input type="submit" onclick="commitResult();" value="Yes, this one" id="submit" class="big-button"
                           style="width: 13em; float: left;"/>
                    <input type="reset" onclick="continueSearch();" value="No, continue to search" id="reset"
                           class="big-button right" style="width: 13em; float: right;"/>
                </div>
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