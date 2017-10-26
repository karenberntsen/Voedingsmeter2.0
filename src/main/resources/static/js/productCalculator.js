/**
 * 
 */




function laadTabelLogboekdag(a) {
	console.log("laadTabelLogboekdag");
	console.log(a);
	console.log(window.location.href);
	var datum=window.location.href.split('?datum=')[1];
	document.getElementById("logboekdagheader").innerHTML=datum;
	getData("getProductHoeveelhedenFromLogboekdag/"+datum, maakLogboekTabel,"logboekTabel");
	}

function maakLogboekTabel(dataFromDatabase,elementId) {
 	console.log("maakLogboekTabel");
	console.log(dataFromDatabase);
 	logboekTabel=document.getElementById(elementId);
 	logboekTabel.innerHTML="";
	var producthoeveelheden = JSON.parse(dataFromDatabase);
 	if (producthoeveelheden.length==0) {
	 	return;
	}
 	console.log(producthoeveelheden);
 	console.log(producthoeveelheden[0]["id"]);

 	logboekTabelHeadRow=logboekTabel.createTHead().insertRow(0);
 	logboekTabelHeadRow.insertCell().innerHTML="Verwijder";
 	var doHead=true;
 	sumrow={};
 	for (var i = 0; i < producthoeveelheden.length; i++) {
 		var row=logboekTabel.insertRow();
 		var obj=producthoeveelheden[i]["product"];
 		row.insertCell().innerHTML='<form><input type="button" onclick="deletePH(\'' + producthoeveelheden[i]["id"] + '\')" class="btn btn-danger"></form>';
 		for (var property in obj) {
 		    if (obj.hasOwnProperty(property) && property!="id") {
 		    	if (property=="inhoud") {
 		    		property="hoeveelheid";
 		    		value=producthoeveelheden[i]["hoeveelheid"];
 		    		sumrow[property]=null;
 		    	} else if (typeof obj[property] == 'number') {
 		    		var value =parseFloat(obj[property]*producthoeveelheden[i]["hoeveelheid"]/obj["inhoud"]);
 		    		if (sumrow[property]==null) {
 		    			sumrow[property]=null;
 		    		}
 		    		if (value!=null) {
 		    			if (sumrow[property]==null) {
 		    				sumrow[property]=value;	
 		    			} else {
 		    				sumrow[property]=Math.round((sumrow[property]+value) * 100) / 100;
 		    			}
 		    		}
 		    	} else {
 		    		if (sumrow[property]==null) {
 		    			sumrow[property]=null;	
 		    		}
 		    		value = obj[property];
 		    	}
 		    	if (doHead) {
 		    	 	var headCell = logboekTabelHeadRow.insertCell();
 		    	 	headCell.innerHTML=camelCaseToFirstUpperCase(property);
 		    	}
 		    	row.insertCell().innerHTML=value;
 		    }
 		}
 		doHead=false;
 	}
	var row=logboekTabel.insertRow();
	row.insertCell().innerHTML=null;
	sumrow["naam"]="Totaal";
	console.log(sumrow);
 	for (var sumprop in sumrow) {
 		row.insertCell().innerHTML=sumrow[sumprop];
 	}
// 	sumTable(elementId);
}

function deletePH(phId) {
	console.log(phId);
	deleteData("delProductHoeveelheidById/"+phId,laadTabelLogboekdag,"");
}

function voegProductToeAanLogboekdag() {
	var producthoeveelheid= {};
	var product = {};
	product["id"]=document.getElementById("product_id").value;
	producthoeveelheid["hoeveelheid"]=document.getElementById("hoeveelheid").value;
	producthoeveelheid["product"]=product;
	console.log(producthoeveelheid);
	postData("ProductHoeveelheidPost/"+window.location.href.split('?datum=')[1], JSON.stringify(producthoeveelheid),laadTabelLogboekdag,"");
}

	function deleteData(api,functieCallback,inputCallbackFunction){
		var xhttp = new XMLHttpRequest();
		console.log("deleteData");
  		xhttp.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {
  					console.log('status OK');
    				if (this.responseText!=null) {
      					console.log(this.responseText);
      				//	if (inputCallbackFunction!="") {
      				//		functieCallback(this.responseText,inputCallbackFunction);
      				//	} else {
      				//		functieCallback(this.responseText);
      				//	}
      					functieCallback(inputCallbackFunction);
      				}
    			}
  		};
        xhttp.open("DELETE", "http://localhost:8080/"+api, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(null);
	}

$(document).ready(function() {
    $( "#productInput" ).autocomplete({
      minLength: 1,
      source: "http://localhost:8080/findProducts",
      focus: function( event, ui ) {
        $( "#productInput" ).val( ui.item.naam );
        return false;
      },
      select: function( event, ui ) {
    	console.log(ui.item);
        $( "#productInput" ).val( ui.item.naam );
        $( "#product_id" ).val( ui.item.id );
        $( "#eenheid").val (ui.item.eenheid );
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<div>" + item.naam + "</div>" )
        .appendTo( ul );
    };
  } );