package org.i4qwee.chgk.trainer.controller.questions;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.ScoreManager;
import org.i4qwee.chgk.trainer.model.enums.GameState;

import javax.swing.*;
import java.util.Observable;

/**
 * User: 4qwee
 * Date: 27.10.11
 * Time: 0:00
 */
public class GameStateSingleton extends Observable
{
    private static GameStateSingleton ourInstance = new GameStateSingleton();

    private GameState gameState;
    private int maxRoundsCount = 0;

    private ScoreManager scoreManager;
    private RoundManager roundManager = RoundManager.getInstance();

    public static GameStateSingleton getInstance()
    {
        return ourInstance;
    }

    private GameStateSingleton()
    {
        gameState = GameState.INIT;
        scoreManager = ScoreManager.getInstance();
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        switch (gameState)
        {
            case FINISHED:
                roundManager.setRound(roundManager.getRound() + 1);

                if (roundManager.getRound() > maxRoundsCount)
                {
                    if (scoreManager.getLeftScore() > scoreManager.getRightScore())
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ScoreManagerSingleton.getInstance().getLeftName() + " выиграл!", "", JOptionPane.PLAIN_MESSAGE);
                    else if (scoreManager.getLeftScore() < scoreManager.getRightScore())
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ScoreManagerSingleton.getInstance().getRightName() + " выиграл!", "", JOptionPane.PLAIN_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ничья!", "", JOptionPane.PLAIN_MESSAGE);

                    ScoreManagerSingleton.getInstance().newGame();
                }

                break;
        }

        this.gameState = gameState;

        fireGamesStateChangedEvent(gameState);
    }

    private void fireGamesStateChangedEvent(GameState gameState)
    {
        setChanged();

        notifyObservers(gameState);
    }

    public int getMaxRoundsCount()
    {
        return maxRoundsCount;
    }

    public void setMaxRoundsCount(int maxRoundsCount)
    {
        this.maxRoundsCount = maxRoundsCount;
    }
}
