/**
 * 
 */


function setLogboekNavBarAccess() {
	getData("navBarLogboekToegang",enableToegangNavBar,"logboektab");
}

function enableToegangNavBar(response,elementId) {
	if (response==true) {
		document.getElementById(elementId).className="nav-link";
	} else {
		document.getElementById(elementId).className="nav-link disabled";
	}
s}


function getData(api, functieCallback,elementId){
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
  		if (this.readyState == 4 && this.status == 200) {
				if (this.responseText!=null) {
        		functieCallback(this.responseText,elementId);
				}
  		}
	};
	xhttp.open("GET", "http://localhost:8080/"+api);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send();
}