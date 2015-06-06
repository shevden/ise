<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="captchaCode" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="width: 175px; height: 60px; float: left; margin-left: 27%; margin-top: 10px;">
    <div>Enter this number:</div>
    <img src="captcha-generator?captchaCode=${captchaCode}" alt="Enter the number appearing in this image." border="1"/>
    <input type="text" id="captchaInput" name="captchaInput"/>
</div>

