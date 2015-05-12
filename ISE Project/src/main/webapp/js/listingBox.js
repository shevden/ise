

    function fulfillEntropyBox() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var response = xmlhttp.responseText;
                var json = JSON.parse(response);
                var contentToDisplay = '';
                for(var i=0; i < json.wrapper.length; ++i){
                    contentToDisplay += '<li>' + '['+ json.wrapper[i].id + '] ' +
                        json.wrapper[i].entropy + ' - ' +
                        json.wrapper[i].question + '</li>';
                }
                document.getElementById("entropy-box").innerHTML = contentToDisplay;
            }
        };
        xmlhttp.open("GET", "api/jsr/entropy",  true);
        xmlhttp.send();
    }

        function fulfillProbabilityBox() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var response = xmlhttp.responseText;
                    var json = JSON.parse(response);
                    var contentToDisplay = '';
                    for(var i=0; i < json.wrapper.length; ++i){
                        contentToDisplay += '<li>' + '['+ json.wrapper[i].id + '] ' +
                            json.wrapper[i].probability + ' - ' +
                            json.wrapper[i].item + '</li>';
                    }
                    document.getElementById("probability-box").innerHTML = contentToDisplay;
                }
            };
            xmlhttp.open("GET", "api/jsr/probability",  true);
            xmlhttp.send();
        }