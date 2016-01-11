
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
		"iterations", 10000, 
		"A-Armies", 10, 
		"A-Dice", 3,
		"A-DiceType", 6,
		"D-Armies", 5,
		"D-Dice", 2,
		"D-DiceType", 6
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
