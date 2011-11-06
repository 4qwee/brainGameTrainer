package org.i4qwee.chgk.trainer.model.events;

/**
 * User: 4qwee
 * Date: 06.11.11
 * Time: 11:02
 */
public class RoundChangedEvent
{
    private int round;
    private int maxRoundsCount;

    public RoundChangedEvent(int round, int maxRoundsCount)
    {
        this.round = round;
        this.maxRoundsCount = maxRoundsCount;
    }

    public int getRound()
    {
        return round;
    }

    public int getMaxRoundsCount()
    {
        return maxRoundsCount;
    }
}
