<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Liquor Shop by DS</title>
    <link href="css/global/global.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header.css" type="text/css" rel="stylesheet" />
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet" />
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet" />
    <link href="css/global/footer.css" type="text/css" rel="stylesheet" />
    <link href="css/error/error.css" type="text/css" rel="stylesheet" />
</head>
<body>

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
</div>

<div id="main_wrapper">

    <div id="main">
        <div class="section">
            <h1>The server rejected access to the current page.</h1>
            <div class="half" style="margin-top: 5%;">
                <div id="customer-form">
                        <div style="margin: 20px;">
                           There are possible solutions to the current problem:
                        </div>
                        <ul id="solution-list">
                            <li>
                        	    If you have to access this page, please, check your current account type. You should login to the one, that has
                        	    appropriate user role to access some specific pages.
                        	</li>
                        	<li>
                                Perhaps, there are some mistakes in the URL for this resource. If you had entered this URL by hand, please,
                                check it once again.
                        	</li>
                        	<li>
                                You may do not have access rights to this page, because of the user role modification at your account. Please,
                                connect to the administration department to solve this problem in this case.
                        	</li>
                        </ul>
                </div>
            </div>
            <div id="jsErrorBlock" class="errorBlock cleaner h10"></div>
        </div>
    </div>
</div>

<div id="footer-wrapper">
    <div id="footer">
        <p>Copyright &copy; 2015 ISE by Denys Shevchenko</p>
    </div>
</div>
</body>

<script type="text/javascript" src="../../js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../../js/validateLogin.js"></script>

</html>
