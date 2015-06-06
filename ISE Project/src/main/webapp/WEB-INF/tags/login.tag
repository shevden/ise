<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="user" type="com.ds.ise.entity.User" %>
<%@ attribute name="errorMessage" type="java.lang.String" %>

<c:choose>
    <c:when test="${not empty user}">
        <form method="post" id="logoutForm" action="logout">
            <div style="height: 35px; float: right">
                <input style="margin-top: 12px;" type="submit" value="Logout" class="medium-button"/>
            </div>
            <div style="float: right; margin: 0 15px;">
                <div style="float: none;" id="logout-label-container">Current user:</div>
                <div style="padding-top: 5px">${user.firstName}&nbsp;${user.lastName}</div>
            </div>
            <div id="avatar-window">
                <img style="width: 45px; height: 45px;" class="center" src="images/avatars/${user.avatarName}">
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" id="loginForm" action="login">
            <div style="height: 35px; float: right">
                <input type="submit" value="Login" class="medium-button" style="margin-top: 12px;"/>
            </div>
            <div class="field-container">
                <label for="password">Password:</label> <input type="password" id="password" name="password"
                                                               class="required input_field"/>
            </div>
            <div class="field-container">
                <label for="email">Email:</label> <input type="text" id="email" name="email"
                                                         class="validate-email required input_field"/>
            </div>
            <div class="label-container" id="login-label-container">Login:</div>
            <div class="label-container" id="register-label-container">
                <a href="registration">Register in the system</a>
            </div>
            <div id="js-error-block" class="error-block cleaner h10"
                 style="height: 18px; text-align: left; margin-left: 222px;clear: both;">
                <c:if test="${not empty errorMessage}"> ${errorMessage}</c:if>
            </div>
        </form>
    </c:otherwise>
</c:choose>