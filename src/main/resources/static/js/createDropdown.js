/**
 * 
 */
       function getData(api, functieCallback,elementId){
    	   console.log("getData");
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
    
  	   function maakDropdown(dataFromDatabase,elementId){
  		   console.log("makeDropDown");
  		   console.log(dataFromDatabase);
     		var opties = JSON.parse(dataFromDatabase);
     		for (var i = 0; i < opties.length; i++) {
				var optie = document.createElement("OPTION");
				optie.setAttribute("value", opties[i]);
				optie.appendChild(document.createTextNode(opties[i]));
				document.getElementById(elementId).appendChild(optie);
			}
	    }
  	   
  	   function camelCaseToFirstUpperCase(text) {
  		   return firstToUpperCase(text.replace(/([A-Z])/g, ' $1').toLowerCase());
  	   }
  	   
  	 function firstToUpperCase( str ) {
  	    return str.substr(0, 1).toUpperCase() + str.substr(1);
  	 }
  	   
  	   
  	   function maakInputFields(dataFromDatabase,elementId) {
    		var fields = JSON.parse(dataFromDatabase);
     		for (var i = 0; i < fields.length; i++) {
     			 var field = fields[i];
     			 if (document.getElementById(field)==null && field!="id") {
      				inputTable=document.getElementById("addProductTable");
      				var tablesize=$('#addProductTable tr').length;
      				row=inputTable.insertRow(tablesize-1);
      				labeltje=document.createElement("label");
     				labeltje.className="control-label col-sm-2";
     				var tekstNaam=field;
     				labeltje.innerHTML=camelCaseToFirstUpperCase(tekstNaam)+":";
     				row.insertCell().appendChild(labeltje);
     				inputje=document.createElement("input");
     				inputje.id=field;
     				inputje.type="number";
     				inputje.step=".1";
     				inputje.className="form-control";
     				row.insertCell();
     				row.insertCell().appendChild(inputje);
     				 
     	/*			var divje = document.createElement("div");
     				divje.className="form-group";
     				labeltje=document.createElement("label");
     				labeltje.className="control-label col-sm-2";
     				var tekstNaam=field;
     				labeltje.innerHTML=camelCaseToFirstUpperCase(tekstNaam)+":";
     				divje2=document.createElement("div");
     				divje2.className="col-sm-offset-2 col-sm-10";
     				inputje=document.createElement("input");
     				inputje.id=field;
     				inputje.type="number";
     				inputje.step=".1";
     				inputje.className="form-control";
     				divje2.appendChild(inputje);
     				labeltje.appendChild(divje2);
     				divje.appendChild(labeltje);
     				inputForm=document.getElementById("productInputForm");
     				inputForm.insertBefore(divje,document.getElementById("submitDiv"));*/
     			 }
			}
  	   }