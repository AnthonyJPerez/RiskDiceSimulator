
package brix;

import brix.risk.RiskSimulation;


public class StatsOutput
{
    private Integer avgAttackerWins;
    private Double  avgAttackerWinsPercent;
    private Integer avgAttackerArmiesRemaining;
    private Double  avgAttackerArmiesRemainingPercent;
    private Integer avgDefenderArmiesRemaining;
    private Double  avgDefenderArmiesRemainingPercent;
    private Integer numSimulations;

    public StatsOutput (final Integer avgAttackerWins,
                        final Double avgAttackerWinsPercent,
                        final Integer avgAttackerArmiesRemaining,
                        final Double avgAttackerArmiesRemainingPercent,
                        final Integer avgDefenderArmiesRemaining,
                        final Double avgDefenderArmiesRemainingPercent,
                        final Integer numSimulations)
    {
        this.avgAttackerWins = avgAttackerWins;
        this.avgAttackerWinsPercent = avgAttackerWinsPercent;
        this.avgAttackerArmiesRemaining = avgAttackerArmiesRemaining;
        this.avgAttackerArmiesRemainingPercent = avgAttackerArmiesRemainingPercent;
        this.avgDefenderArmiesRemaining = avgDefenderArmiesRemaining;
        this.avgDefenderArmiesRemainingPercent = avgDefenderArmiesRemainingPercent;
        this.numSimulations = numSimulations;
    }

    public StatsOutput ()
    {
        avgAttackerWins = 0;
        avgAttackerWinsPercent = 0.0;
        avgAttackerArmiesRemaining = 0;
        avgAttackerArmiesRemainingPercent = 0.0;
        avgDefenderArmiesRemaining = 0;
        avgDefenderArmiesRemainingPercent = 0.0;
        numSimulations = RiskSimulation.DEFAULT_SIMULATION_COUNT;
    }

    public Integer getAvgAttackerWins ()
    {
        return avgAttackerWins;
    }

    public void setAvgAttackerWins (Integer avgAttackerWins)
    {
        this.avgAttackerWins = avgAttackerWins;
    }

    public Double getAvgAttackerWinsPercent ()
    {
        return avgAttackerWinsPercent;
    }

    public void setAvgAttackerWinsPercent (Double avgAttackerWinsPercent)
    {
        this.avgAttackerWinsPercent = avgAttackerWinsPercent;
    }

    public Integer getAvgAttackerArmiesRemaining ()
    {
        return avgAttackerArmiesRemaining;
    }

    public void setAvgAttackerArmiesRemaining (Integer avgAttackerArmiesRemaining)
    {
        this.avgAttackerArmiesRemaining = avgAttackerArmiesRemaining;
    }

    public Double getAvgAttackerArmiesRemainingPercent ()
    {
        return avgAttackerArmiesRemainingPercent;
    }

    public void setAvgAttackerArmiesRemainingPercent (Double avgAttackerArmiesRemainingPercent)
    {
        this.avgAttackerArmiesRemainingPercent = avgAttackerArmiesRemainingPercent;
    }

    public Integer getAvgDefenderArmiesRemaining ()
    {
        return avgDefenderArmiesRemaining;
    }

    public void setAvgDefenderArmiesRemaining (Integer avgDefenderArmiesRemaining)
    {
        this.avgDefenderArmiesRemaining = avgDefenderArmiesRemaining;
    }

    public Double getAvgDefenderArmiesRemainingPercent ()
    {
        return avgDefenderArmiesRemainingPercent;
    }

    public void setAvgDefenderArmiesRemainingPercent (Double avgDefenderArmiesRemainingPercent)
    {
        this.avgDefenderArmiesRemainingPercent = avgDefenderArmiesRemainingPercent;
    }

    public Integer getNumSimulations ()
    {
        return numSimulations;
    }

    public void setNumSimulations (Integer numSimulations)
    {
        this.numSimulations = numSimulations;
    }
}