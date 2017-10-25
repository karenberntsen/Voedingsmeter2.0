/**
 * 
 */




function x() {
	getData("getDataFromLogboekdag", maakDropdown,"eenheid");
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
        $( "#productInput" ).val( ui.item.naam );
        $( "#naam" ).val( ui.item.naam );
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