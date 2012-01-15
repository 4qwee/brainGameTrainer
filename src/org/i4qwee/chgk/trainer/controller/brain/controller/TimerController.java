package org.i4qwee.chgk.trainer.controller.brain.controller;

import org.i4qwee.chgk.trainer.controller.brain.listener.GameStateListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.GameStateManager;
import org.i4qwee.chgk.trainer.controller.time.Timer;
import org.i4qwee.chgk.trainer.model.enums.GameState;

/**
 * User: 4qwee
 * Date: 15.01.12
 * Time: 11:35
 */
public class TimerController implements GameStateListener
{
    private static final TimerController instance = new TimerController();

    private final GameStateManager gameStateManager = GameStateManager.getInstance();
    private final Timer timer = Timer.getInstance();

    private TimerController()
    {
        gameStateManager.addListener(this);
    }

    public static TimerController getInstance()
    {
        return instance;
    }

    public void onGameStageChanged(GameState gameState)
    {
        switch (gameState)
        {
            case WAIT_START_TIMER:
                timer.restart();
                break;
            case RUNNING:
                timer.start();
                break;
            case PAUSED:
                timer.stop();
                break;
            case FINISHED:
                timer.stop();
                break;
        }
    }
}
