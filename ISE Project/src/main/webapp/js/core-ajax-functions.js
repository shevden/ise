    /*
    * Core AJAX functions of the ISE module.
    *
    * @author Denys Shevchenko
    * @version 1.0
    */

    const ON_LOAD_ADMIN_URL = "api/jsr/onLoadAdmin";
    const ANSWER_ADMIN_URL = "api/jsr/answerAdmin";
    const STOP_SEARCH_SESSION_URL = "api/jsr/stopSearchSession";

    const QUESTION_BOX_ID = "question-box";
    const ENTROPY_BOX_ID = "entropy-box";
    const PROBABILITY_BOX_ID = "probability-box";
    const ANSWER_ID_BOX_ID = "answer-id-box";

    function answerAdmin(answerOptionCode){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            location.reload();
        };
        xmlhttp.open("POST", ANSWER_ADMIN_URL);
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