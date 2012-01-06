package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.RoundListener;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 11:51
 */
public class RoundManager extends Manager<RoundListener>
{
    private static final RoundManager instance = new RoundManager();

    private int round;

    public RoundManager()
    {
    }

    public static RoundManager getInstance()
    {
        return instance;
    }

    public int getRound()
    {
        return round;
    }

    public void setRound(int round)
    {
        this.round = round;
        notifyListeners();
    }

    @Override
    protected void notifyListeners()
    {
        for (RoundListener listener : listeners)
            listener.onRoundChange(round);
    }
}
