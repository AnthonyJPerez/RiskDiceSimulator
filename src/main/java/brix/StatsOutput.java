
package brix;

import brix.risk.RiskSimulation;


public class StatsOutput
{
    // Results
    private Integer avgAttackerWins;
    private Double  avgAttackerWinsPercent;
    private Integer avgAttackerArmiesRemaining;
    private Double  avgAttackerArmiesRemainingPercent;
    private Integer avgDefenderArmiesRemaining;
    private Double  avgDefenderArmiesRemainingPercent;

    // Parameters
    private Integer iterations;
    private Integer initialAttackerArmies;
    private Integer initialDefenderArmies;

    public StatsOutput (final Integer avgAttackerWins,
                        final Double avgAttackerWinsPercent,
                        final Integer avgAttackerArmiesRemaining,
                        final Double avgAttackerArmiesRemainingPercent,
                        final Integer avgDefenderArmiesRemaining,
                        final Double avgDefenderArmiesRemainingPercent,
                        final Integer numSimulations,
                        final Integer initialAttackerArmies,
                        final Integer initialDefenderArmies)
    {
        this.avgAttackerWins = avgAttackerWins;
        this.avgAttackerWinsPercent = avgAttackerWinsPercent;
        this.avgAttackerArmiesRemaining = avgAttackerArmiesRemaining;
        this.avgAttackerArmiesRemainingPercent = avgAttackerArmiesRemainingPercent;
        this.avgDefenderArmiesRemaining = avgDefenderArmiesRemaining;
        this.avgDefenderArmiesRemainingPercent = avgDefenderArmiesRemainingPercent;
        this.iterations = numSimulations;
        this.initialAttackerArmies = initialAttackerArmies;
        this.initialDefenderArmies = initialDefenderArmies;
    }

    public StatsOutput ()
    {
        avgAttackerWins = 0;
        avgAttackerWinsPercent = 0.0;
        avgAttackerArmiesRemaining = 0;
        avgAttackerArmiesRemainingPercent = 0.0;
        avgDefenderArmiesRemaining = 0;
        avgDefenderArmiesRemainingPercent = 0.0;
        iterations = RiskSimulation.DEFAULT_SIMULATION_COUNT;
        initialAttackerArmies = 0;
        initialDefenderArmies = 0;
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

    public Integer getIterations ()
    {
        return iterations;
    }

    public void setIterations (Integer iterations)
    {
        this.iterations = iterations;
    }

    public Integer getInitialAttackerArmies ()
    {
        return initialAttackerArmies;
    }

    public void setInitialAttackerArmies (Integer initialAttackerArmies)
    {
        this.initialAttackerArmies = initialAttackerArmies;
    }

    public Integer getInitialDefenderArmies ()
    {
        return initialDefenderArmies;
    }

    public void setInitialDefenderArmies (Integer initialDefenderArmies)
    {
        this.initialDefenderArmies = initialDefenderArmies;
    }

}