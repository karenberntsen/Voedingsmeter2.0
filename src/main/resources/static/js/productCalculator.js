/**
 * 
 */




function laadTabelLogboekdag() {
	var datum=window.location.href.split('?datum=')[1];
	document.getElementById("logboekdagheader").innerHTML=datum;
	getData("getProductHoeveelhedenFromLogboekdag/"+datum, maakLogboekTabel,"logboekTabel");
	}

function maakLogboekTabel(dataFromDatabase,elementId) {
 	console.log("maakLogboekTabel");
	console.log(dataFromDatabase);
	var producthoeveelheden = JSON.parse(dataFromDatabase);
 	if (producthoeveelheden.length==0) {
	 	return;
	}
 	console.log(producthoeveelheden);
 	console.log(producthoeveelheden[0]["id"]);
 	logboekTabel=document.getElementById(elementId);
 	logboekTabel.innerHTML=""
 	logboekTabelHeadRow=logboekTabel.createTHead().insertRow(0);
 	var doHead=true;
 	sumrow={};
 	for (var i = 0; i < producthoeveelheden.length; i++) {
 		var row=logboekTabel.insertRow();
 		var obj=producthoeveelheden[i]["product"];
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
 		    				sumrow[property]+=value;
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
	sumrow["naam"]="Totaal";
	console.log(sumrow);
 	for (var sumprop in sumrow) {
 		row.insertCell().innerHTML=sumrow[sumprop];
 	}
// 	sumTable(elementId);
}

function voegProductToeAanLogboekdag() {
	var producthoeveelheid= {};
	var product = {};
	product["id"]=document.getElementById("product_id").value;
	producthoeveelheid["hoeveelheid"]=document.getElementById("hoeveelheid").value;
	producthoeveelheid["product"]=product;
	console.log(producthoeveelheid);
	postData("ProductHoeveelheidPost/"+window.location.href.split('?datum=')[1], JSON.stringify(producthoeveelheid),laadTabelLogboekdag(),"");
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