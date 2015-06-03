    /*
    * Core AJAX functions of the ISE module.
    *
    * @author Denys Shevchenko
    * @version 1.0
    */

    const ON_LOAD_CLIENT_URL = "api/jsr/onLoadClient";
    const ON_LOAD_ADMIN_URL = "api/jsr/onLoadAdmin";
    const POST_ANSWER_URL = "api/jsr/processAnswer";
    const STOP_SEARCH_SESSION_URL = "api/jsr/stopSearchSession";
    const GET_RESULT_URL = "api/jsr/getResult";

    const QUESTION_BOX_ID = "question-box";
    const ENTROPY_BOX_ID = "entropy-box";
    const PROBABILITY_BOX_ID = "probability-box";
    const ANSWER_ID_BOX_ID = "answer-id-box";

    const TITLE_BOX_ID = "title-box";
    const GENRE_BOX_ID = "genre-box";
    const PUBLISHER_BOX_ID = "publisher-box";
    const DEVELOPER_BOX_ID = "developer-box";
    const RELEASE_DATE_BOX_ID = "release-date-box";
    const DESCRIPTION_BOX_ID = "description-box";
    const COVER_BOX_ID = "cover-box";

    function postAnswer(answerOptionCode){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.status == 200){
                location.reload();
            } else if(xmlhttp.status == 302){
                window.location.replace("answer");
            }
        };
        xmlhttp.open("POST", POST_ANSWER_URL);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send('{"answerOptionCode":"' + answerOptionCode + '"}');
    }

    function stopSearchSession(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            location.reload();
        };
        xmlhttp.open("POST", STOP_SEARCH_SESSION_URL);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send('{"longValue":"' + document.getElementById(ANSWER_ID_BOX_ID).value + '"}');
    }

    function onLoadClient(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var response = xmlhttp.responseText;
                var json = JSON.parse(response);
                document.getElementById(QUESTION_BOX_ID).innerHTML = json.description;
            }
        };
        xmlhttp.open("GET", ON_LOAD_CLIENT_URL);
        xmlhttp.send();
    }

    function onLoadAdmin(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var response = xmlhttp.responseText;
                var json = JSON.parse(response);
                reloadAdminPage(json)
            }
        };
        xmlhttp.open("GET", ON_LOAD_ADMIN_URL);
        xmlhttp.send();
    }

    function reloadAdminPage(json){
        document.getElementById(QUESTION_BOX_ID).innerHTML = json.description;
        var contentToDisplay = '';
        for(var i = 0; i < json.questionEntropyFulfillment.length; ++i){
            contentToDisplay += '<li>' + '[' +
                json.questionEntropyFulfillment[i].id + '] ' +
                json.questionEntropyFulfillment[i].entropy + ' - ' +
                json.questionEntropyFulfillment[i].description + '</li>';
        }
        document.getElementById(ENTROPY_BOX_ID).innerHTML = contentToDisplay;
        contentToDisplay = '';
        for(var i = 0; i < json.itemProbabilityFulfillment.length; ++i){
            contentToDisplay += '<li>' + '[' +
                json.itemProbabilityFulfillment[i].id + '] ' +
                json.itemProbabilityFulfillment[i].probability + ' - ' +
                json.itemProbabilityFulfillment[i].name + '</li>';
        }
        document.getElementById(PROBABILITY_BOX_ID).innerHTML = contentToDisplay;
    }

    function fulfillResult(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var response = xmlhttp.responseText;
                var json = JSON.parse(response);
                document.getElementById(TITLE_BOX_ID).innerHTML = json.title;
                document.getElementById(GENRE_BOX_ID).innerHTML = json.genre;
                document.getElementById(PUBLISHER_BOX_ID).innerHTML = json.publisher;
                document.getElementById(DEVELOPER_BOX_ID).innerHTML = json.developer;
                document.getElementById(RELEASE_DATE_BOX_ID).innerHTML = json.releaseDate;
                document.getElementById(DESCRIPTION_BOX_ID).innerHTML = json.description;
                document.getElementById(COVER_BOX_ID).src = json.coverPath;
            }
        };
        xmlhttp.open("GET", GET_RESULT_URL);
        xmlhttp.send();
    }

    function commitResult(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            window.location.replace("client");
        };
        xmlhttp.open("POST", STOP_SEARCH_SESSION_URL);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send();
    }

    function continueSearch(){
        window.location.replace("client");
    }
