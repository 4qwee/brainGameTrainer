package org.i4qwee.chgk.trainer.controller.brain.listener;

import org.i4qwee.chgk.trainer.model.enums.GameState;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 15:06
 */
public interface GameStateListener
{
    void onGameStageChanged(GameState gameState);
}
