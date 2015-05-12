    /*
    * Core AJAX functions of the ISE module.
    *
    * @author Denys Shevchenko
    * @version 1.0
    */

    const ENTROPY_SERVICE_URL = "api/jsr/entropy";
    const PROBABILITY_SERVICE_URL = "api/jsr/probability";

    const ENTROPY_BOX_ID = "entropy-box";
    const PROBABILITY_BOX_ID = "probability-box";

    function getIdFromJson(json, id){
        return json.wrapper[id].id;
    }

    function getProbabilityFromJson(json, id){
        return json.wrapper[id].probability;
    }

    function getItemFromJson(json, id){
        return json.wrapper[id].item;
    }

    function getEntropyFromJson(json, id){
        return json.wrapper[id].entropy;
    }

    function getQuestionFromJson(json, id){
        return json.wrapper[id].question;
    }

    function fulfillListingBox(serviceURL, boxId, firstGetter, secondGetter){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var response = xmlhttp.responseText;
                var json = JSON.parse(response);
                var contentToDisplay = '';
                for(var i = 0; i < json.wrapper.length; ++i){
                    contentToDisplay += '<li>' + '[' +
                        getIdFromJson(json, i) + '] ' +
                        firstGetter(json, i) + ' - ' +
                        secondGetter(json, i) + '</li>';
                }
                document.getElementById(boxId).innerHTML = contentToDisplay;
            }
        };
        xmlhttp.open("GET", serviceURL,  true);
        xmlhttp.send();
    }

    function fulfillEntropyBox() {
        fulfillListingBox(ENTROPY_SERVICE_URL, ENTROPY_BOX_ID,
            getEntropyFromJson, getQuestionFromJson);
    }

    function fulfillProbabilityBox() {
        fulfillListingBox(PROBABILITY_SERVICE_URL, PROBABILITY_BOX_ID,
                    getProbabilityFromJson, getItemFromJson);
    }

    function getQuestion(){
    }

    function postAnswer(){
    }