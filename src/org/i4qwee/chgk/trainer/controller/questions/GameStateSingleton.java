package org.i4qwee.chgk.trainer.controller.questions;

import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.model.enums.GameState;

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

    private RoundManager roundManager = RoundManager.getInstance();

    public static GameStateSingleton getInstance()
    {
        return ourInstance;
    }

    private GameStateSingleton()
    {
        gameState = GameState.INIT;
    }

    public void setGameState(GameState gameState)
    {

        this.gameState = gameState;

    }
}
