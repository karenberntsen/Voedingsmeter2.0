/**
 * 
 */




function x() {
	getData("getDataFromLogboekdag", maakDropdown,"eenheid");
}

function voegProductToeAanLogboekdag() {
	var producthoeveelheid= {};
	var product = {};
	product["id"]=document.getElementById("product_id").value;
	producthoeveelheid["hoeveelheid"]=document.getElementById("hoeveelheid").value;
	producthoeveelheid["product"]=product;
	console.log(producthoeveelheid);
	postData("api", JSON.stringify(producthoeveelheid),function(a){},"");
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