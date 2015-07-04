
package brix.risk;

public class Statistics
{
	private Boolean attackerWon;
	private Double attackerArmiesLostPercentage;
	private Double defenderArmiesLostPercentage;

	public Statistics()
	{
		attackerWon = false;
		attackerArmiesLostPercentage = 0.0;
		defenderArmiesLostPercentage = 0.0;
	}

	public Boolean getAttackerWon()
	{
		return this.attackerWon;
	}

	public void setAttackerWon(final Boolean attackerWon)
	{
		this.attackerWon = attackerWon;
	}

	public void setAttackerArmiesLostPercentage(final Double armiesLostPercentage)
	{
		this.attackerArmiesLostPercentage = armiesLostPercentage;
	}

	public Double getAttackerArmiesLostPercentage()
	{
		return this.attackerArmiesLostPercentage;
	}

	public void setDefenderArmiesLostPercentage(final Double armiesLostPercentage)
	{
		this.defenderArmiesLostPercentage = armiesLostPercentage;
	}

	public Double getDefenderArmiesLostPercentage()
	{
		return this.defenderArmiesLostPercentage;
	}
}