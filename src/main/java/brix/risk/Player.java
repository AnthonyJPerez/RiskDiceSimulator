
package brix.risk;


/** Defines a mass of armies */
public class Player
{
	/** Initial number of armies **/
	private Integer armies;
	private Integer initialArmies;
	private Ruleset rules;

	/** Keep simulating until the number of armies drops to this amount */
	private Integer minRemaining;

	public Player(Ruleset rules, final Integer initialArmies, final Integer minRemaining)
	{
		this.armies = initialArmies;
		this.initialArmies = initialArmies;
		this.minRemaining = minRemaining;
		this.rules = rules;
	}

	public void reset()
	{
		this.armies = this.initialArmies;
	}

	public Ruleset getRuleset()
	{
		return this.rules;
	}

	public void setArmies(final Integer armies)
	{
		this.armies = armies;
	}

	public Integer getArmies()
	{
		return armies;
	}

	public Integer getInitialArmies()
	{
		return initialArmies;
	}

	public Integer getMinRemaining()
	{
		return minRemaining;
	}
}