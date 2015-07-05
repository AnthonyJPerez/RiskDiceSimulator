
package brix.risk;

public class Statistics
{
	private Boolean attackerWon;
	private Double attackerArmiesPercentRemaining;
	private Double defenderArmiesPercentRemaining;

	public Statistics()
	{
		attackerWon = false;
		attackerArmiesPercentRemaining = 0.0;
		defenderArmiesPercentRemaining = 0.0;
	}

	public Boolean getAttackerWon()
	{
		return this.attackerWon;
	}

	public void setAttackerWon(final Boolean attackerWon)
	{
		this.attackerWon = attackerWon;
	}

	public void setAttackerArmiesPercentRemaining(final Double armiesPercentRemaining)
	{
		this.attackerArmiesPercentRemaining = armiesPercentRemaining;
	}

	public Double getAttackerArmiesPercentRemaining()
	{
		return this.attackerArmiesPercentRemaining;
	}

	public void setDefenderArmiesPercentRemaining(final Double armiesPercentRemaining)
	{
		this.defenderArmiesPercentRemaining = armiesPercentRemaining;
	}

	public Double getDefenderArmiesPercentRemaining()
	{
		return this.defenderArmiesPercentRemaining;
	}
}