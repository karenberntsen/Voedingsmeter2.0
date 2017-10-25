/**
 * 
 */


function setLogboekNavBarAccess() {
	getData("navBarLogboekToegang",enableToegangNavBar,"");
}

function enableToegangNavBar(response,variable) {
	if (response=="true") {
		console.log("got true back from database");
		document.getElementById("logboektab").className="nav-link";
		document.getElementById("logboektab").href="logboek.html";
		console.log($( "#logintab" ));
		console.log($( "#logintab" ).text);
		$( "#logintab" ).text("Log uit");
	} else {
		console.log("got false back from database");
		document.getElementById("logboektab").className="nav-link disabled";
		document.getElementById("logboektab").href="#";
		console.log($( "#logintab" ));
		console.log($( "#logintab" ).text);
		$( "#logintab" ).text("Log in");
	}
}

function logOut() {
	
}

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