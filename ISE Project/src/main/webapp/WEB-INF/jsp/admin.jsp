<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<body onload="onLoadAdmin();">

<div id="label-wrapper">

    <div id="label">
        <!--<%-- User defined tag used. --%>-->
        <!--<u:login user="${sessionScope.user}" errorMessage="${param.loginErrorMessage}"/>-->
    </div>

    <div id="change-lang-block">

        <!--<%-- User defined tag used. --%>-->
        <!--<u:change_lang setLanguage="${requestScope.setLanguage}" appLanguages="${applicationScope.appLanguages}"/>-->
    </div>
</div>
<div id="header-wrapper">
    <div id="header">
        <div id="site-title">Implicit Search Engine</div>
    </div>
</div>

<div id="main-wrapper">

    <div id="main">
        <div id="question-box" class="header-text" style="padding-bottom: 20px;">
        </div>
        <div>
            <div id="entropy-label">
                <h2>Entropy:</h2>
            </div>
            <div id="probability-label">
                <h2>Probability:</h2>
            </div>
        </div>
        <div class="section">
            <div class="listing-box">
                <div id="entropy-box"></div>
            </div>

            <div id="listing-controls-box">
                <input type="text" id="answer-id-box" class="standard-text-input rounded-text-input"/>
                <input type="submit" value="Stop" class="medium-button" style="color: #B40404; width: 11.2em;" onclick="stopSearchSession();"/>

                <div id="listing-controls-answer-box">
                    <input type="submit" value="Yes" class="medium-button" onclick="postAnswer(0);"/>
                    <input type="submit" value="No" class="medium-button" onclick="postAnswer(1);"/>
                    <input type="submit" value="Probably yes" class="medium-button" onclick="postAnswer(2);"/>
                    <input type="submit" value="Probably no" class="medium-button" onclick="postAnswer(3);"/>
                    <input type="submit" value="Don't know" class="medium-button" onclick="postAnswer(4);"/>
                </div>
            </div>
            <div class="listing-box">
                <div id="probability-box"></div>
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