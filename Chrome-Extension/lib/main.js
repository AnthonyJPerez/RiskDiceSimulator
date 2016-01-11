
const WORKER_COUNT = 4;
const TOTAL_SIMULATION_COUNT = 100;

//// 
// Globals
////
var dom_loaded = new $.Deferred();
var workerPool = [];

// Register the main routine to fire when our
// dependencies have loaded:
$.when(dom_loaded).then(main);


(function()
{    
	dom_loaded.resolve();
	console.info("DOM is ready");
})();


// Callback function to handle messages received from the popup. The
// popup will send simulation parameters submitted by the user.
function popupMessageHandler (parameters, sender, replyToPopup)
{
	_Instrument("popupMessageHandler");
	_Log("Received message from popup -- parameters: %O, sender: %O, replyToPopup: %O", parameters, sender, replyToPopup);
	
	// Perform an AJAX request here to the server. The path is as follows:
	// 
	// /simulate/iterations/{numSimulations}/A-Armies/{initialAttackerArmies}/A-Dice/{attackerDice}/A-DiceType/{attackerDiceType}/D-Armies/{initialDefenderArmies}/D-Dice/{defenderDice}/D-DiceType/{defenderDiceType}
	//
	var url = [
		"https://lit-anchorage-1848.herokuapp.com",
		"simulate", 
		"iterations", parameters.total_simulation_count, 
		"A-Armies", parameters.attacker_armies, 
		"A-Dice", parameters.attacker_die_count,
		"A-DiceType", parameters.attacker_die_type,
		"D-Armies", parameters.defender_armies,
		"D-Dice", parameters.defender_die_count,
		"D-DiceType", parameters.defender_die_type
		].join("/");
		
	_Log("About to request: %O", url);
	$.ajax({
		url: url
	})
	.done(function(data) {
		_Log("Received response from server: %O", data);
		replyToPopup(data);
	})
	.fail(function() {
		alert("Ajax failed to fetch data.");
	});
	_InstrumentEnd();
	
	// True allows the replyToPopup reponse handler to stay alive
	// and allow its use in an asynchronous fashion.
	return true;
}


function main()
{
	_Instrument("Main");

	// Listen to messages from the popup
	chrome.runtime.onMessage.addListener(popupMessageHandler);

	_InstrumentEnd();
}
