package org.i4qwee.chgk.trainer.controller;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.model.GameState;
import org.i4qwee.chgk.trainer.model.GameStateSingleton;
import org.i4qwee.chgk.trainer.view.QuestionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:30
 */
public class TimeButtonActionListener implements ActionListener
{
    private static Logger logger = Logger.getLogger(TimeButtonActionListener.class);

    private Timer timer;
    private QuestionPanel questionPanel;

    public TimeButtonActionListener(Timer timer, QuestionPanel questionPanel)
    {
        this.timer = timer;
        this.questionPanel = questionPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        switch (GameStateSingleton.getInstance().getGameState())
        {
            case INIT:
                setGameState(GameState.WAIT_START_TIMER);
                //todo add get questions
                break;
            case WAIT_START_TIMER:
                timer.timerStart();
                break;
            case RUNNING:
                break;
            case PAUSED:
                break;
            case FINISHED:
                //todo add get questions
                break;
            default:
                logger.error("Unsupported game state: " + GameStateSingleton.getInstance().getGameState());
        }
    }

    private void setGameState(GameState gameState)
    {
        GameStateSingleton.getInstance().setGameState(gameState);
    }
}
