package org.i4qwee.chgk.trainer.controller.questions;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
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
    private int roundsCount = 0;
    private int maxRoundsCount = 0;

    public static GameStateSingleton getInstance()
    {
        return ourInstance;
    }

    private GameStateSingleton()
    {
        gameState = GameState.INIT;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        if (gameState.equals(GameState.FINISHED))
        {
            if (++roundsCount == maxRoundsCount)
            {
                if (ScoreManagerSingleton.getInstance().getLeftScore() > ScoreManagerSingleton.getInstance().getRightScore())
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ScoreManagerSingleton.getInstance().getLeftName() + " выиграл!", "", JOptionPane.PLAIN_MESSAGE);
                else if (ScoreManagerSingleton.getInstance().getLeftScore() < ScoreManagerSingleton.getInstance().getRightScore())
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ScoreManagerSingleton.getInstance().getRightName() + " выиграл!", "", JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ничья!", "", JOptionPane.PLAIN_MESSAGE);

                ScoreManagerSingleton.getInstance().newGame();
            }
        }

        this.gameState = gameState;

        setChanged();

        notifyObservers(gameState);
    }

    public void setMaxRoundsCount(int maxRoundsCount)
    {
        this.maxRoundsCount = maxRoundsCount;
    }

    public void resetRoundsCount()
    {
        roundsCount = 0;
    }
}
