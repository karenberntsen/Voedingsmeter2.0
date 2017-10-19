/**
 * 
 */
function registreren() {
	console.log("registreren");
	geboortedatumElement=document.getElementById("geboortedatum");
	console.log(geboortedatumElement);
	var geboortedatum = geboortedatumElement.value;
	if (validDate(geboortedatum)) {
		geboortedatumElement.style.border="1px solid rgba(0,0,0,.15)";
		gebruikerVersturen();
	} else {
		geboortedatumElement.style.border="1px solid red";
	}

}

	function gebruikerVersturen() {
		objectVersturen("gebruikerInputFormulier","GebruikerPost",function(a){});
	}

	function productVersturen() {
  		objectVersturen("productInputForm","ProductPost",function(a){});
   	}	
  	   
  	function objectVersturen(inputFormId,api,functieCallback) {
 		var inputs = document.getElementById(inputFormId).getElementsByTagName("input");
		var selects = document.getElementById(inputFormId).getElementsByTagName("select");
		var product  = {}
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type!="button") {
				product[inputs[i].id]=inputs[i].value;
			}
		}
		for (var i = 0; i < selects.length; i++) {
			product[selects[i].id]=selects[i].value;
		}
		console.log(product);
		postData(api, JSON.stringify(product),functieCallback);
	}
  	
 	
  	function postData(api, data,functieCallback){
		var xhttp = new XMLHttpRequest();
  		xhttp.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {
  					console.log('status OK');
    				if (this.responseText!=null) {
      					console.log(this.responseText);
      					functieCallback(this.responseText);
      				}
    			}
  		};
        xhttp.open("POST", "http://localhost:8080/"+api, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(data);
	}