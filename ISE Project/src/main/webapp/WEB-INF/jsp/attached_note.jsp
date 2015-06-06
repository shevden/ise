<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
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
        <div class="section" style="margin-bottom: 15px;">
            <h1>${note.title}</h1>

            <div class="half" style="margin-bottom: 50px;">
                <div id="customer-form">
                   <div style="margin-bottom: 20px;">
                       ${note.description}
                   </div>
                </div>
            </div>

            <div id="title-box" class="answer-title" style="font-size: 3em">${item.name}</div>
            <div>
                <div class="answer-image-wrapper">
                    <img id="cover-box" class="answer-image center" src="${item.coverPath}"/>
                </div>
                <div class="answer-block">
                    <div class="answer-title">Genre:</div>
                    <div id="genre-box" class="answer-text">${item.genre}</div>
                    <div class="answer-title">Publisher:</div>
                    <div id="publisher-box" class="answer-text">${item.publisher}</div>
                    <div class="answer-title">Developer:</div>
                    <div id="developer-box" class="answer-text">${item.developer}</div>
                    <div class="answer-title">Release Date:</div>
                    <div id="release-date-box" class="answer-text">${item.releaseDate}</div>
                    <div class="answer-title">Description:</div>
                    <div id="description-box" class="answer-text">${item.description}</div>
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
