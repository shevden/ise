<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Liquor Shop by DS</title>
    <link href="css/global/global.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/header/header.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/footer.css" type="text/css" rel="stylesheet"/>
    <link href="css/error/error.css" type="text/css" rel="stylesheet"/>
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
            <h1>Here are your notes:</h1>

            <div class="half" style="margin-bottom: 20px;">
                <div id="customer-form">
                    <c:choose>
                        <c:when test="${empty noteList}">
                            You do not have any note at the moment.
                        </c:when>
                        <c:otherwise>
                            <ul style="width: 65%; text-align: right;">
                            <c:forEach var="i" items="${noteList}">
                                <li>
                                    <c:if test="${not empty i.itemId and i.itemId ne 0}">
                                        <img src="images/global/play_icon.png" width="25px" height="25px;"/>
                                    </c:if>
                                    <a href="note?id=${i.id}" style="font-size: 1.5em; color: white;">${i.title}</a>
                                    <a href="saveNote?id=${i.id}"><img src="images/global/edit_icon.png" width="25px" height="25px"/></a>
                                </li>
                            </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
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
</body>

</html>
