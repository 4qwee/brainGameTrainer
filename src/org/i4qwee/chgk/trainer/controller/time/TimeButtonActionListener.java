package org.i4qwee.chgk.trainer.controller.time;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.brain.manager.GameStateManager;
import org.i4qwee.chgk.trainer.controller.questions.QuestionsCache;
import org.i4qwee.chgk.trainer.model.enums.GameState;
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

    private QuestionPanel questionPanel;

    private final GameStateManager gameStateManager = GameStateManager.getInstance();

    public TimeButtonActionListener(QuestionPanel questionPanel)
    {
        this.questionPanel = questionPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        switch (gameStateManager.getGameState())
        {
            case INIT:
                setGameState(GameState.WAIT_START_TIMER);
                questionPanel.setQuestion(QuestionsCache.getNextQuestion(), false);
                break;
            case WAIT_START_TIMER:
                setGameState(GameState.RUNNING);
                break;
            case RUNNING:
                break;
            case PAUSED:
                break;
            case FINISHED:
                questionPanel.setQuestion(QuestionsCache.getNextQuestion(), false);
                setGameState(GameState.WAIT_START_TIMER);
                break;
            default:
                logger.error("Unsupported game state: " + gameStateManager.getGameState());
        }
    }

    private void setGameState(GameState gameState)
    {
        gameStateManager.setGameState(gameState);
    }
}
