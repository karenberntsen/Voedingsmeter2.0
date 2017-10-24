/**
 * 
 */
function nieuweLogboekdag() {
	console.log("nieuwe logboekdag");
	datumElement=document.getElementById("datum");
	result = changeDatumInputFormStyle(datumElement);
	if (result==true) {
		objectVersturen("logboekdagInputForm","LogboekdagPost",redirectIfTrue,getUrlLogboekdag(datumElement.value));
		//postData("LogboekdagPost", JSON.stringify(datumElement.value),redirectIfTrue,getUrlLogboekdag(datumElement.value));
	}
}

function getUrlLogboekdag(datum) {
	return "logboekdag.html?datum="+datum;
}

function kiesLogboekdag() {
	console.log("kies logboekdag");
	datum=document.getElementById("logboek").value;
	window.location.replace(getUrlLogboekdag(datum));
}


function changeDatumInputFormStyle(element) {
	var datum = element.value;
	if (validDate(datum)) {
		element.style.border="1px solid rgba(0,0,0,.15)";
		return true;
	} else {
		element.style.border="1px solid red";
		return false;
	}
}

function registreren() {
	console.log("registreren");
	geboortedatumElement=document.getElementById("geboortedatum");
	console.log(geboortedatumElement);
	result = changeDatumInputFormStyle(geboortedatumElement);
	if (result==true) {
		gebruikerVersturen();
	}
}

function gebruikerLogin() {
	objectVersturen("login","gebruikerLogin",redirectIfTrue,"index.html");
}

function redirectIfTrue(response,url) {
	console.log(response);
	if (response=="true") {
		window.location.replace(url);
	}
}

	function gebruikerVersturen() {
		objectVersturen("gebruikerInputFormulier","GebruikerPost",redirectIfTrue,"login.html");
	}

	function productVersturen() {
  		objectVersturen("productInputForm","ProductPost",function(a){},"");
   	}	
  	   
  	function objectVersturen(inputFormId,api,functieCallback,inputCallbackFunction) {
 		var inputs = document.getElementById(inputFormId).getElementsByTagName("input");
		var selects = document.getElementById(inputFormId).getElementsByTagName("select");
		var product  = {}
		console.log("object versturen");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type!="button") {
				product[inputs[i].id]=inputs[i].value;
			}
		}
		for (var i = 0; i < selects.length; i++) {
			product[selects[i].id]=selects[i].value;
		}
		console.log(product);
		postData(api, JSON.stringify(product),functieCallback,inputCallbackFunction);
	}
  	
 	
  	function postData(api, data,functieCallback,inputCallbackFunction){
		var xhttp = new XMLHttpRequest();
		console.log("postData");
  		xhttp.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {
  					console.log('status OK');
    				if (this.responseText!=null) {
      					console.log(this.responseText);
      					if (inputCallbackFunction!="") {
      						functieCallback(this.responseText,inputCallbackFunction);
      					} else {
      						functieCallback(this.responseText);
      					}
      				}
    			}
  		};
        xhttp.open("POST", "http://localhost:8080/"+api, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(data);
	}