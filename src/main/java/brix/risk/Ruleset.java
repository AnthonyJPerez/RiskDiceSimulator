package brix.risk;

/** Defines the rules that an Attacker must abide by. */
public class Ruleset
{
	/** The number of dice used per action */
	private Integer numDice;

	/** Number of faces on each die */
	private Integer numDieFaces;

	public Ruleset(final Integer numDice, final Integer numDieFaces)
	{
		this.numDice = numDice;
		this.numDieFaces = numDieFaces;
	}

	public Integer getNumDice()
	{
		return this.numDice;
	}

	public Integer getNumDieFaces()
	{
		return this.numDieFaces;
	}
}