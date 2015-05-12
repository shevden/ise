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
    <link href="css/global/header/header_label.css" type="text/css" rel="stylesheet"/>
    <link href="css/global/main_menu.css" type="text/css" rel="stylesheet" />
    <link href="css/global/content_block.css" type="text/css" rel="stylesheet" />
    <link href="css/global/footer.css" type="text/css" rel="stylesheet" />
    <link href="css/registration/registration.css" type="text/css" rel="stylesheet" />
    <link href="css/registration/avatar_loader.css" type="text/css" rel="stylesheet" />
</head>
<body>

<div id="label-wrapper">
    <div id="label">
    </div>

    <div id="change-lang-block">
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
            <h1>Bastion</h1>
            <div>

                    <form method="post" id="addNewCustomerForm" action="registration">
                        <div class="answer-image-wrapper">
                            <img id="cover" class="answer-image center" src="images/bastion.jpg" >
                        </div>
                        <div class="answer-block">
                            <div class="answer-title">Genre:</div>
                            <div class="answer-text">Action, Indie, RPG</div>
                            <div class="answer-title">Publisher:</div>
                            <div class="answer-text">Warner Bros. Interactive Entertainment	</div>
                            <div class="answer-title">Developer:</div>
                            <div class="answer-text">Supergiant Games</div>
                            <div class="answer-title">Release Date:</div>
                            <div class="answer-text">16 Aug, 2011</div>
                            <div class="answer-title">Description:</div>
                            <div class="answer-text">Bastion is an action role-playing experience that redefines storytelling in games, with a reactive narrator who marks your every move. Explore more than 40 lush hand-painted environments as you discover the secrets of the Calamity, a surreal catastrophe that shattered the world to pieces. Wield a huge arsenal of upgradeable weapons and battle savage beasts adapted to their new habitat. Finish the main story to unlock the New Game Plus mode and continue your journey! </div>
                        </div>
                        <div style="width: 100%; height: 70px; float: left; margin-top: 10px;">
                            <input type="submit" value="Yes, this one" id="submit" class="big-button" style="width: 13em; float: left;"/>
                            <input type="reset" value="No, continue to search" id="reset" class="big-button right" style="width: 13em; float: right;"/>
                        </div>
                    </form>
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