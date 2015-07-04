
package brix;

import brix.risk.Ruleset;
import brix.risk.Player;
import brix.risk.Statistics;
import brix.risk.RiskSimulation;

import rx.Observable;
import rx.observables.MathObservable;


public class RiskDiceSimulator
{
	public static void main(String [] args)
	{
		System.out.println("Starting...");
		Ruleset attackerRules = new Ruleset(3, 6);
		Ruleset defenderRules = new Ruleset(2, 6);
		Player attacker = new Player(attackerRules, 10, 3);
		Player defender = new Player(defenderRules, 8, 0);
		RiskSimulation simulation = new RiskSimulation();
		//simulation.run(attacker, attackerRules, defender, defenderRules);
		
		// Run the simulation 10 times
		Observable<Statistics> simulationOutcomes = Observable
			.range(1, 2)
			.map((x) -> simulation.run(attacker, defender));

		Observable<Double> averageWins = MathObservable.averageDouble(
			simulationOutcomes.map(stat -> stat.getAttackerWon() ? 1.0 : 0.0));

		Observable<Double> averageAttackerArmyLoss = MathObservable.averageDouble(
			simulationOutcomes.map(stat -> stat.getAttackerArmiesLostPercentage()));

		Observable<Double> averageDefenderArmyLoss = MathObservable.averageDouble(
			simulationOutcomes.map(stat -> stat.getDefenderArmiesLostPercentage()));

		averageWins.subscribe(winAverage -> System.out.println("% Wins: " + (winAverage * 100)));
		averageAttackerArmyLoss.subscribe(armyDeathAvg -> System.out.println("% Attacker Armies Remaining: " + (100 * (1.0 - armyDeathAvg))));
		averageDefenderArmyLoss.subscribe(armyDeathAvg -> System.out.println("% Defender Armies Remaining: " + (100 * (1.0 - armyDeathAvg))));
	}
}
