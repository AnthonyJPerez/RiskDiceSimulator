
package brix;

import brix.risk.Ruleset;
import brix.risk.Player;
import brix.risk.Statistics;
import brix.risk.RiskSimulation;

import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;


public class RiskDiceSimulator
{
	public static void main(String [] args)
	{
		final Integer numSimulations = 10000;
		final Integer initialAttackerArmies = 10;
		final Integer initialDefenderArmies = 8;
		
		Ruleset attackerRules = new Ruleset(3, 6);
		//attackerRules.setNumDice(3); // Attack using three die
		//attackerRules.setNumDieFaces(6); // Each die is a 6-sided die
		
		Ruleset defenderRules = new Ruleset(2, 6);
		//defenderRules.setNumDice(2); // Defend using two die
		//defenderRules.setNumDieFaces(6); // Each die is a 6-sided die

		Player attacker = new Player(attackerRules, initialAttackerArmies, 3);
		Player defender = new Player(defenderRules, initialDefenderArmies, 0);

		RiskSimulation simulation = new RiskSimulation();
		
		// Run the simulations. The .publish() at the end makes this a
		// ConnectableObservable, meaning that it won't begin emitting
		// anything until all of the subscribers are setup and we explicitly
		// call .connect(). If we don't do this, each subscriber will cause
		// this Observable to emit items, and the next subscription will
		// cause a whole new set of items being emitted, instead of
		// each subscriber getting handed the same emitted items.
		ConnectableObservable<Statistics> simulationOutcomes = Observable
			.range(1, numSimulations)
			.map((x) -> simulation.run(attacker, defender))
			.publish();

		// Calculate the average number of times the Simulations returned with a win.
		// A "win" in a Risk battle is when the attacker successfully take the
		// defender's country (defender reaches 0 armies).
		//
		// This Observable first takes each SimulationResult output by
		// the `simulationOutcomes` observable and maps them into a 1
		// if the attacker took the country, and a 0 otherwise. Once all
		// SimulationResults are mapped, they are fed into the .averageDouble()
		// Observable, which sums all of the numbers in the stream and
		// returns the average of those numbers.
		Observable<Double> averageWins = MathObservable.averageDouble(
			simulationOutcomes.map(stat -> stat.getAttackerWon() ? 1.0 : 0.0));

		// Subscribe to our averageWins stream and print out the result.
		averageWins.subscribe(
			winAverage -> System.out.println(
				String.format(
					"Avg attacker wins: %.0f%% (%.0f wins)", 
					(winAverage * 100), (winAverage * numSimulations))));

		// Determine the average number of units that remain after successfully
		// taking a country. 
		//
		// This Observable first takes each SimulationResult output by
		// the `simulationOutcomes` observable, and filters to select only
		// those results where the attacker won. Then, the filtered results
		// are converted into a percentage representing the amount of armies
		// the attacker had left over. These percents are then averaged
		// and that average returned.
		Observable<Double> avgAttackerArmiesRemaining = MathObservable.averageDouble(
			simulationOutcomes
				.filter(stat -> stat.getAttackerWon())
				.map(stat -> stat.getAttackerArmiesPercentRemaining()));

		// Subscribe to the average attacker armies remaining observable, and 
		// print out the result.
		avgAttackerArmiesRemaining.subscribe(
			armiesRemaining -> System.out.println(
				String.format(
					"Avg attacker armies remaining on wins: %.0f%% (%.0f armies)",
					(100 * armiesRemaining), (armiesRemaining * initialAttackerArmies))));

		// Determine the average number of units that remain after failing to
		// take over a country. I use a similar method of calculation here as
		// I did for the average attacker's armies.
		Observable<Double> averageDefenderArmyLoss = MathObservable.averageDouble(
			simulationOutcomes
				.filter(stat -> !stat.getAttackerWon())
				.map(stat -> stat.getDefenderArmiesPercentRemaining()));

		// Subscribe to the average defender armies remaining observable, and 
		// print out the result.
		averageDefenderArmyLoss.subscribe(
			armiesRemaining -> System.out.println(
				String.format(
					"Avg defender armies remaining on losses: %.0f%% (%.0f armies)",
					(100 * armiesRemaining), (armiesRemaining * initialDefenderArmies))));
	
		// Now that all of our subscribers are setup, start emitting the results of 
		// the simulations.
		System.out.println(String.format("Running %d Simulations...", numSimulations));
		simulationOutcomes.connect();
		System.out.println("Finished running the simulations.");
	}
}