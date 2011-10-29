package org.i4qwee.chgk.trainer.controller.brain;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.model.GameState;
import org.i4qwee.chgk.trainer.model.GameStateSingleton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 12:26
 */
public class BrainMouseListener extends MouseAdapter
{
    private static Logger logger = Logger.getLogger(BrainMouseListener.class);

    public void mousePressed(MouseEvent event)
    {
        switch (GameStateSingleton.getInstance().getGameState())
        {
            case INIT:
                break;
            case WAIT_START_TIMER:
                break;
            case RUNNING:
                //todo add impl
                GameStateSingleton.getInstance().setGameState(GameState.PAUSED);
                break;
            case PAUSED:
                break;
            case FINISHED:
                break;
            default:
                logger.error("Unsupported game state: " + GameStateSingleton.getInstance().getGameState());
        }
    }
}
