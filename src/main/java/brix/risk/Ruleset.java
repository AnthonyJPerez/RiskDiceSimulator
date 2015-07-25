package brix.risk;

/** Defines the rules that an Attacker must abide by. */
public class Ruleset
{
	public static final Integer DEFAULT_NUM_DIE = 2;
	public static final Integer DEFAULT_DIE_FACES = 6;

	/** The number of dice used per action */
	private Integer numDice;

	/** Number of faces on each die */
	private Integer numDieFaces;

	public Ruleset(final Integer numDice, final Integer numDieFaces)
	{
		this.numDice = numDice;
		this.numDieFaces = numDieFaces;
	}

	public Ruleset()
	{
		this.numDice = DEFAULT_NUM_DIE;
		this.numDieFaces = DEFAULT_DIE_FACES;
	}

	public Integer getNumDice()
	{
		return this.numDice;
	}

	public void setNumDice(final Integer dice)
	{
		this.numDice = dice;
	}

	public Integer getNumDieFaces()
	{
		return this.numDieFaces;
	}

	public void setNumDieFaces(final Integer dieFaces)
	{
		this.numDieFaces = dieFaces;
	}
}