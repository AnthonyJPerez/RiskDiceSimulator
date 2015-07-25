
package brix.risk;

import brix.risk.Statistics;
import brix.risk.Player;
import brix.risk.Ruleset;
import java.lang.Math;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;


/** Simulates a risk battle where the attacker fights the defender based on their respective rules. */
public class RiskSimulation
{
	private Random random;

	public RiskSimulation()
	{
		random = new Random();
	}

	/** Grabs the number of dice a player gets to use on their attack */
	private Integer getDice(final Integer numArmies, final Integer maxNumDice)
	{
		return Math.min(numArmies, maxNumDice);
	}

	private Integer getDieRoll(final Integer numDieFaces)
	{
		return random.nextInt(numDieFaces) + 1;
	}

	private List<Integer> getDieRolls(Player player, final Integer numOfDice)
	{
		List<Integer> dieRolls = new ArrayList<>();
		for (int i=0; i<numOfDice; ++i)
		{
			dieRolls.add(getDieRoll(player.getRuleset().getNumDieFaces()));
		}

		// Sort in descending order
		Collections.sort(dieRolls, (a, b) -> b.compareTo(a));
		return dieRolls;
	}

	private void runTick(Player attacker, Player defender)
	{
		//System.out.println("Attacker armies before: " + attacker.getArmies());
		//System.out.println("Defender armies before: " + defender.getArmies());

		// Get the attacker dice. In Risk, the attacker cannot attack with 1 army.
		Integer numAttackDice = getDice(attacker.getArmies()-1, attacker.getRuleset().getNumDice());

		// Get the defender dice
		Integer numDefendDice = getDice(defender.getArmies(), defender.getRuleset().getNumDice());

		// Perform an attack
		List<Integer> attackerDieRolls = getDieRolls(attacker, numAttackDice);
		List<Integer> defenderDieRolls = getDieRolls(defender, numDefendDice);

		//System.out.println("Attacker rolls: " + attackerDieRolls.toString());
		//System.out.println("Defender rolls: " + defenderDieRolls.toString());

		// Zip together the sorted die rolls together, pairing the highest
		// attack die with the highest defend die, and so on.
		// Ex. Attacker [5, 3, 1], Defender [6, 2]
		// zip results in: {[5, 6]}, {[3, 2]}
		// where {} is the observable emitted by zip, and it's contents
		// is a tuple (actually array of 2 Integers).
		Observable<Integer[]> diceRolls = Observable.zip(
			Observable.from(attackerDieRolls.toArray(new Integer[1])),
			Observable.from(defenderDieRolls.toArray(new Integer[1])),
			(a, d) -> new Integer[] {a, d}
		);

		// Update the defenders armies
		diceRolls.filter(rolls -> rolls[0] > rolls[1])
			.count()
			.subscribe(numAttackerWins -> {
				defender.setArmies(defender.getArmies() - numAttackerWins);
			});

		diceRolls.filter(rolls -> rolls[0] <= rolls[1])
			.count()
			.subscribe(numDefenderWins -> attacker.setArmies(attacker.getArmies() - numDefenderWins));
	
		//System.out.println("Attacker armies after: " + attacker.getArmies());
		//System.out.println("Defender armies after: " + defender.getArmies());
		//System.out.println("\n");
	}

	public Statistics run (Player attacker, Player defender)
	{
		Statistics stats = new Statistics();
		Boolean done = false;

		do
		{
			// If the defender's armies are 0, then the attacker was taken the country!
			if (defender.getArmies() == 0)
			{
				//System.out.println("Defender has no armies left!");
				stats.setAttackerWon(true);
				done = true;
			}

			// If the defender has gone below his minimum armies threshold, end the simulation.
			// Likewise, if the attacker has gone below the minimum threshold, end the simulation.
			// In this scenario, the attacker/defender hit the threshold before the country was
			// taken.
			else if (
				(defender.getArmies() <= defender.getMinRemaining())
				|| (attacker.getArmies() <= attacker.getMinRemaining())
				|| (attacker.getArmies() <= 1)
			)
			{
				//System.out.println("Either Attacker has no armies left, or attacker/defender has hit their minimum threshold.");
				done = true;
			}
			else
			{
				// Perform a tick of the simulation.
				runTick(attacker, defender);
			}
		} while (!done);

		// Set the final stats.
		stats.setAttackerArmiesPercentRemaining(new Double(attacker.getArmies()) / new Double(attacker.getInitialArmies()));
		stats.setDefenderArmiesPercentRemaining(new Double(defender.getArmies()) / new Double(defender.getInitialArmies()));

		// Restore the attacker and defender back to their original state prior to running the simulation.
		attacker.reset();
		defender.reset();
		return stats;
	}
}