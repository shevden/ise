<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<body>

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
        <div class="section" id="addNewCustomer">
            <h1>Have you seen some orcs in this game?</h1>
                <div class="question-block">
                    <input type="submit" value="Yes" class="big-button"/>
                    <input type="submit" value="No" class="big-button"/>
                    <input type="submit" value="Probably yes" class="big-button"/>
                    <input type="submit" value="Probably no" class="big-button"/>
                    <input type="submit" value="Don't know" class="big-button"/>
                </div>
        </div>
    </div>
</div>

<div id="footer-wrapper">
    <div id="footer">
        <p>Copyright &copy; 2015 ISE by Denys Shevchenko</p>
    </div>
</div>

</body>

</html>
