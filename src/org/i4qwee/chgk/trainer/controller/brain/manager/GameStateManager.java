package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.GameStateListener;
import org.i4qwee.chgk.trainer.model.enums.GameState;
import org.i4qwee.chgk.trainer.view.dialogs.BrainConfirmationDialog;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 15:07
 */
public class GameStateManager extends Manager<GameStateListener>
{
    private static final GameStateManager instance = new GameStateManager();

    private GameState gameState = GameState.INIT;

    private GameStateManager()
    {
    }

    public static GameStateManager getInstance()
    {
        return instance;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
        notifyListeners();
    }

    @Override
    protected void notifyListeners()
    {
        for (GameStateListener listener : listeners)
        {
            if (!(listener instanceof BrainConfirmationDialog))
            {
                listener.onGameStageChanged(gameState);
            }
        }

        for (GameStateListener listener : listeners)
        {
            if (listener instanceof BrainConfirmationDialog)
            {
                listener.onGameStageChanged(gameState);
            }
        }
    }
}
