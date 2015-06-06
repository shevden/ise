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
            <h1>The requested resource was not found (404)</h1>
            <div class="half" style="margin-top: 5%;">
                <div id="customer-form">
                        <div style="margin: 20px;">
                           There was some trouble with loading of page that was requested. Here may be reason why the error occurred:
                        </div>
                        <ul id="solution-list">
                            <li>
                        	    If you entered URL by hand, check it again, please. Perhaps, there might be a misprint in the address of the target page.
                        	</li>
                        	<li>
                                The resource might be unavailable due to the technical work on our server.
                        	</li>
                        	<li>
                                You might need to log in at the system to get access to this resource.
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
