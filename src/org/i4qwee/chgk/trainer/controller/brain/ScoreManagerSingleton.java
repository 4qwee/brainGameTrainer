package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.brain.manager.PriceManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.ScoreManager;

import java.util.Observable;

/**
 * User: 4qwee
 * Date: 30.10.11
 * Time: 20:36
 */
public class ScoreManagerSingleton extends Observable
{
    private static final ScoreManagerSingleton ourInstance = new ScoreManagerSingleton();

    private final ScoreManager scoreManager = ScoreManager.getInstance();
    private final PriceManager priceManager = PriceManager.getInstance();

    public static ScoreManagerSingleton getInstance()
    {
        return ourInstance;
    }

    private ScoreManagerSingleton()
    {
    }

    public void newGame()
    {
        scoreManager.setLeftScore(0);
        scoreManager.setRightScore(0);

        priceManager.setPrice(1);
        RoundManager.getInstance().setRound(1);
    }
}
