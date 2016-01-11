var dom_loaded = new $.Deferred();

// Register the main routine to fire when our
// dependencies have loaded:
$.when(dom_loaded).then(main);


function convertFormToJson(form)
{
	var json = {};
    
    jQuery.each(form, function() {
        json[this.name] = this.value || '';
    });
    
    return json;
}


function showResponse(response)
{
	var sim = $('#simulationResponse');
	for (fieldName in response) {
		$('#'+fieldName, sim).text(response[fieldName]);
		console.log("%O: %O", fieldName, response[fieldName]);
	}

	sim.fadeIn();
}


function runSimulation()
{
	console.log("Running the simulation");
	
	// Clear any previous results
	$("#simulationResponse").hide();

	// Grab the form parameters
	var formArray = $("#simulationForm").serializeArray();
	var simParams = convertFormToJson(formArray);
	console.log("form JSON:", simParams);

	chrome.runtime.sendMessage(
		simParams,
		function(response) {
			console.log("Background returned %O to popup.", response);
			showResponse(response);
		});

	// Don't reset the form.
	return false;
}


function main()
{
	console.log("Main started.");

	// Setup the onclick handler for our simulate button
	$("#simulate").click(runSimulation);
	$("#simulationResponse").hide();
}


$(document).ready(function(){
	dom_loaded.resolve();
});